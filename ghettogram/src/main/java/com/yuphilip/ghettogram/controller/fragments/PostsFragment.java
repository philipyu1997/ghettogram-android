package com.yuphilip.ghettogram.controller.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.yuphilip.ghettogram.R;
import com.yuphilip.ghettogram.controller.adapters.PostsAdapter;
import com.yuphilip.ghettogram.model.Post;
import com.yuphilip.ghettogram.model.helper.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.List;

public class PostsFragment extends Fragment {

    public static final String TAG = "PostsFragment";
    private RecyclerView rvPosts;
    protected List<Post> mPosts;
    protected PostsAdapter adapter;
    private SwipeRefreshLayout swipeContainer;
    private EndlessRecyclerViewScrollListener scrollListener;
    private int skipPosts = 0;

    // onCreateView to inflate the view
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_posts, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        // Lookup the swipe container view
        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);

        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                Log.d(TAG, "Pull to Refresh");

                // Reset states
                mPosts.clear();
                skipPosts = 0;
                scrollListener.resetState();

                // Fetch posts
                queryPosts();
                swipeContainer.setRefreshing(false);
            }
        });

        rvPosts = view.findViewById(R.id.rvPosts);

        // create the data source
        mPosts = new ArrayList<>();

        // create the adapter
        adapter = new PostsAdapter(getContext(), mPosts);

        // set the layout manager on the recycler view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvPosts.setLayoutManager(linearLayoutManager);

        // set the adapter on the recycler view
        rvPosts.setAdapter(adapter);

        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                Log.d(TAG, "On load more... page:" + page);
                queryMorePosts();
            }
        };

        // Adds the scroll listener to RecyclerView
        rvPosts.addOnScrollListener(scrollListener);

        queryPosts();

    }

    private void queryMorePosts() {

        Log.d(TAG, "Load more posts...");
        skipPosts = mPosts.size();
        queryPosts();

    }

    protected void queryPosts() {

        final ParseQuery<Post> postQuery = new ParseQuery<Post>(Post.class);

        postQuery.include(Post.KEY_USER);
        postQuery.setSkip(skipPosts);
        postQuery.setLimit(6);
        postQuery.addDescendingOrder(Post.KEY_CREATED_AT);

        postQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Error with query");
                    e.printStackTrace();
                    return;
                }

                mPosts.addAll(posts);
                adapter.notifyDataSetChanged();

                for (int i = 0; i < posts.size(); ++i) {
                    Log.d(TAG, "Post: " + posts.get(i).getDescription() + ", username: " + posts.get(i).getUser().getUsername());
                }
            }
        });
    }

}

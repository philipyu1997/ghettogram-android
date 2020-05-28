package com.yuphilip.ghettogram.controller.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;
import com.yuphilip.ghettogram.R;
import com.yuphilip.ghettogram.controller.activities.DetailActivity;
import com.yuphilip.ghettogram.model.Constant;
import com.yuphilip.ghettogram.model.Post;

import org.parceler.Parcels;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;

    public PostsAdapter(Context context, List<Post> posts) {

        this.context = context;
        this.posts = posts;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Post post = posts.get(position);
        holder.bind(post);

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout container;
        private ImageView ivProfileImage;
        private ImageView ivPostImage;
        private TextView tvHandle;
        private TextView tvCreatedAt;
        private TextView tvDescription;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            container = itemView.findViewById(R.id.container);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            ivPostImage = itemView.findViewById(R.id.ivPostImage);
            tvHandle = itemView.findViewById(R.id.tvHandle);
            tvCreatedAt = itemView.findViewById(R.id.tvCreatedAt);
            tvDescription = itemView.findViewById(R.id.tvDescription);

        }

        public void bind(final Post post) {

            // TODO: bind the view element to the post
            tvHandle.setText(post.getUser().getUsername());
            tvCreatedAt.setText(String.format("%s", Constant.getRelativeTimeAgo(post.getCreatedAt().toString())));

            ParseFile postImage = post.getPostImage();

            if (postImage != null) {
                Glide.with(context)
                        .load(postImage.getUrl())
                        .into(ivPostImage);
            }

            Constant.setProfileImage(context, ivProfileImage);

            tvDescription.setText(post.getDescription());

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Segue to Detail Activity...", Toast.LENGTH_SHORT).show();

                    // Tapped post, segue to detail activity
                    Intent i = new Intent(context, DetailActivity.class);
                    i.putExtra("post", Parcels.wrap(post));
                    context.startActivity(i);
                }
            });

        }

        // Clean all elements of the recycler
        public void clear() {

            posts.clear();
            notifyDataSetChanged();

        }

        // Add a list of items -- change to type used
        public void addAll(List<Post> list) {

            posts.addAll(list);
            notifyDataSetChanged();

        }

    }

}

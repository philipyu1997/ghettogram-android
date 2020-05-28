package com.yuphilip.ghettogram.controller.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.yuphilip.ghettogram.R;
import com.yuphilip.ghettogram.databinding.ActivityDetailBinding;
import com.yuphilip.ghettogram.model.Constant;
import com.yuphilip.ghettogram.model.Post;

import org.parceler.Parcels;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        ActivityDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        ImageView ivProfileImage = binding.ivProfileImage;
        ImageView ivPostImage = binding.ivPostImage;
        TextView tvHandle = binding.tvHandle;
        TextView tvCreatedAt = binding.tvCreatedAt;
        TextView tvDescription = binding.tvDescription;

        Post post = Parcels.unwrap(getIntent().getParcelableExtra("post"));

        Log.d("DetailActivity", "" + post.getDescription());

        Glide.with(this)
                .load(post.getPostImage().getUrl())
                .into(ivPostImage);
        ivProfileImage.setImageResource(R.drawable.ic_instagram_user_filled_24);
        tvHandle.setText(post.getUser().getUsername());
        tvCreatedAt.setText(Constant.getRelativeTimeAgo(post.getCreatedAt().toString()));
        tvDescription.setText(post.getDescription());

        Constant.setProfileImage(this, ivProfileImage);

    }

}

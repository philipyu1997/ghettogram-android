package com.yuphilip.ghettogram.controller.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.yuphilip.ghettogram.R;
import com.yuphilip.ghettogram.model.Constant;
import com.yuphilip.ghettogram.model.Post;

import org.parceler.Parcels;

public class DetailActivity extends AppCompatActivity {

    ImageView ivPostImage;
    ImageView ivProfileImage;
    TextView tvHandle;
    TextView tvCreatedAt;
    TextView tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        ivPostImage = findViewById(R.id.ivPostImage);
        ivProfileImage = findViewById(R.id.ivProfileImage);
        tvHandle = findViewById(R.id.tvHandle);
        tvCreatedAt = findViewById(R.id.tvCreatedAt);
        tvDescription = findViewById(R.id.tvDescription);

        Post post = Parcels.unwrap(getIntent().getParcelableExtra("post"));

        Log.d("DetailActivity", "" + post.getDescription());

        Glide.with(this)
                .load(post.getPostImage().getUrl())
                .into(ivPostImage);
        ivProfileImage.setImageResource(R.drawable.ic_instagram_user_filled_24);
        tvHandle.setText(post.getUser().getUsername());
        tvCreatedAt.setText(Constant.getRelativeTimeAgo(post.getCreatedAt().toString()));
        tvDescription.setText(post.getDescription());

    }

}

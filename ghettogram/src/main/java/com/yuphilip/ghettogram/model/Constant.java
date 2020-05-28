package com.yuphilip.ghettogram.model;

import android.content.Context;
import android.text.format.DateUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.yuphilip.ghettogram.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Constant {

    public static final ParseUser currentUser = ParseUser.getCurrentUser();
    public static final String photoFileName = "photo.jpg";
    public final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1034;

    // getRelativeTimeAgo("Mon Apr 01 21:16:23 +0000 2014");
    public static String getRelativeTimeAgo(String rawJsonDate) {

        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";

        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS, DateUtils.FORMAT_ABBREV_RELATIVE).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;

    }

    public static void setProfileImage(Context context, ImageView ivProfileImage) {

        ParseFile profileImage = Constant.currentUser.getParseFile("profileImage");

        if (profileImage != null) {
            Glide.with(context)
                    .load(profileImage.getUrl())
                    .apply(RequestOptions.circleCropTransform())
                    .into(ivProfileImage);
        } else {
            Glide.with(context)
                    .load(R.drawable.ic_instagram_user_filled_24)
                    .apply(RequestOptions.circleCropTransform())
                    .into(ivProfileImage);
        }

    }

}

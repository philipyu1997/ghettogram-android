package com.yuphilip.ghettogram.model.net;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;
import com.yuphilip.ghettogram.BuildConfig;
import com.yuphilip.ghettogram.model.Post;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {

        super.onCreate();

        ParseObject.registerSubclass(Post.class);
        // set applicationId, and server server based on the values in the Heroku settings.
        // clientKey is not needed unless explicitly configured
        // any network interceptors must be added with the Configuration Builder given this syntax
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(BuildConfig.APP_ID) // should correspond to APP_ID env variable
                .clientKey(BuildConfig.MASTER_KEY)  // set explicitly unless clientKey is explicitly configured on Parse server
                .server(BuildConfig.SERVER_URL)
                .build());

    }

}

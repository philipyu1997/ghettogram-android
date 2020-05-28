package com.yuphilip.ghettogram.controller.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.yuphilip.ghettogram.R;
import com.yuphilip.ghettogram.databinding.ActivityLoginBinding;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private EditText etUsername;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        if (ParseUser.getCurrentUser() != null) {
            // do stuff with the user
            goMainActivity();
            Log.d(TAG, "Welcome back!");
        } else {
            // show the signup or login screen
            ParseUser.logOut();
        }

        etUsername = binding.etUsername;
        etPassword = binding.etPassword;

        Button btnLogin = binding.btnLogin;
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                login(username, password);
            }
        });

        Button btnSignUp = binding.btnSignUp;
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                signUp(username, password);
            }
        });

    }

    private void signUp(final String username, String password) {

        ParseUser user = new ParseUser();

        user.setUsername(username);
        user.setPassword(password);

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with sign up");
                    e.printStackTrace();
                    return;
                } else {
                    Log.d(TAG, "Successfully registered user: " + username);
                    Toast.makeText(LoginActivity.this, "Signed Up", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

    }

    private void login(String username, String password) {

        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {

                if (e != null) {
                    Log.e(TAG, "Issue with login");
                    e.printStackTrace();
                    return;
                } else {
                    Log.d(TAG, "Successfully logged in");
                    Toast.makeText(LoginActivity.this, "Logged In", Toast.LENGTH_SHORT).show();
                }

                goMainActivity();

            }
        });

    }

    private void goMainActivity() {

        Log.d(TAG, "Segue into MainActivity");
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();

    }

}

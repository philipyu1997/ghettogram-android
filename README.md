# Ghettogram

## Table of Contents
1. [Overview](#Overview)
2. [Product Specs](#Product-Specs)
3. [App Walkthrough](#App-Walkthrough)
4. [Libraries](#Libraries)
5. [Backend](#Backend)
6. [Credits](#Credits)

## Overview
### Description

Ghettogram is an Instagram-like client with a custom backend server that allows users to post photos and view their feed.

## Product Specs
### User Stories

- [x] User shall be able to sign up to create a new account using Parse authentication.
- [x] User shall be able to log in and log out of his or her account.
- [x] User shall remain logged in across app restarts.
- [x] User shall be able to take a photo, add a caption, and post it to "Instagram".
- [ ] User shall be able to see an app icon in home screen and styled bottom navigation view.
- [x] User shall be able to see an indeterminate progress bar while the post is being uploaded to Parse.
- [x] User shall be able to view the last 20 posts submitted to "Instagram".
- [x] User shall be able to pull to refresh the last 20 posts submitted to "Instagram".
- [x] User shall be able to switch between different tabs - viewing all posts (feed view), capture (camera and photo gallery view) and profile tabs (posts made) using fragments and a Bottom Navigation View.
- [x] User shall be able to load more posts once he or she reaches the bottom of the feed using infinite scrolling.
- [ ] User shall be able to see the username and creation time for each post.
- [ ] User shall be able to tap a post to view post details, including timestamp and caption.
- [ ] User shall be able to add a profile photo.
- [ ] Tapping on a post's username or profile photo shall segue to that user's profile page and shows a grid view of the user's posts.
- [ ] User shall be able to comment on a post and see all comments for each post in the post details screen.
- [ ] User shall be able to like a post and see number of likes for each post in the post details screen.

## App Walkthrough

Here's a GIF of how the app works:

<img src="ADD_GIF_LINK" width=250><br>

## Libraries

- [Android Async HTTP](https://github.com/codepath/CPAsyncHttpClient) - Simple asynchronous HTTP requests with JSON parsing.
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Android.

## Backend

- [Parse](https://github.com/parse-community/Parse-SDK-iOS-OSX) - An open source Backend-as-a-Service (BaaS) framework initially developed by Facebook. The platform now has an active and robust community of fanatical developers who constantly innovate and strive to improve the already impressive and modular platform.

## Credits

>This is a companion project to CodePath's Professional Android Course, check out the full course at [www.codepath.org](https://codepath.org/).

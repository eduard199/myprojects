package com.project.Dao;

import com.project.Exceptions.UserNotExisting;
import com.project.Model.Post;

import java.sql.Timestamp;
import java.util.List;

public interface PostDAO
{
    Post addPost(Post post, int userId);
    List<Post> getOwnPosts(int userId, Timestamp timestamp) throws UserNotExisting;
    List<Post> getFeed(int userId) throws UserNotExisting;
}

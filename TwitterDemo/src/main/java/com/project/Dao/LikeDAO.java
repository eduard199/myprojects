package com.project.Dao;

import com.project.Exceptions.AlreadyLikedThisPost;
import com.project.Model.Like;

import java.util.List;

public interface LikeDAO
{
    Like like(int userId, int postId) throws AlreadyLikedThisPost;
    List<Like> getLikes(int postId);
}

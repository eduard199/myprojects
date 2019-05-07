package com.project.Dao;

import com.project.Exceptions.DuplicateIdAndFollowindUserId;
import com.project.Exceptions.DuplicateUsername;
import com.project.Exceptions.SameUserIdAndFollowingUserId;
import com.project.Exceptions.UsernameNotFound;
import com.project.Model.Follow;
import com.project.Model.User;

import java.util.List;

public interface UserDAO
{
    List<User> searchUser(String name) throws UsernameNotFound;
    User register(User user) throws DuplicateUsername;
    Follow follow(int userId, int followingUserId) throws SameUserIdAndFollowingUserId, DuplicateIdAndFollowindUserId;
    String unfollow(int userId, int unfollowingUserId);
    String unregister(String userName) throws UsernameNotFound;
}

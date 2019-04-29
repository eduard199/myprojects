package com.project.Dao;

import com.project.Exceptions.AlreadyLikedThisPost;
import com.project.Model.Like;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.project.DbConnection.DbConnection.getConnection;


@Component
public class LikeDAOImpl implements LikeDAO {
    @Override
    public Like like(int userId, int postId) throws AlreadyLikedThisPost  {
        String SQL = "INSERT INTO likes(userId,postId) VALUES(?,?)";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, postId);
            preparedStatement.execute();
        } catch (Exception e) {
            throw new AlreadyLikedThisPost("You already liked this post");
        }
        return Like.builder()
                .withUserId(userId)
                .withPostId(postId)
                .build();
    }

    @Override
    public List<Like> getLikes(int postId) {
        List<Like> likes = new ArrayList<>();
        String SQL = "SELECT * FROM likes JOIN posts ON likes.postId=posts.id WHERE likes.postId=?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, postId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                likes.add(Like.builder()
                        .withUserId(resultSet.getInt(2))
                        .withPostId(resultSet.getInt(3))
                        .build());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return likes;
    }
}

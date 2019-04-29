package com.project.Dao;

import com.project.Exceptions.UserNotExisting;
import com.project.Model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.project.DbConnection.DbConnection.getConnection;


@Component
public class PostDAOImpl implements PostDAO {

    private LikeDAO likeDAO;
    private ReplyDAO replyDAO;

    @Autowired
    public PostDAOImpl(LikeDAO likeDAO,ReplyDAO replyDAO) {

        this.likeDAO = likeDAO;
        this.replyDAO = replyDAO;
    }

    @Override
    public Post addPost(Post post, int userId) {
        String SQL = "INSERT INTO posts(userId,message) VALUES (?,?)";
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(SQL,Statement.RETURN_GENERATED_KEYS))
        {
            statement.setInt(1, userId);
            statement.setString(2, post.getMessage());
            statement.executeUpdate();
            ResultSet resultSet=statement.getGeneratedKeys();
            if(resultSet.next())
            {
                post.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public List<Post> getOwnPosts(int userId, Timestamp timestamp) throws UserNotExisting {
        List<Post> postsToReturn = new ArrayList<>();
        String SQL = "SELECT * FROM POSTS WHERE userId = ?";
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            if(!rs.next())
            {
                throw new UserNotExisting("User with the id "+userId+" does not exists");
            }
            while (rs.next()) {
                if (timestamp.before(rs.getTimestamp(4))) {
                    postsToReturn.add(Post.builder()
                            .withId(rs.getInt(1))
                            .withUserId(rs.getInt(2))
                            .withMessage(rs.getString(3))
                            .withLikes(likeDAO.getLikes(rs.getInt(1)))
                            .withReplies(replyDAO.getReplies(rs.getInt(1)))
                            .build());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return postsToReturn;
    }

    @Override
    public List<Post> getFeed(int userId) throws UserNotExisting{
        List<Post> postsToReturn = new ArrayList<>();
        String SQL = "SELECT * FROM posts INNER JOIN followers ON posts.userId=followers.followingUserId WHERE followers.userId=?";
        try (Connection connection = getConnection();
             PreparedStatement statement1 = connection.prepareStatement(SQL)) {
            statement1.setInt(1, userId);
            ResultSet resultSet = statement1.executeQuery();
            if(!resultSet.next())
            {
                throw new UserNotExisting("User does not exists");
            }
            while (resultSet.next()) {
                postsToReturn.add(Post.builder()
                        .withId(resultSet.getInt(1))
                        .withUserId(resultSet.getInt(2))
                        .withMessage(resultSet.getString(3))
                        .withLikes(likeDAO.getLikes(resultSet.getInt(1)))
                        .withReplies(replyDAO.getReplies(resultSet.getInt(1)))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return postsToReturn;
    }
}

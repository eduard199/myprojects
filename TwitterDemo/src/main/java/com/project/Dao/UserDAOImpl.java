package com.project.Dao;

import com.project.Exceptions.DuplicateIdAndFollowindUserId;
import com.project.Exceptions.DuplicateUsername;
import com.project.Exceptions.SameUserIdAndFollowingUserId;
import com.project.Exceptions.UsernameNotFound;
import com.project.Model.Follow;
import com.project.Model.User;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.project.DbConnection.DbConnection.getConnection;

@Component
public class UserDAOImpl implements UserDAO {

    @Override
    public List<User> searchUser(String name) throws UsernameNotFound {
        List<User> usersToReturn = new ArrayList<>();
        String SQL = "SELECT * FROM users WHERE username LIKE ? OR firstname LIKE ? OR lastname LIKE ?";
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setString(1, "%" + name + "%".toLowerCase());
            statement.setString(2, "%" + name + "%".toLowerCase());
            statement.setString(3, "%" + name + "%".toLowerCase());
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next())
            {
                throw new UsernameNotFound("Username not found");
            }
            while (resultSet.next()) {
                usersToReturn.add(User.builder()
                        .withUserId(resultSet.getInt(1))
                        .withUserName(resultSet.getString(2))
                        .withFirstName(resultSet.getString(3))
                        .withLastName(resultSet.getString(4))
                        .withEmail(resultSet.getString(5))
                        .build());
            }
        } catch (SQLException e) {
            throw new UsernameNotFound("Username not found");
        }
        return usersToReturn;
    }

    @Override
    public User register(User user) throws DuplicateUsername {
        String SQL = "INSERT INTO users(username,firstname,lastname,email,password) VALUES(?,?,?,?,?)";
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPassword());
            statement.executeUpdate();
            ResultSet resultSet=statement.getGeneratedKeys();
            if(resultSet.next())
            {
                user.setUserId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            throw new DuplicateUsername("This username already exists");
        }
        return user;
    }

    @Override
    public Follow follow(int userId, int followingUserId) throws SameUserIdAndFollowingUserId, DuplicateIdAndFollowindUserId {
        if (userId == followingUserId) {
            throw new SameUserIdAndFollowingUserId("Same user id and following user id");
        }
        String SQL1 = "INSERT INTO followers(userId,followingUserId) VALUES (?,?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL1)) {
            statement.setInt(1, userId);
            statement.setInt(2, followingUserId);
            statement.execute();
        } catch (SQLException e) {
            throw new DuplicateIdAndFollowindUserId("Pair userId and followingUserId already exists in table followers");
        }
        return Follow.builder()
                .withUserId(userId)
                .withFollowingUserId(followingUserId)
                .build();
    }
}

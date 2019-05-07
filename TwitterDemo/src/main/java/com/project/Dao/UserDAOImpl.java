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
        String SQL = "SELECT * FROM users WHERE username LIKE ?";
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setString(1, "%" + name + "%".toLowerCase());
//            statement.setString(1,name.toLowerCase());
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next())
            {
                throw new UsernameNotFound("Username not found");
            }
            do {
                usersToReturn.add(User.builder()
                        .withUserId(resultSet.getInt(1))
                        .withUserName(resultSet.getString(2))
                        .withFirstName(resultSet.getString(3))
                        .withLastName(resultSet.getString(4))
                        .withEmail(resultSet.getString(5))
                        .build());
            }
            while (resultSet.next());
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

    @Override
    public String unfollow(int userId, int unfollowingUserId) {
        String SQL="DELETE FROM followers WHERE followingUserId=? AND userId=?";
        try(Connection connection=getConnection();
            PreparedStatement statement=connection.prepareStatement(SQL))
        {
            statement.setInt(1,unfollowingUserId);
            statement.setInt(2,userId);
            statement.execute();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return "The user with the id "+userId+" is no longer following the user with the id "+unfollowingUserId;
    }

    @Override
    public String unregister(String userName) throws UsernameNotFound
    {
        String SQL="SELECT id FROM users WHERE userName=?";
        String SQL1="DELETE FROM users WHERE userName=?";
        String SQL2="DELETE FROM posts WHERE userId=?";
        try(Connection connection=getConnection();
            PreparedStatement statement=connection.prepareStatement(SQL);
            PreparedStatement statement1=connection.prepareStatement(SQL1);
            PreparedStatement statement2=connection.prepareStatement(SQL2))
        {
            statement.setString(1,userName);
            ResultSet rs=statement.executeQuery();
            if(!rs.next())
            {
                throw new UsernameNotFound("User name not found");
            }
            else
            {
                statement1.setString(1,userName);
                statement2.setInt(1,rs.getInt(1));
                statement2.execute();
                statement1.execute();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return "User name "+userName+" and all his posts have been deleted";
    }
}

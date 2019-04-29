package com.project.Dao;

import com.project.Model.Reply;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.project.DbConnection.DbConnection.getConnection;

@Component
public class ReplyDAOImpl implements ReplyDAO {


    @Override
    public Reply reply(int userId, Reply reply) {
        String SQL="INSERT INTO posts(userId,message,parentId,visibility) VALUES(?,?,?,?)";
        try(Connection connection=getConnection(); PreparedStatement preparedStatement=connection.prepareStatement(SQL))
        {
            preparedStatement.setInt(1,userId);
            preparedStatement.setString(2,reply.getMessage());
            preparedStatement.setInt(3,reply.getParentId());
            preparedStatement.setInt(4,reply.getVisibility());
            preparedStatement.execute();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return Reply.builder()
                .withMessage(reply.getMessage())
                .withParentId(reply.getParentId())
                .withVisibility(reply.getVisibility())
                .build();
    }

    @Override
    public List<Reply> getReplies(int userId) {
        List<Reply> repliesToReturn=new ArrayList<>();
        String SQL="SELECT * FROM posts WHERE parentId=?";
        try(Connection connection=getConnection();PreparedStatement preparedStatement=connection.prepareStatement(SQL))
        {
            preparedStatement.setInt(1,userId);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()) {
                repliesToReturn.add(Reply.builder()
                        .withReplyId(resultSet.getInt(1))
                        .withMessage(resultSet.getString(3))
                        .withParentId(resultSet.getInt(5))
                        .withVisibility(resultSet.getInt(6))
                        .build());
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return  repliesToReturn;
    }
}

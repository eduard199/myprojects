package com.project.Dao;

import com.project.Model.Reply;

import java.util.List;

public interface ReplyDAO {
    Reply reply(int userId, Reply reply);

    List<Reply> getReplies(int userId);
}

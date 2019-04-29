package com.project.Model;

import com.project.Model.Like;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Post
{
    private int id;
    private int userId;
    private String message;
    private List<Like> likes=new ArrayList<>();
    private List<Reply> replies=new ArrayList<>();
    private LocalDateTime timestamp;


    public Post(int id, int userId, String message) {
        this.id = id;
        this.userId = userId;
        this.message = message;
        this.likes=new ArrayList<>();
        this.replies=new ArrayList<>();
        this.timestamp=LocalDateTime.now();
    }

    public Post()
    {

    }
    public Post(Builder builder)
    {
        this.id=builder.id;
        this.userId=builder.userId;
        this.message=builder.message;
        this.timestamp=builder.timestamp;
        this.likes=builder.likes;
        this.replies=builder.replies;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }

    public Timestamp getTimestamp() {
        return Timestamp.valueOf(timestamp);
    }

    public List<Like> getLikes() {
        return likes;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", userId=" + userId +
                ", message='" + message + '\'' +
                ", likes=" + likes +
                ", timestamp=" + timestamp +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int id;
        private int userId;
        private String message;
        private List<Like> likes;
        private List<Reply> replies;
        private LocalDateTime timestamp;

        public Builder withId(int id)
        {
            this.id=id;
            return this;
        }

        public Builder withUserId(int userId) {
            this.userId=userId;
            return this;
        }

        public Builder withMessage(String message) {
            this.message=message;
            return this;
        }

        public Builder withTimeStamp(LocalDateTime timestamp)
        {
            this.timestamp=timestamp;
            return this;
        }
        public Builder withLikes(List<Like> likes)
        {
            this.likes=likes;
            return this;
        }
        public Builder withReplies(List<Reply> replies)
        {
            this.replies=replies;
            return this;
        }
        public Post build() {
            return new Post(this);
        }
    }
}

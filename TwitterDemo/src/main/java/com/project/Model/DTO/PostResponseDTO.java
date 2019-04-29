package com.project.Model.DTO;

import com.project.Model.Like;
import com.project.Model.Reply;

import java.util.ArrayList;
import java.util.List;

public class PostResponseDTO {
    private int id;
    private int userId;
    private String message;
    private List<Like> likes=new ArrayList<>();
    private List<Reply> replies=new ArrayList<>();

    public PostResponseDTO(Builder builder)
    {
        this.id=builder.id;
        this.userId=builder.userId;
        this.message=builder.message;
        this.likes=builder.likes;
        this.replies=builder.replies;
    }

    public PostResponseDTO()
    {

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

    public List<Like> getLikes() {
        return likes;
    }

    public List<Reply> getReplies() {
        return replies;
    }


    @Override
    public String toString() {
        return "PostResponseDTO{" +
                "id=" + id +
                ", userId=" + userId +
                ", message='" + message + '\'' +
                ", likes=" + likes +
                ", replies=" + replies +
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

        public Builder withId(int id)
        {
            this.id=id;
            return this;
        }

        public Builder withUserId(int userId)
        {
            this.userId=userId;
            return this;
        }

        public Builder withMessage(String message)
        {
            this.message=message;
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
        public PostResponseDTO build() {
            return new PostResponseDTO(this);
        }

    }
}

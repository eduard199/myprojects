package com.project.Model;

public class Like
{
    private int id;
    private int userId;
    private int postId;

    public Like(int id, int userId, int postId) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
    }

    public Like() {
    }
    public Like(Builder builder)
    {
        this.postId=builder.postId;
        this.userId=builder.userId;
    }


    public int getUserId() {
        return userId;
    }

    public int getPostId() {
        return postId;
    }

    @Override
    public String toString() {
        return "LikeDAO{" +
                "id=" + id +
                ", userId=" + userId +
                ", postId=" + postId +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int userId;
        private int postId;

        public Builder withUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder withPostId(int postId) {
            this.postId = postId;
            return this;
        }

        public Like build() {
                return new Like(this);
        }
    }
}

package com.project.Model.DTO;


public class LikeResponseDTO {
    private int userId;
    private int postId;

    public LikeResponseDTO(int userId, int postId) {
        this.userId = userId;
        this.postId = postId;
    }

    public LikeResponseDTO() {
    }
    public LikeResponseDTO(Builder builder)
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

        public LikeResponseDTO build() {
            return new LikeResponseDTO(this);
        }
    }
}

package com.project.Model.DTO;

public class FollowResponseDTO {
    private int userId;
    private int followingUserId;

    public FollowResponseDTO(Builder builder)
    {
        this.userId=builder.userId;
        this.followingUserId=builder.followingUserId;
    }
    public FollowResponseDTO()
    {

    }

    public int getUserId() {
        return userId;
    }

    public int getFollowingUserId() {
        return followingUserId;
    }

    @Override
    public String toString() {
        return "FollowResponseDTO{" +
                "userId=" + userId +
                ", followingUserId=" + followingUserId +
                '}';
    }
    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {

        private int userId;
        private int followingUserId;

        public Builder withUserId(int userId)
        {
            this.userId=userId;
            return this;
        }
        public Builder withFollowingUserId(int followingUserId)
        {
            this.followingUserId=followingUserId;
            return this;
        }
        public FollowResponseDTO build() {
            return new FollowResponseDTO(this);
        }
    }
}

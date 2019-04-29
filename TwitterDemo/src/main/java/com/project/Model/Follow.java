package com.project.Model;

import java.time.LocalDateTime;

public class Follow
{
    private int userId;
    private LocalDateTime timestamp=LocalDateTime.now();
    private int followingUserId;

    public Follow(int userId, int followingUserId) {
        this.userId = userId;
        this.timestamp=LocalDateTime.now();
        this.followingUserId = followingUserId;
    }

    public Follow() {
    }
    public Follow(Builder builder)
    {
        this.userId=builder.userId;
        this.followingUserId=builder.followingUserId;
    }

    public int getUserId() {
        return userId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getFollowingUserId() {
        return followingUserId;
    }

    @Override
    public String toString() {
        return "Follow{" +
                ", userId=" + userId +
                ", timestamp=" + timestamp +
                ", followingUserId=" + followingUserId +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int userId;
        private LocalDateTime timestamp;
        private int followingUserId;

        public Builder withUserId(int userId)
        {
            this.userId=userId;
            return this;
        }
        public Builder withTimestamp(LocalDateTime timestamp) {
            this.timestamp=timestamp;
            return this;
        }

        public Builder withFollowingUserId(int followingUserId) {
            this.followingUserId=followingUserId;
            return this;
        }

        public Follow build() {
            return new Follow(this);
        }
    }
}

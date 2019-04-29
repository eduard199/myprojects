package com.project.Model;


public class Reply
{
    private int replyId;
    private int parentId;
    private int visibility;
    private String message;

    public Reply(int parentId, int publicOrPrivate,String message,int replyId)
    {
        this.replyId=replyId;
        this.parentId = parentId;
        this.visibility = publicOrPrivate;
        this.message=message;
    }

    public Reply() {
    }

    public Reply(Builder builder)
    {
        this.replyId=builder.replyId;
        this.parentId=builder.parentId;
        this.visibility=builder.visibility;
        this.message=builder.message;
    }

    public int getReplyId() {
        return replyId;
    }

    public int getParentId() {
        return parentId;
    }

    public int getVisibility() {
        return visibility;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "parentId=" + parentId +
                ", visibility=" + visibility +
                ", message=" + message +
                '}';
    }
    public static Builder builder()
    {
        return new Builder();
    }
    public static class Builder
    {
        private int replyId;
        private int parentId;
        private int visibility;
        private String message;

        public Builder withReplyId(int replyId)
        {
            this.replyId=replyId;
            return this;
        }

        public Builder withParentId(int parentId)
        {
            this.parentId=parentId;
            return this;
        }

        public Builder withVisibility(int visibility)
        {
            this.visibility=visibility;
            return this;
        }

        public Builder withMessage(String message)
        {
            this.message=message;
            return this;
        }

        public Reply build()
        {
            return new Reply(this);
        }
    }
}

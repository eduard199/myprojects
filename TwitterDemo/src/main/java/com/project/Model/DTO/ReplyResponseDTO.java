package com.project.Model.DTO;


public class ReplyResponseDTO
{
    private int parentId;
    private int visibility;
    private String message;

    public ReplyResponseDTO(int parentId, int publicOrPrivate, String message)
    {
        this.parentId = parentId;
        this.visibility = publicOrPrivate;
        this.message=message;
    }

    public ReplyResponseDTO() {
    }

    public ReplyResponseDTO(Builder builder)
    {
        this.parentId=builder.parentId;
        this.visibility=builder.visibility;
        this.message=builder.message;
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
        private int parentId;
        private int visibility;
        private String message;

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

        public ReplyResponseDTO build()
        {
            return new ReplyResponseDTO(this);
        }
    }
}

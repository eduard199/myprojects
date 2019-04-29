package com.project.Model.DTO;

public class PostRequestDTO {
    private String message;

    public PostRequestDTO(String message) {
        this.message = message;
    }

    public PostRequestDTO(Builder builder)
    {
        this.message=builder.message;
    }

    public PostRequestDTO()
    {

    }

    public String getMessage() {
        return message;
    }


    @Override
    public String toString() {
        return "PostResponseDTO{" +
                ", message='" + message + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {

        private String message;

        public Builder withMessage(String message)
        {
            this.message=message;
            return this;
        }

        public PostRequestDTO build() {
            return new PostRequestDTO(this);
        }

    }
}

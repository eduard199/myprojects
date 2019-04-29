package com.project.Model.DTO;

public class PostCreationResponseDTO {
    private int id;
    private String message;

    public PostCreationResponseDTO(Builder builder)
    {
        this.id=builder.id;
        this.message=builder.message;
    }

    public PostCreationResponseDTO()
    {

    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }


    public void setId(int id) {
        this.id = id;
    }

    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {

        private int id;
        private String message;

        public Builder withId(int id)
        {
            this.id=id;
            return this;
        }

        public Builder withMessage(String message)
        {
            this.message=message;
            return this;
        }
        public PostCreationResponseDTO build() {
            return new PostCreationResponseDTO(this);
        }

    }
}

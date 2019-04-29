package com.project.Model.DTO;

public class UserResponseDTO {
    private final int userId;
    private final String userName;
    private final String firstName;
    private final String lastName;
    private final String email;

    public UserResponseDTO(Builder builder)
    {
        this.userId=builder.userId;
        this.userName=builder.userName;
        this.firstName=builder.firstName;
        this.lastName=builder.lastName;
        this.email=builder.email;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "PostResponseDTO{" +
                "userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {

        private int userId;
        private String userName;
        private String firstName;
        private String lastName;
        private String email;

        public Builder withUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }


        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }
        public Builder withUserId(int userId)
        {
            this.userId=userId;
            return this;
        }
        public UserResponseDTO build() {
            return new UserResponseDTO(this);
        }

    }
}

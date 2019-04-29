package com.project.Model;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {
    private int userId;
    private String userName;
    @NotNull(message = "First name can't be null")
    private String firstName;
    @NotNull(message = "Last name can't be null")
    private String lastName;
    @Email(message = "Email format is wrong")
    private String email;
    @Size(min = 8, message = "Password too short")
    private String password;

    public User(int userId,String userName, String firstName, String lastName, String email, String password) {
        this.userId=userId;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public User() {

    }

    public User(Builder builder) {
        this.userId=builder.userId;
        this.userName = builder.userName;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.password=builder.password;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
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


    public String getPassword() {
        return password;
    }


    @Override
    public String toString() {
        return "PostResponseDTO{" +
                "userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
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
        private String password;

        public Builder withUserId(int userId)
        {
            this.userId=userId;
            return this;
        }
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

        public Builder withPassword(String password)
        {
            this.password=password;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}

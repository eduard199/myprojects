package com.project.Model.DTO;


public class UserRequestDTO {
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public UserRequestDTO(String userName, String firstName, String lastName, String email, String password) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public UserRequestDTO() {
    }

    public UserRequestDTO(Builder builder) {
        this.userName = builder.userName;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.password = builder.password;
    }
    public String getUserName()
    {
        return userName;
    }


    public String getFirstName()
    {
        return firstName;
    }


    public String getLastName()
    {
        return lastName;
    }


    public String getEmail()
    {
        return email;
    }


    public String getPassword()
    {
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

        private String userName;
        private String firstName;
        private String lastName;
        private String email;
        private String password;

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

        public UserRequestDTO build() {
            return new UserRequestDTO(this);
        }
    }
}

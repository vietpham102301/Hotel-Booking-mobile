package com.example.hotelbooking.signin;

import java.time.LocalDateTime;

public class SignInRes {
    private String status;
    private String message;
    private UserData data;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public UserData getData() {
        return data;
    }

    public static class UserData {
        private int id;
        private String firstname;
        private String lastname;
        private String birthday;
        private String email;
        private String phone;
        private String gender;
        private String avatar;
        private String roleId;
        private String roleName;

        public int getId() {
            return id;
        }

        public String getFirstname() {
            return firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public String getBirthday() {
            return birthday;
        }

        public String getEmail() {
            return email;
        }

        public String getPhone() {
            return phone;
        }

        public String getGender() {
            return gender;
        }

        public String getAvatar() {
            return avatar;
        }

        public String getRoleId() {
            return roleId;
        }

        public String getRoleName() {
            return roleName;
        }
    }
}

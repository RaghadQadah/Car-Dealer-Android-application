package com.example.advancecardealerapp;

import java.util.ArrayList;

public class User {
    public static ArrayList<User> allUsers = new ArrayList<>();

    boolean isAdmin;
    String email;
    String gender;
    String firstName;
    String lastName;
    String password;
    String phone;

//
    public User(String email, String firstName, String lastName, String password,String gender, String phone,boolean isAdmin) {
        this.isAdmin = isAdmin;
        this.email = email;
        this.gender=gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phone = phone;
    }

    public User(){ }

    public boolean isAdmin() {
        return isAdmin;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "isAdmin=" + isAdmin +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

package com.example.movie.model;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

    private int id;
    private String fullName;
    private String phoneNumber;
    private String mail;
    private String password;


    public User(int id, String fullName, String phoneNumber, String mail) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
    }

    public User(int id, String fullName, String phoneNumber, String mail, String password) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "" + id;
    }
}

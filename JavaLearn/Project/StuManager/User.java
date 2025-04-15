package com.ngbarsenal.test2.StuManager;

import java.util.Scanner;
import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private String idCardNum;
    private String phoneNum;

    public User() {

    }

    public User(String username, String password, String idCardNum, String phoneNum) {
        this.username = username;
        this.password = password;
        this.idCardNum = idCardNum;
        this.phoneNum = phoneNum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getidCardNum() {
        return idCardNum;
    }

    public void setidCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }

    public String getphoneNum() {
        return phoneNum;
    }

    public void setphoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}

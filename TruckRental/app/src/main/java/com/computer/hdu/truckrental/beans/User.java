package com.computer.hdu.truckrental.beans;

/**
 * Created by yjt on 2017/2/4.
 */

public class User {
    private int User_id;
    private String User_phone;
    private int User_level;

    public User(int user_id, String user_phone, int user_level) {
        User_id = user_id;
        User_phone = user_phone;
        User_level = user_level;
    }

    public User(String user_phone) {
        User_phone = user_phone;
    }

    public int getUser_id() {
        return User_id;
    }

    public String getUser_phone() {
        return User_phone;
    }

    public int getUser_level() {
        return User_level;
    }

    public void setUser_phone(String user_phone) {
        User_phone = user_phone;
    }

    public void setUser_level(int user_level) {
        User_level = user_level;
    }

    @Override
    public String toString() {
        return "User{" +
                "User_id=" + User_id +
                ", User_phone='" + User_phone + '\'' +
                ", User_level=" + User_level +
                '}';
    }
}

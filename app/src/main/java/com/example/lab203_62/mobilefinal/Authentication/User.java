package com.example.lab203_62.mobilefinal.Authentication;

import android.provider.BaseColumns;

public class User {

    private String userId;
    private String password;
    private String name;
    private String age;

    // Database --

    public static final String TABLE = "Users";
    public class Column{
        public static final String ID = BaseColumns._ID;
        public static final String USER_ID = "userId";
        public static final String PASSWORD = "password";
        public static final String NAME = "name";
        public static final String AGE = "age";
    }

    // Database --

    public User(String userId, String password, String name, String age){
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.age = age;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}

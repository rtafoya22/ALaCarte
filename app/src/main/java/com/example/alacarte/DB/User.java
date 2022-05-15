package com.example.alacarte.DB;

import androidx.room.ColumnInfo;
import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.alacarte.DB.AppDataBase;
import com.example.alacarte.productDB.Product;

@Entity(tableName = AppDataBase.USER_TABLE)
public class User {

    @PrimaryKey(autoGenerate = true)
    private Integer mId;

    @ColumnInfo(name = "mUsername")
    private String mUsername;

    @ColumnInfo(name = "mPassword")
    private String mPassword;

    @ColumnInfo(name = "mName")
    private String mName;

    @ColumnInfo(name = "mIsAdmin")
    private boolean mIsAdmin;


    public User(String username, String password, String name, boolean isAdmin) {
        mUsername = username;
        mPassword = password;
        mName = name;
        mIsAdmin = isAdmin;

    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public boolean isAdmin() {
        return mIsAdmin;
    }

    public void setAdmin(boolean admin) {
        mIsAdmin = admin;
    }

}


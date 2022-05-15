package com.example.alacarte.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDAO {

    @Insert
    void registerUser(User... users);

    @Update
    void updateUser(User... users);

    @Delete
    void deleteUser(User users);

    @Query("SELECT * FROM " + AppDataBase.USER_TABLE + " WHERE mUsername = :username")
    User getUserByUsername(String username);

    @Query("Select * FROM " + AppDataBase.USER_TABLE + " WHERE mId = :userId")
    User getUserById(Integer userId);

    @Query("SELECT * FROM " + AppDataBase.USER_TABLE)
    List<User> getAllUsers();

    @Query("SELECT * FROM " + AppDataBase.USER_TABLE + " WHERE mUsername = :username and mPassword = :password")
    User getUser(String username, String password);

    @Query("SELECT * FROM " + AppDataBase.USER_TABLE + " ORDER BY mUsername")
    List<User> getUsers();

}

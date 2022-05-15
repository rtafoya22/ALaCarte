package com.example.alacarte.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public static String dbName = "ALaCarte.db";
    public static final String USER_TABLE = "user";

    public abstract UserDAO UserDAO();
    private static volatile AppDataBase instance;
    private static final Object LOCK = new Object();

    public static AppDataBase getInstance(Context context){
        if(instance == null){
            synchronized (LOCK){
                if(instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),AppDataBase.class, dbName).build();
                }
            }
        }
        return instance;
    }

}

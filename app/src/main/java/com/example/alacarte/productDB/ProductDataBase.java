package com.example.alacarte.productDB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.alacarte.DB.AppDataBase;
import com.example.alacarte.DB.UserDAO;

@Database(entities = {Product.class}, version = 1)
public abstract class ProductDataBase extends RoomDatabase {
    public static String dbName = "ALaCarte.db";
    public static final String PRODUCT_TABLE = "user";

    public abstract ProductDAO ProductDAO();
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

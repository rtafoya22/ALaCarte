package com.example.alacarte.productDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductDAO {

    @Insert
    void insert(Product... products);

    @Update
    void update(Product... products);

    @Delete
    void delete(Product product);

    @Query("SELECT * FROM " + ProductDataBase.PRODUCT_TABLE + " ORDER BY name")
    List<Product> getProducts();


    @Query("SELECT * FROM "+ ProductDataBase.PRODUCT_TABLE + " WHERE name = :name")
    Product getItemFromName(String name);

}

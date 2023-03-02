package com.example.appfacebook.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.appfacebook.Product;

import java.util.List;

@Dao
public interface ProductDAO {
    @Insert
    void insertProduct(Product product);
    @Query("SELECT * FROM product")
    List<Product> getListProduct();

    @Query("SELECT * FROM product where productName= :productName")
    List<Product> checkProduct(String productName);

    @Update
    void updateProduct(Product product);

    @Delete
    void deleteProduct(Product product);
}

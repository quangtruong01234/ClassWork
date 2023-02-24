package com.example.appfacebook.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.appfacebook.Product;

import java.util.List;

@Dao
public interface ProductDAO {
    @Insert
    void insertProduct(Product product);
    @Query("SELECT * FROM product")
    List<Product> getListProduct();
}

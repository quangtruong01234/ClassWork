package com.example.appfacebook.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.appfacebook.Product;

@Database(entities = {Product.class}, version = 1)
public abstract class ProductDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "goods.db";
    private  static ProductDatabase instance;

    public static synchronized ProductDatabase getInstance(Context context){
        if(instance ==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),ProductDatabase.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();

        }
        return instance;
    }

    public abstract ProductDAO productDAO();

}

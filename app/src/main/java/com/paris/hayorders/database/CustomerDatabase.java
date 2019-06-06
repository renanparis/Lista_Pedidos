package com.paris.hayorders.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.paris.hayorders.dao.CustomerDao;
import com.paris.hayorders.model.Customers;

@Database(entities = {Customers.class}, version = 1)
public abstract class CustomerDatabase extends RoomDatabase {

    public static final String NAME_DATABASE = "customer_database";
    private static CustomerDatabase instance;

    public abstract CustomerDao customerDao();

    public static CustomerDatabase getInstance(Context context) {
        if (instance == null){

            instance = Room.databaseBuilder(context.getApplicationContext(),
                    CustomerDatabase.class, NAME_DATABASE).build();
        }

        return instance;
    }

}

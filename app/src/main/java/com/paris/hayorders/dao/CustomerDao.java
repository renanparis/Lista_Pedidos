package com.paris.hayorders.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.paris.hayorders.model.Customers;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface CustomerDao {

    @Query("SELECT * FROM customers ORDER BY name COLLATE NOCASE")
    List<Customers> searchAllCustomers();

    @Insert(onConflict = REPLACE)
    void saveCustomer(Customers customer);

    @Update
    void updateCustomer(Customers customer);

    @Delete
    void remove(Customers customer);

    @Query("SELECT * FROM customers WHERE `order` != 0 ORDER BY city COLLATE NOCASE ")
    List<Customers> searchAllOrders();
}

package com.paris.hayorders.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.paris.hayorders.model.Customers;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface CustomerDao {

    @Query("SELECT * FROM customers")
    List<Customers> searchAllCustomers();

    @Insert(onConflict = REPLACE)
    void saveCustomer(Customers customer);
}

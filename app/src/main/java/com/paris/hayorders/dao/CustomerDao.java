package com.paris.hayorders.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.paris.hayorders.model.Customers;

import java.util.List;

@Dao
public interface CustomerDao {

    @Query("SELECT * FROM Customers")
    List<Customers> searchAllCustomers();
}

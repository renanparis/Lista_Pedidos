package com.paris.hayorders.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Customers {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String city;
    private long order;

    public Customers(String name, String city, long order) {
        this.name = name;
        this.city = city;
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getOrder() {
        return order;
    }

    public void setOrder(long order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

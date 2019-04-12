package com.paris.hayorders.model;

import androidx.annotation.NonNull;

public class Customers {

    private String name;
    private String city;

    public Customers(String name, String city) {
        this.name = name;
        this.city = city;
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

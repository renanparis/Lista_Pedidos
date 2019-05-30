package com.paris.hayorders.model;

public class Customers {

    private String name;
    private String city;
    private String order;

    public Customers(String name, String city, String order) {
        this.name = name;
        this.city = city;
        this.order = order;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
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

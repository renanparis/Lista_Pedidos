package com.paris.hayorders.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Customers implements Parcelable, Comparable<Customers> {

    @PrimaryKey(autoGenerate = true)
    private long id = 0;
    private String name;
    private String city;
    private long order;


    public Customers(Parcel in) {
        id = in.readLong();
        name = in.readString();
        city = in.readString();
        order = in.readLong();
    }

    public static final Creator<Customers> CREATOR = new Creator<Customers>() {
        @Override
        public Customers createFromParcel(Parcel in) {
            return new Customers(in);
        }

        @Override
        public Customers[] newArray(int size) {
            return new Customers[size];
        }
    };

    public Customers() {
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
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
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(city);
        dest.writeLong(order);
    }

    @Override
    public int compareTo(Customers customer) {
        return this.name.compareToIgnoreCase(customer.getName());
    }
}

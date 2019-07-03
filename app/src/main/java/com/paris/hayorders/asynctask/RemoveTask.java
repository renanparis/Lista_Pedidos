package com.paris.hayorders.asynctask;

import android.os.AsyncTask;

import com.paris.hayorders.dao.CustomerDao;
import com.paris.hayorders.model.Customers;

import java.util.AbstractCollection;

public class RemoveTask extends AsyncTask<Void, Void, Void> {


    private CustomerDao dao;
    private Customers cutomer;

    public RemoveTask(CustomerDao dao, Customers customer) {
        this.dao = dao;
        this.cutomer = customer;

    }

    @Override
    protected Void doInBackground(Void... voids) {

        dao.remove(cutomer);
        return null;
    }
}

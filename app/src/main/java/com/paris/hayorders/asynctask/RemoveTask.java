package com.paris.hayorders.asynctask;

import android.os.AsyncTask;

import com.paris.hayorders.database.dao.CustomerDao;
import com.paris.hayorders.model.Customers;

public class RemoveTask extends AsyncTask<Void, Void, Void> {


    private CustomerDao dao;
    private Customers customer;

    public RemoveTask(CustomerDao dao, Customers customer) {
        this.dao = dao;
        this.customer = customer;

    }

    @Override
    protected Void doInBackground(Void... voids) {

        dao.remove(customer);
        return null;
    }
}

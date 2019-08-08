package com.paris.hayorders.asynctask;

import android.os.AsyncTask;

import com.paris.hayorders.database.dao.CustomerDao;
import com.paris.hayorders.model.Customers;

public class SaveCustomerTask extends AsyncTask<Void, Void, Void> {

    private CustomerDao dao;
    private Customers customer;


    public SaveCustomerTask(CustomerDao dao, Customers customer) {
        this.dao = dao;
        this.customer = customer;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        dao.saveCustomer(customer);

        return null;
    }

}

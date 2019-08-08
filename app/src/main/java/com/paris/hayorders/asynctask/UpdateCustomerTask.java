package com.paris.hayorders.asynctask;

import android.os.AsyncTask;

import com.paris.hayorders.database.dao.CustomerDao;
import com.paris.hayorders.model.Customers;

public class UpdateCustomerTask extends AsyncTask <Void, Void, Void>{


    private CustomerDao dao;
    private Customers customer;

    public UpdateCustomerTask(CustomerDao dao, Customers customer) {
        this.dao = dao;
        this.customer = customer;
    }

    @Override
    protected Void doInBackground(Void...voids) {

        dao.updateCustomer(customer);
        return null;
    }
}

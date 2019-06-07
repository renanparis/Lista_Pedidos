package com.paris.hayorders.asynctask;

import android.widget.Toast;

import com.paris.hayorders.activity.CustomerForm;
import com.paris.hayorders.dao.CustomerDao;
import com.paris.hayorders.model.Customers;

public class SaveCustomer extends BaseAsynctask {

    private CustomerDao dao;
    private Customers customer;


    public SaveCustomer(CustomerDao dao, Customers customer, EndsListener listener) {
        super(listener);
        this.dao = dao;
        this.customer = customer;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        dao.saveCustomer(customer);


        return null;
    }

}

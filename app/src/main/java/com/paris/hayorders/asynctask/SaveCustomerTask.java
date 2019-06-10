package com.paris.hayorders.asynctask;

import com.paris.hayorders.dao.CustomerDao;
import com.paris.hayorders.model.Customers;

public class SaveCustomerTask extends BaseAsynctask {

    private CustomerDao dao;
    private Customers customer;


    public SaveCustomerTask(CustomerDao dao, Customers customer, EndsListener listener) {
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

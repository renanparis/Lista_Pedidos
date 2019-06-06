package com.paris.hayorders.asynctask;

import android.os.AsyncTask;

import com.paris.hayorders.dao.CustomerDao;
import com.paris.hayorders.recyclerview.CustomersRecyclerAdapter;

public class SearchAllCustomers extends AsyncTask<Void, Void, Void> {

    private CustomerDao dao;
    private CustomersRecyclerAdapter adapter;

    public SearchAllCustomers(CustomerDao dao, CustomersRecyclerAdapter adapter) {
        this.dao = dao;
        this.adapter = adapter;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        return dao.searchAllCustomers();
    }
}

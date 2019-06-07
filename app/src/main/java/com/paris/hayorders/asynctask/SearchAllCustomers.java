package com.paris.hayorders.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import androidx.recyclerview.widget.RecyclerView;

import com.paris.hayorders.dao.CustomerDao;
import com.paris.hayorders.model.Customers;
import com.paris.hayorders.recyclerview.CustomersRecyclerAdapter;

import java.util.List;

public class SearchAllCustomers extends AsyncTask<Void, Void, List<Customers>> {

    private CustomerDao dao;

    public SearchAllCustomers(CustomerDao dao) {
        this.dao = dao;

    }

    @Override
    protected List<Customers> doInBackground(Void... voids) {
        return dao.searchAllCustomers();
    }

    @Override
    protected void onPostExecute(List<Customers> customers) {


    }


}

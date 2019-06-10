package com.paris.hayorders.asynctask;

import android.os.AsyncTask;

import com.paris.hayorders.dao.CustomerDao;
import com.paris.hayorders.model.Customers;

import java.util.List;

public class SearchAllCustomers extends AsyncTask<Void, Void, List<Customers>> {

    private CustomerDao dao;
    private final ListCustomersFoundListener listener;

    public SearchAllCustomers(CustomerDao dao, ListCustomersFoundListener listener) {
        this.dao = dao;

        this.listener = listener;
    }

    @Override
    protected List<Customers> doInBackground(Void... voids) {
        return dao.searchAllCustomers();
    }

    @Override
    protected void onPostExecute(List<Customers> customers) {
        super.onPostExecute(customers);
        listener.ListFound(customers);



    }

    public interface ListCustomersFoundListener{

        void ListFound(List<Customers> customers);
    }


}

package com.paris.hayorders.asynctask;

import android.os.AsyncTask;

import com.paris.hayorders.dao.CustomerDao;
import com.paris.hayorders.model.Customers;

import java.util.List;

public class SearchAllOrders extends AsyncTask<Void, Void, List<Customers>> {

    private CustomerDao dao;
    private FinishSearchOrders listener;

    public SearchAllOrders(CustomerDao dao, FinishSearchOrders listener) {
        this.dao = dao;
        this.listener = listener;
    }

    @Override
    protected List<Customers> doInBackground(Void...voids) {
        return dao.searchAllOrders();
    }

    @Override
    protected void onPostExecute(List<Customers> customers) {
        super.onPostExecute(customers);
        listener.listOrdersFound(customers);
    }

    public interface FinishSearchOrders{

        void listOrdersFound(List<Customers> list);
    }
}

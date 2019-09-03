package com.paris.hayorders.asynctask;

import android.os.AsyncTask;

import com.paris.hayorders.database.dao.CustomerDao;
import com.paris.hayorders.model.Customers;

public class SaveCustomerTask extends AsyncTask<Void, Void, Long> {

    private CustomerDao dao;
    private Customers customer;
    private FinishListenerSaveTask listener;


    public SaveCustomerTask(CustomerDao dao, Customers customer, FinishListenerSaveTask listener) {
        this.dao = dao;
        this.customer = customer;
        this.listener = listener;
    }

    @Override
    protected Long doInBackground(Void... voids) {

        return dao.saveCustomer(customer);

    }

    @Override
    protected void onPostExecute(Long id) {
        super.onPostExecute(id);
        listener.whenItEnds(id);


    }

    public interface FinishListenerSaveTask{

        void whenItEnds(Long id);
    }
}

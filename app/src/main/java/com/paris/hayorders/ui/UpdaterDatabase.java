package com.paris.hayorders.ui;

import com.paris.hayorders.asynctask.RemoveTask;
import com.paris.hayorders.asynctask.SaveCustomerTask;
import com.paris.hayorders.asynctask.SearchAllCustomers;
import com.paris.hayorders.asynctask.UpdateCustomerTask;
import com.paris.hayorders.database.dao.CustomerDao;
import com.paris.hayorders.model.Customers;
import com.paris.hayorders.ui.recyclerview.CustomersRecyclerAdapter;

public class UpdaterDatabase {


    private CustomerDao dao;
    private Customers customer;
    private CustomersRecyclerAdapter adapter;

    public UpdaterDatabase(CustomerDao dao, Customers customer, CustomersRecyclerAdapter adapter) {
        this.dao = dao;
        this.customer = customer;
        this.adapter = adapter;
    }

    public void deleteCustomer(int position) {
        new RemoveTask(dao, customer).execute();
        adapter.remove(position);


    }

    public void saveCustomer() {
        new SaveCustomerTask(dao, customer).execute();
        adapter.insertCustomer(customer);
    }

    public void updateCustomer(int position) {
        new UpdateCustomerTask(dao, customer).execute();
        adapter.update(customer, position);
    }

    public void deleteAllOrders() {
        new SearchAllCustomers(dao, customers -> {
            for (Customers customer :
                    customers) {
                customer.setOrder(0);
                new UpdateCustomerTask(dao, customer).execute();
                adapter.deleteOrder(customer);
            }
        }).execute();
    }

    public void saveOrder(int position) {

        new UpdateCustomerTask(dao, customer).execute();
        adapter.insertOrder(customer, position);
    }
}

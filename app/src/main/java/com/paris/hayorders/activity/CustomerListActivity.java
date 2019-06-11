package com.paris.hayorders.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.paris.hayorders.R;
import com.paris.hayorders.asynctask.SaveCustomerTask;
import com.paris.hayorders.asynctask.SearchAllCustomers;
import com.paris.hayorders.dao.CustomerDao;
import com.paris.hayorders.database.CustomerDatabase;
import com.paris.hayorders.model.Customers;
import com.paris.hayorders.recyclerview.CustomersRecyclerAdapter;

import java.util.List;

public class CustomerListActivity extends AppCompatActivity {

    private CustomerDao dao;
    private CustomersRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);
        CustomerDatabase db = CustomerDatabase.getInstance(this);
        dao = db.customerDao();
        configList();
        configFabAddCustomer();

    }


    private void configRecyclerAdapter(List<Customers> customers) {
        RecyclerView customerList = findViewById(R.id.customer_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        customerList.setLayoutManager(layoutManager);
        adapter = new CustomersRecyclerAdapter(customers, this);
        customerList.setAdapter(adapter);
    }

    private void configList() {

        new SearchAllCustomers(dao, new SearchAllCustomers.ListCustomersFoundListener() {
            @Override
            public void ListFound(List<Customers> customers) {

                configRecyclerAdapter(customers);

            }
        }).execute();
    }

    private void configFabAddCustomer() {
        FloatingActionButton addCutomer = findViewById(R.id.fab_add_cutomers);
        addCutomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToForm = new Intent(CustomerListActivity.this, CustomerForm.class);
                startActivityForResult(goToForm, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data.hasExtra("insert_customer")){

            Customers customerReceived = data.getParcelableExtra("insert_customer");

            new SaveCustomerTask(dao, customerReceived, this::finish).execute();

            adapter.insertCustomer(customerReceived);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}



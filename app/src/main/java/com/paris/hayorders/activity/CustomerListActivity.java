package com.paris.hayorders.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.paris.hayorders.R;
import com.paris.hayorders.asynctask.SearchAllCustomers;
import com.paris.hayorders.dao.CustomerDao;
import com.paris.hayorders.database.CustomerDatabase;
import com.paris.hayorders.model.Customers;
import com.paris.hayorders.recyclerview.CustomersRecyclerAdapter;

import java.util.List;

public class CustomerListActivity extends AppCompatActivity {

    private RecyclerView customerList;
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

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void configRecyclerAdapter(List<Customers> customers) {
        customerList = findViewById(R.id.customer_list);
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
                startActivity(goToForm);
            }
        });
    }

}



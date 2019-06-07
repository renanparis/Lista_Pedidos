package com.paris.hayorders.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.paris.hayorders.R;
import com.paris.hayorders.dao.CustomerDao;
import com.paris.hayorders.database.CustomerDatabase;
import com.paris.hayorders.model.Customers;
import com.paris.hayorders.recyclerview.CustomersRecyclerAdapter;

import java.util.List;

public class CustomerListActivity extends AppCompatActivity {

    private RecyclerView customerList;
    private CustomerDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);
        CustomerDatabase db = CustomerDatabase.getInstance(this);
        dao = db.customerDao();
        configFabAddCustomer();


    }

    @Override
    protected void onResume() {
        super.onResume();
        configList();


    }

    private void configList() {

        List<Customers> customers = dao.searchAllCustomers();
        configRecyclerView(customers);

    }

    private void configRecyclerView(List<Customers> customers) {
        customerList = findViewById(R.id.customer_list);
        customerList.setAdapter(new CustomersRecyclerAdapter(customers, this));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        customerList.setLayoutManager(layoutManager);
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

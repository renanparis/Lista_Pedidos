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
import com.paris.hayorders.recyclerview.CustomersRecyclerAdapter;

public class CustomerListActivity extends AppCompatActivity {

    private RecyclerView customerList;
    private CustomerDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);
        dao = CustomerDatabase.getInstance(this).customerDao();
        configList();
        configFabAddCustomer();


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void configList() {

        new SearchAllCustomers(dao).execute();

        configRecyclerAdapter();

    }

    private void configRecyclerAdapter() {
        customerList = findViewById(R.id.customer_list);
        customerList.setAdapter(new CustomersRecyclerAdapter(this ));
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

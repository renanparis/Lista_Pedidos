package com.paris.hayorders.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.paris.hayorders.R;
import com.paris.hayorders.asynctask.SaveCustomerTask;
import com.paris.hayorders.asynctask.SearchAllCustomers;
import com.paris.hayorders.asynctask.UpdateCustomerTask;
import com.paris.hayorders.dao.CustomerDao;
import com.paris.hayorders.database.CustomerDatabase;
import com.paris.hayorders.model.Customers;
import com.paris.hayorders.recyclerview.CustomersRecyclerAdapter;

import java.util.List;

public class CustomerListActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_INSERT_CUSTOMER = 1;
    public static final int REQUEST_CODE_UPDATE_CUSTOMER = 2;
    public static final String KEY_UPDATE_CUSTOMER = "update_customer";
    public static final String KEY_POSITION = "position";
    private CustomerDao dao;
    private CustomersRecyclerAdapter adapter;
    private Customers customer;

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
        configContextMenuListener();
    }

    private void configContextMenuListener() {
        adapter.setOnMenuItemClickListener((item, customer, position) -> {

            switch (item.getItemId()) {
                case 1:
                    Intent goToUpdateCustomerForm = new Intent(this, CustomerForm.class);
                    goToUpdateCustomerForm.putExtra(KEY_UPDATE_CUSTOMER, customer);
                    goToUpdateCustomerForm.putExtra(KEY_POSITION, position);
                    startActivityForResult(goToUpdateCustomerForm, REQUEST_CODE_UPDATE_CUSTOMER);
            }

        });
    }

    private void configList() {

        new SearchAllCustomers(dao, this::configRecyclerAdapter).execute();
    }

    private void configFabAddCustomer() {
        FloatingActionButton addCustomer = findViewById(R.id.fab_add_cutomers);
        addCustomer.setOnClickListener(v -> {
            Intent goToForm = new Intent(CustomerListActivity.this, CustomerForm.class);
            startActivityForResult(goToForm, REQUEST_CODE_INSERT_CUSTOMER);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == REQUEST_CODE_INSERT_CUSTOMER && resultCode == Activity.RESULT_OK && data.hasExtra("result_form_customer")) {

            customer = data.getParcelableExtra("result_form_customer");

            new SaveCustomerTask(dao, customer).execute();

            adapter.insertCustomer(customer);
            Toast.makeText(CustomerListActivity.this,
                    "Cliente criado com sucesso" + customer.getId(), Toast.LENGTH_SHORT).show();

        }

        if (requestCode == REQUEST_CODE_UPDATE_CUSTOMER && resultCode == Activity.RESULT_OK && data.hasExtra("result_form_customer")) {


            Customers updateCustomerReceived = data.getParcelableExtra(KEY_UPDATE_CUSTOMER);
            int positionReceived = data.getIntExtra(KEY_POSITION, -1);
            if (positionReceived > -1){

                new UpdateCustomerTask(dao, updateCustomerReceived).execute();
                adapter.update(updateCustomerReceived, positionReceived);
                Toast.makeText(this, "Cliente alterado com sucesso", Toast.LENGTH_SHORT).show();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}



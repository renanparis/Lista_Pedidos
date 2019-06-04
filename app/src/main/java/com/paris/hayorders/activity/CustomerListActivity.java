package com.paris.hayorders.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.paris.hayorders.R;
import com.paris.hayorders.model.Customers;
import com.paris.hayorders.recyclerview.CustomersRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class CustomerListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);

        List<Customers> customers = new ArrayList<>();
        Customers customer1 = new Customers("Juliana", "Pirassununga", 55);
        Customers customer2 = new Customers("José", "Porto Ferreira", 100);
        customers.add(customer1);
        customers.add(customer2);

        RecyclerView customerList = findViewById(R.id.customer_list);
        customerList.setAdapter( new CustomersRecyclerAdapter(customers, this));
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(this);

        customerList.setLayoutManager(layoutManager);

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

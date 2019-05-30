package com.paris.hayorders;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Layout;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

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
        Customers customer1 = new Customers("Juliana", "Pirassununga", "55");
        Customers customer2 = new Customers("José", "Porto Ferreira", "100");
        customers.add(customer1);
        customers.add(customer2);

        RecyclerView customerList = findViewById(R.id.customer_list);
        customerList.setAdapter( new CustomersRecyclerAdapter(customers, this));
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(this);

        customerList.setLayoutManager(layoutManager);




    }




}

package com.paris.hayorders;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.paris.hayorders.model.Customers;

import java.util.ArrayList;
import java.util.List;

public class CustomerListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);

        List<Customers> customers = new ArrayList<>();
        Customers customer1 = new Customers("Valla", "Diablo");
        Customers customer2 = new Customers("Furion", "WarCraft");
        customers.add(customer1);
        customers.add(customer2);

        ListView customerList = findViewById(R.id.customer_list);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, customers);
        customerList.setAdapter(adapter);




    }




}

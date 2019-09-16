package com.paris.hayorders.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.paris.hayorders.R;
import com.paris.hayorders.asynctask.SearchAllOrders;
import com.paris.hayorders.database.CustomerDatabase;
import com.paris.hayorders.database.dao.CustomerDao;
import com.paris.hayorders.model.Customers;
import com.paris.hayorders.ui.recyclerview.ListOrderRecyclerAdapter;
import com.paris.hayorders.ui.recyclerview.helper.callback.ListOrdersCallback;

import java.util.List;

public class ListOrdersActivity extends AppCompatActivity {

    public static final String TITLE_LIST_ORDERS = "Lista de pedidos";
    private CustomerDao dao;
    private ListOrderRecyclerAdapter adapter;
    private TextView totalOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_orders);
        setTitle(TITLE_LIST_ORDERS);
        addAnUpAction();
        CustomerDatabase db = CustomerDatabase.getInstance(this);
        totalOrders = findViewById(R.id.sum_orders);
        dao = db.customerDao();
        configListOrders();

    }



    private void addAnUpAction() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home){
            goToCustomerLIst();
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToCustomerLIst() {
        startActivity(new Intent(this, CustomerListActivity.class));
        finishAffinity();
    }

    private void configListOrders() {
        new SearchAllOrders(dao, this::configAdapter).execute();
    }

    private void configAdapter(List<Customers> list) {
        RecyclerView recycler = findViewById(R.id.recycler_order_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListOrdersActivity.this);
        recycler.setLayoutManager(layoutManager);
        adapter = new ListOrderRecyclerAdapter(list, ListOrdersActivity.this);
        recycler.setAdapter(adapter);
        configItemTouchHelper(recycler);
        configFieldTotalOrder(list);
        updateTotalOrderWhenCustomerRemoved();

    }

    private void updateTotalOrderWhenCustomerRemoved() {
        adapter.setListener(customers -> configFieldTotalOrder(customers));
    }

    private void configFieldTotalOrder(List<Customers> list) {

        int sumOrders = 0;
        for (Customers customer:
                list) {
            sumOrders += customer.getOrder();
        }
        totalOrders.setText(String.valueOf(sumOrders));
    }


    private void configItemTouchHelper(RecyclerView recycler) {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ListOrdersCallback(adapter));
        itemTouchHelper.attachToRecyclerView(recycler);
    }
}



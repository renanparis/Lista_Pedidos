package com.paris.hayorders.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.paris.hayorders.R;
import com.paris.hayorders.asynctask.SearchAllCustomers;
import com.paris.hayorders.database.CustomerDatabase;
import com.paris.hayorders.database.dao.CustomerDao;
import com.paris.hayorders.model.Customers;
import com.paris.hayorders.ui.Dialog.DeleteCustomerDialog;
import com.paris.hayorders.ui.Dialog.InsertOrderDialog;
import com.paris.hayorders.ui.UpdaterDatabase;
import com.paris.hayorders.ui.recyclerview.CustomersRecyclerAdapter;
import com.paris.hayorders.ui.recyclerview.listener.OnItemClickListener;

import java.util.List;

import static com.paris.hayorders.ui.activity.ConstantsActivity.INVALID_VALUE;
import static com.paris.hayorders.ui.activity.ConstantsActivity.KEY_POSITION;
import static com.paris.hayorders.ui.activity.ConstantsActivity.KEY_RESULT_FORM;
import static com.paris.hayorders.ui.activity.ConstantsActivity.KEY_UPDATE_CUSTOMER;
import static com.paris.hayorders.ui.ConstantsContextMenu.DELETE_ID;
import static com.paris.hayorders.ui.ConstantsContextMenu.EDIT_ID;

public class CustomerListActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_INSERT_CUSTOMER = 1;
    private static final int REQUEST_CODE_UPDATE_CUSTOMER = 2;
    private static final String TITLE_ACTIVITY = "Lista de clientes";
    public static final String MESSAGE_UPDATE_SUCCESSFULLY = "Cliente alterado com sucesso";
    public static final String MESSAGE_CUSTOMER_CREATED_SUCCESSFULLY = "Cliente criado com sucesso";
    public static final String MESSAGE_DELETE_SUCCESSFULLY = "Cliente deletado com sucesso";
    private CustomerDao dao;
    private CustomersRecyclerAdapter adapter;
    private Customers customer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);
        setTitle(TITLE_ACTIVITY);
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
        configItemClickListener();
    }

    private void configItemClickListener() {
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(Customers customer, int position) {
                showInsertOrderDialog(customer, position);
            }
        });
    }

    private void showInsertOrderDialog(Customers customer, int position) {
        InsertOrderDialog dialog = new InsertOrderDialog(this, new InsertOrderDialog.InputOrderListener() {
            @Override
            public void InputOrder(String input) {
                saveInputOrder(input, customer, position);
            }
        });

        dialog.show();
    }

    private void saveInputOrder(String input, Customers customer, int position) {
        if (input.equals("")) {
            input = String.valueOf(0);
        }
        customer.setOrder(Long.parseLong(input));
        saveOrder(customer, position);
    }

    private void saveOrder(Customers customer, int position) {
        new UpdaterDatabase(dao, customer, adapter).saveOrder(position);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_customers_activity_options, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {

            case R.id.menu_delete_all_orders:
                deleteAllOrders();
                break;

            case R.id.menu_list_order:
                goToListOrders();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void goToListOrders() {
        Intent goToListOrders = new Intent(this, ListOrdersActivity.class);
        startActivity(goToListOrders);
    }

    private void deleteAllOrders() {
        new UpdaterDatabase(dao, customer, adapter).deleteAllOrders();
    }

    private void configContextMenuListener() {
        adapter.setOnMenuItemClickListener((item, customer, position) -> {

            switch (item.getItemId()) {
                case EDIT_ID:
                    goToUpdateForm(customer, position);
                    break;

                case DELETE_ID:
                    showDeleteAlertDialog(customer, position);
            }

        });
    }

    private void goToUpdateForm(Customers customer, int position) {
        Intent goToUpdateCustomerForm = new Intent(this, CustomerForm.class);
        goToUpdateCustomerForm.putExtra(KEY_UPDATE_CUSTOMER, customer);
        goToUpdateCustomerForm.putExtra(KEY_POSITION, position);
        startActivityForResult(goToUpdateCustomerForm, REQUEST_CODE_UPDATE_CUSTOMER);
    }

    private void showDeleteAlertDialog(Customers customer, int position) {
        DeleteCustomerDialog dialog =
                new DeleteCustomerDialog(this, customer, customerReceived ->
                        CustomerListActivity.this.deleteCustomer(customer, position));

        dialog.show();
    }

    private void deleteCustomer(Customers customer, int position) {
        new UpdaterDatabase(dao, customer, adapter).deleteCustomer(position);
        messageCustomerDeletedSuccessfully();
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

        assert data != null;
        if (requestCode == REQUEST_CODE_INSERT_CUSTOMER && resultCode == Activity.RESULT_OK && data.hasExtra(KEY_RESULT_FORM)) {

            customer = data.getParcelableExtra(KEY_RESULT_FORM);
            saveCustomer();
        }

        if (requestCode == REQUEST_CODE_UPDATE_CUSTOMER && resultCode == Activity.RESULT_OK && data.hasExtra(KEY_RESULT_FORM)) {

            customer = data.getParcelableExtra(KEY_RESULT_FORM);
            int positionReceived = data.getIntExtra(KEY_POSITION, INVALID_VALUE);
            if (positionReceived > INVALID_VALUE) {

                updateCustomer(positionReceived);
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void updateCustomer(int positionReceived) {
        new UpdaterDatabase(dao, customer, adapter).updateCustomer(positionReceived);
        messageCustomerUpdateSuccessfully();
    }

    private void messageCustomerUpdateSuccessfully() {
        Toast.makeText(this, MESSAGE_UPDATE_SUCCESSFULLY, Toast.LENGTH_SHORT).show();
    }

    private void saveCustomer() {
        new UpdaterDatabase(dao, customer, adapter).saveCustomer();
        messageCustomerSavedSuccessfully();
    }

    private void messageCustomerSavedSuccessfully() {
        Toast.makeText(CustomerListActivity.this,
                MESSAGE_CUSTOMER_CREATED_SUCCESSFULLY, Toast.LENGTH_SHORT).show();
    }

    private void messageCustomerDeletedSuccessfully(){
        Toast.makeText(this, MESSAGE_DELETE_SUCCESSFULLY,
                Toast.LENGTH_SHORT).show();
    }

}



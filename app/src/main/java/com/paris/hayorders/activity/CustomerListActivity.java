package com.paris.hayorders.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.paris.hayorders.R;
import com.paris.hayorders.activity.Fragments.DialogInsertOrderFragment;
import com.paris.hayorders.asynctask.RemoveTask;
import com.paris.hayorders.asynctask.SaveCustomerTask;
import com.paris.hayorders.asynctask.SearchAllCustomers;
import com.paris.hayorders.asynctask.UpdateCustomerTask;
import com.paris.hayorders.dao.CustomerDao;
import com.paris.hayorders.database.CustomerDatabase;
import com.paris.hayorders.model.Customers;
import com.paris.hayorders.recyclerview.CustomersRecyclerAdapter;

import java.util.List;

import static com.paris.hayorders.activity.ConstantsActivity.INVALID_VALUE;
import static com.paris.hayorders.activity.ConstantsActivity.KEY_POSITION;
import static com.paris.hayorders.activity.ConstantsActivity.KEY_RESULT_FORM;
import static com.paris.hayorders.activity.ConstantsActivity.KEY_UPDATE_CUSTOMER;
import static com.paris.hayorders.recyclerview.ConstantsContextMenu.DELETE_ID;
import static com.paris.hayorders.recyclerview.ConstantsContextMenu.EDIT_ID;

public class CustomerListActivity extends AppCompatActivity implements DialogInsertOrderFragment.InputOrderListener {

    public static final int REQUEST_CODE_INSERT_CUSTOMER = 1;
    public static final int REQUEST_CODE_UPDATE_CUSTOMER = 2;
    public static final String INSERT_ORDER_DIALOG = "InsertOrderDialog";
    public static final String TITLE_ACTIVITY = "Lista de clientes";
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

    @Override
    public void sendInputOrder(String input) {

        if (input.equals("")) {
            input = String.valueOf(0);
        }
        customer.setOrder(Long.parseLong(input));
        new UpdateCustomerTask(dao, customer).execute();
        adapter.insertOrder(customer);


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
        adapter.setOnItemClickListener(customer -> {
            showInsertOrderDialog();
            getCustomerClicked(customer);
        });
    }

    private void getCustomerClicked(Customers customerClicked) {

        customer = customerClicked;

    }

    private void showInsertOrderDialog() {
        DialogFragment dialogFragment = new DialogInsertOrderFragment();
        dialogFragment.show(getSupportFragmentManager(), INSERT_ORDER_DIALOG);

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
        new SearchAllCustomers(dao, customers -> {
            for (Customers customer :
                    customers) {
                customer.setOrder(0);
                new UpdateCustomerTask(dao, customer).execute();
                adapter.insertOrder(customer);
            }
        }).execute();
    }

    private void configContextMenuListener() {
        adapter.setOnMenuItemClickListener((item, customer, position) -> {

            switch (item.getItemId()) {
                case EDIT_ID:
                    goToUpdateForm(customer, position);
                    break;

                case DELETE_ID:
                    showAlertDialog(customer, position);
            }

        });
    }

    private void goToUpdateForm(Customers customer, int position) {
        Intent goToUpdateCustomerForm = new Intent(this, CustomerForm.class);
        goToUpdateCustomerForm.putExtra(KEY_UPDATE_CUSTOMER, customer);
        goToUpdateCustomerForm.putExtra(KEY_POSITION, position);
        startActivityForResult(goToUpdateCustomerForm, REQUEST_CODE_UPDATE_CUSTOMER);
    }

    private void showAlertDialog(Customers customer, int position) {
        new AlertDialog.Builder(this).setTitle("Deletar Cliente")
                .setMessage("Deseja deletar cliente").setPositiveButton("Sim", (dialog, which) -> {
            adapter.remove(position);
            new RemoveTask(dao, customer).execute();
            Toast.makeText(CustomerListActivity.this, "Cliente deletado com sucesso",
                    Toast.LENGTH_SHORT).show();
        }).setNegativeButton("Não", (dialog, which) -> dialog.dismiss()).show();
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

            new SaveCustomerTask(dao, customer).execute();

            adapter.insertCustomer(customer);
            Toast.makeText(CustomerListActivity.this,
                    "Cliente criado com sucesso", Toast.LENGTH_SHORT).show();

        }

        if (requestCode == REQUEST_CODE_UPDATE_CUSTOMER && resultCode == Activity.RESULT_OK && data.hasExtra(KEY_RESULT_FORM)) {


            customer = data.getParcelableExtra(KEY_RESULT_FORM);
            int positionReceived = data.getIntExtra(KEY_POSITION, INVALID_VALUE);
            if (positionReceived > INVALID_VALUE) {

                new UpdateCustomerTask(dao, customer).execute();
                adapter.update(customer, positionReceived);
                Toast.makeText(this, "Cliente alterado com sucesso", Toast.LENGTH_SHORT).show();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}



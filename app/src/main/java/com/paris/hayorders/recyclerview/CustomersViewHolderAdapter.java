package com.paris.hayorders.recyclerview;

import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.paris.hayorders.R;
import com.paris.hayorders.model.Customers;
import com.paris.hayorders.recyclerview.listener.OnMenuItemClickListener;

class CustomersViewHolderAdapter extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
    private static final String EDIT = "Editar";
    private static final int DELETE_ID = 2;
    private static final int EDIT_ID = 1;
    public static final String DELETE = "Deletar";
    public static final int EDIT_ORDER = 1;
    public static final int DELETE_ORDER = 2;
    private final TextView name;
    private final TextView city;
    private final TextView order;
    private OnMenuItemClickListener onMenuItemClickListener;
    private Customers customer;

    CustomersViewHolderAdapter(@NonNull View itemView, OnMenuItemClickListener onMenuItemClickListener) {
        super(itemView);
        name = itemView.findViewById(R.id.item_list_name);
        city = itemView.findViewById(R.id.item_list_city);
        order = itemView.findViewById(R.id.item_list_order);
        this.onMenuItemClickListener = onMenuItemClickListener;
        itemView.setOnCreateContextMenuListener(this);

    }

    void setValues(Customers customer) {

        this.customer = customer;

        name.setText(customer.getName());
        city.setText(customer.getCity());
        if (customer.getOrder() != 0) {
            order.setText(String.valueOf(customer.getOrder()));

        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem edit = menu.add(Menu.NONE, EDIT_ID, EDIT_ORDER, EDIT);
        MenuItem delete = menu.add(Menu.NONE, DELETE_ID, DELETE_ORDER, DELETE);
        delete.setOnMenuItemClickListener(item -> {
            onMenuItemClickListener.OnMenuItemClickListener(item, customer, getAdapterPosition());
            return false;
        });

        edit.setOnMenuItemClickListener(item -> {
            onMenuItemClickListener.OnMenuItemClickListener(item, customer, getAdapterPosition());
            return false;
        });

    }
}

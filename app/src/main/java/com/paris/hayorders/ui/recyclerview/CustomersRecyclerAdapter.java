package com.paris.hayorders.ui.recyclerview;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.paris.hayorders.R;
import com.paris.hayorders.model.Customers;
import com.paris.hayorders.ui.recyclerview.listener.OnItemClickListener;
import com.paris.hayorders.ui.recyclerview.listener.OnMenuItemClickListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.paris.hayorders.ui.ConstantsContextMenu.DELETE_ID;
import static com.paris.hayorders.ui.ConstantsContextMenu.EDIT_ID;

public class CustomersRecyclerAdapter extends RecyclerView.Adapter<CustomersRecyclerAdapter.CustomersViewHolderAdapter> {

    private List<Customers> customers;
    private Context context;
    private OnMenuItemClickListener onMenuItemClickListener;
    private OnItemClickListener listener;


    public CustomersRecyclerAdapter(List<Customers> customers, Context context) {
        this.context = context;
        this.customers = customers;
        setHasStableIds(true);
    }

    @Override
    public long getItemId(int position) {
        return customers.get(position).getId();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.listener = onItemClickListener;
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.onMenuItemClickListener = onMenuItemClickListener;
    }

    @NonNull
    @Override
    public CustomersViewHolderAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_list_customers, parent, false);

        return new CustomersViewHolderAdapter(view, onMenuItemClickListener, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomersViewHolderAdapter holder, int position) {

        Customers customer = customers.get(position);
        holder.setValues(customer);
    }


    @Override
    public int getItemCount() {
        return customers.size();
    }


    public void insertCustomer(Customers customer) {
        customers.add(customer);
        sortListByName();
        notifyDataSetChanged();
    }

    public void update(Customers customer, int position) {
        customers.set(position, customer);
        notifyItemChanged(position);
        sortListByName();
    }

    public void remove(int position) {
        customers.remove(position);
        notifyItemRemoved(position);
    }

    private void sortListByName() {
        Collections.sort(customers);
    }

    public void insertOrder(Customers customer, int position) {

        customers.set(position, customer);
        notifyItemChanged(position);

    }

    public void deleteOrder(Customers customer) {

        int position;

        for (int i = 0; i < customers.size(); i++) {
            position = i;
            if (customers.get(position).getId() == customer.getId()) {
                customers.set(position, customer);
                notifyItemChanged(position);
                return;
            }
        }
    }

    public void searchCustomer(String query, List<Customers> customers) {

        this.customers = customers;
        updateListWithSearch(query);
    }

    private void updateListWithSearch(String query) {
        this.customers = searchCustomerInList(query);
        notifyDataSetChanged();
    }

    private List<Customers> searchCustomerInList(String query) {

        List<Customers> search = new ArrayList<>();
        for (Customers customer : this.customers) {
            String name = customer.getName().toLowerCase();
            if (name.contains(query)) {
                search.add(customer);
            }

        }

        return search;
    }


    class CustomersViewHolderAdapter extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        private static final String EDIT = "Editar";
        private static final String DELETE = "Deletar";
        private static final int EDIT_ORDER = 1;
        private static final int DELETE_ORDER = 2;
        static final String EMPTY_FIELD = "";
        private final TextView name;
        private final TextView city;
        private final TextView order;
        private OnMenuItemClickListener onMenuItemClickListener;
        private Customers customer;


        CustomersViewHolderAdapter(@NonNull View itemView, OnMenuItemClickListener onMenuItemClickListener, OnItemClickListener onItemClick) {
            super(itemView);
            name = itemView.findViewById(R.id.item_list_name);
            city = itemView.findViewById(R.id.item_list_city);
            order = itemView.findViewById(R.id.item_list_order);
            this.onMenuItemClickListener = onMenuItemClickListener;
            itemView.setOnCreateContextMenuListener(this);
            itemView.setOnClickListener(v -> onItemClick.onItemClick(customer, getAdapterPosition()));

        }

        void setValues(Customers customer) {

            this.customer = customer;
            name.setText(customer.getName());
            city.setText(customer.getCity());
            if (customer.getOrder() != 0) {
                order.setText(String.valueOf(customer.getOrder()));

            } else {
                order.setText(EMPTY_FIELD);
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


}

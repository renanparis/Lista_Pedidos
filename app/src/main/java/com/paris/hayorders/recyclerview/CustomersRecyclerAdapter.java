package com.paris.hayorders.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.paris.hayorders.R;
import com.paris.hayorders.model.Customers;
import com.paris.hayorders.recyclerview.listener.OnMenuItemClickListener;

import java.util.List;

public class CustomersRecyclerAdapter extends RecyclerView.Adapter<CustomersViewHolderAdapter> {

    private List<Customers> customers;
    private Context context;
    private OnMenuItemClickListener onMenuItemClickListener;

    public CustomersRecyclerAdapter(List<Customers> customers, Context context) {
        this.context = context;
        this.customers = customers;
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.onMenuItemClickListener = onMenuItemClickListener;
    }

    @NonNull
    @Override
    public CustomersViewHolderAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_list_customers, parent, false);

        return new CustomersViewHolderAdapter(view, onMenuItemClickListener);
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
        notifyDataSetChanged();
    }

    public void update(Customers customer, int position) {
        customers.set(position, customer);
        notifyDataSetChanged();
    }
}

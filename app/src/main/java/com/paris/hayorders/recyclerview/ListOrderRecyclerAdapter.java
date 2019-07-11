package com.paris.hayorders.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.paris.hayorders.R;
import com.paris.hayorders.model.Customers;

import java.util.Collections;
import java.util.List;


public class ListOrderRecyclerAdapter extends RecyclerView.Adapter<ListOrderRecyclerAdapter.ListOrdersViewHolder> {

    private List<Customers> listOrders;
    private Context context;

    public ListOrderRecyclerAdapter(List<Customers> listOrders, Context context) {
        this.listOrders = listOrders;
        this.context = context;
    }


    @NonNull
    @Override
    public ListOrderRecyclerAdapter.ListOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_list_orders, parent, false);

        return new ListOrdersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListOrderRecyclerAdapter.ListOrdersViewHolder holder, int position) {

        Customers customer = listOrders.get(position);
        holder.setValues(customer);

    }

    @Override
    public int getItemCount() {
        return listOrders.size();
    }

    public void changePosition(int positionHome, int positionEnd) {

        Collections.swap(listOrders, positionHome, positionEnd);
        notifyItemMoved(positionHome, positionEnd);
    }

    class ListOrdersViewHolder extends RecyclerView.ViewHolder{

        private TextView name;
        private TextView city;
        private TextView order;


        ListOrdersViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.list_order_name);
            city = itemView.findViewById(R.id.list_order_city);
            order = itemView.findViewById(R.id.list_order);
        }

        public void setValues (Customers customer){
            name.setText(customer.getName());
            city.setText(customer.getCity());
            if (customer.getOrder() !=0){
                order.setText(String.valueOf(customer.getOrder()));
            }
        }
    }
}

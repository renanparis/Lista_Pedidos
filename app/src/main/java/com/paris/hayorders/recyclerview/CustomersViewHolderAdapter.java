package com.paris.hayorders.recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.paris.hayorders.R;
import com.paris.hayorders.model.Customers;

class CustomersViewHolderAdapter extends RecyclerView.ViewHolder {
    private final TextView name;
    private final TextView city;
    private final TextView order;

    CustomersViewHolderAdapter(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.item_list_name);
        city = itemView.findViewById(R.id.item_list_city);
        order = itemView.findViewById(R.id.item_list_order);
    }

    void setValues(Customers customer) {

        name.setText(customer.getName());
        city.setText(customer.getCity());
        if (customer.getOrder() != 0) {
            order.setText(String.valueOf(customer.getOrder()));

        }
    }
}

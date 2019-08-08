package com.paris.hayorders.ui.recyclerview.listener;

import android.view.MenuItem;

import com.paris.hayorders.model.Customers;

public interface OnMenuItemClickListener {

    void OnMenuItemClickListener(MenuItem item, Customers customer, int position);
}

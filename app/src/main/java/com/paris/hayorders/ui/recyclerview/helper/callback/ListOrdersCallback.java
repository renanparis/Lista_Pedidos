package com.paris.hayorders.ui.recyclerview.helper.callback;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.paris.hayorders.ui.recyclerview.ListOrderRecyclerAdapter;

public class ListOrdersCallback extends ItemTouchHelper.Callback {

    private ListOrderRecyclerAdapter adapter;

    public ListOrdersCallback(ListOrderRecyclerAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {

        int slideCustomer = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        int dragsCustomer = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT
                | ItemTouchHelper.DOWN | ItemTouchHelper.UP;
        return makeMovementFlags(dragsCustomer, slideCustomer);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        int positionHome = viewHolder.getAdapterPosition();
        int positionEnd = target.getAdapterPosition();

        adapter.changePosition(positionHome, positionEnd);

        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

    }
}

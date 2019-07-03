package com.paris.hayorders.activity.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.paris.hayorders.asynctask.RemoveTask;
import com.paris.hayorders.dao.CustomerDao;
import com.paris.hayorders.model.Customers;

public class DeleteCustomersDialogFragment extends DialogFragment {

    private CustomerDao dao;
    private Customers customer;

    public DeleteCustomersDialogFragment(CustomerDao dao, Customers customer) {
        this.dao = dao;
        this.customer = customer;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Deseja deletar cliente?").setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new RemoveTask(dao, customer).execute();

            }
        }).setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        return builder.create();
    }
}

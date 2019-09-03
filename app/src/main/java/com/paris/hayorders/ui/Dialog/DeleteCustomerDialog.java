package com.paris.hayorders.ui.Dialog;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

import com.paris.hayorders.model.Customers;

public class DeleteCustomerDialog {


    private static final String TITLE_DIALOG = "Deletar Cliente";
    private static final String MESSAGE_DIALOG = "Deseja deletar cliente";
    private static final String DESCRIPTION_POSITIVE_BUTTON = "Sim";
    private static final String DESCRIPTION_NEGATIVE_BUTTON = "Não";
    private Context context;
    private PositiveButtonListener listener;
    private Customers customer;

    public DeleteCustomerDialog(Context context, Customers customer, PositiveButtonListener listener) {
        this.context = context;
        this.listener = listener;
        this.customer = customer;
    }

    public void show(){

        new AlertDialog.Builder(context).setTitle(TITLE_DIALOG)
                .setMessage(MESSAGE_DIALOG)
                .setPositiveButton(DESCRIPTION_POSITIVE_BUTTON, (dialog, which) ->
                        listener.deleteCustomer(customer))
                .setNegativeButton(DESCRIPTION_NEGATIVE_BUTTON, (dialog, which) -> dialog.dismiss()).show();
    }


    public interface PositiveButtonListener {
        void deleteCustomer(Customers customer);
    }

}

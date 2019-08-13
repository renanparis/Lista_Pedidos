package com.paris.hayorders.ui.Dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

import com.paris.hayorders.R;

public class InsertOrderDialog {

    private static final String DESCRIPTION_NEGATIVE_BUTTON = "Cancelar";
    private static final String DESCRIPTION_POSITIVE_BUTTON = "Confirmar";
    private InputOrderListener listener;
    private Context context;

    public InsertOrderDialog(Context context, InputOrderListener listener) {
        this.listener = listener;
        this.context = context;
    }

    public void show() {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.dialog_insert_order, null, false);

        EditText fieldOrder = view.findViewById(R.id.dialog_fragment_field_order);

        new AlertDialog.Builder(context)
                .setView(view)
                .setNegativeButton(DESCRIPTION_NEGATIVE_BUTTON, (dialog, which) ->
                        dialog.dismiss())
                .setPositiveButton(DESCRIPTION_POSITIVE_BUTTON, sendInputOrder(fieldOrder)).show();

        fieldOrder.requestFocus();

    }

    private DialogInterface.OnClickListener sendInputOrder(final EditText fieldOrder) {

        return (dialog, which) -> {
            String input = fieldOrder.getText().toString();
            listener.InputOrder(input);
        };
    }

    public interface InputOrderListener {
        void InputOrder(String input);
    }
}
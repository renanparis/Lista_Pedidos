package com.paris.hayorders.activity.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.paris.hayorders.R;

public class DialogInsertOrder extends DialogFragment{

    private EditText fieldOrder;
    private InputOrderListener listener;

    public interface InputOrderListener{
        void sendInputOrder(String input);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_fragment_insert_order, null);

        fieldOrder = view.findViewById(R.id.dialog_fragment_field_order);

        builder.setView(view)
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getDialog().dismiss();
                    }
                }).setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String input = fieldOrder.getText().toString();

                listener.sendInputOrder(input);
            }
        });


        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (InputOrderListener) getActivity();
        }catch (ClassCastException e){
            Log.e("Exception", "onAttach Exception" + e.getMessage() );
        }
    }
}

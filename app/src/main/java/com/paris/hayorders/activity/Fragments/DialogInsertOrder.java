package com.paris.hayorders.activity.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputLayout;
import com.paris.hayorders.R;

public class DialogInsertOrder extends DialogFragment{

    private TextView title;
    private EditText fieldOrder;
    private TextView cancel;
    private TextView confirm;
    private InputOrderListener listener;

    public interface InputOrderListener{
        void sendInputOrder(String input);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_fragment_insert_order, container, false);
        title = view.findViewById(R.id.dialog_fragment_title);
        TextInputLayout inputLayout = view.findViewById(R.id.dialog_fragment_field_order);
        fieldOrder = inputLayout.getEditText();
        cancel = view.findViewById(R.id.dialog_fragment_cancel);
        confirm = view.findViewById(R.id.dialog_fragment_confirm);



        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String input = fieldOrder.getText().toString();
                listener.sendInputOrder(input);
                getDialog().dismiss();
            }
        });

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

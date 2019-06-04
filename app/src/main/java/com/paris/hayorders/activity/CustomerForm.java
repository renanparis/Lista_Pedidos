package com.paris.hayorders.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.paris.hayorders.R;
import com.paris.hayorders.validator.FormFieldValidator;

import java.util.ArrayList;
import java.util.List;

import javax.xml.validation.Validator;

public class CustomerForm extends AppCompatActivity {

    private List<FormFieldValidator> validators = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_form);

        configFieldName();
        configFieldCity();
        configButtonSaveCustomer();
    }

    private void configButtonSaveCustomer() {
        Button btnSaveCustomer = findViewById(R.id.form_btn_save_customer);
        btnSaveCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validFields()) {
                    Toast.makeText(CustomerForm.this,
                            "Cliente Salvo com Sucesso", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private boolean validFields() {
        boolean isValid = true;
        for (FormFieldValidator validator :
                validators) {
            if (!validator.validFormField()) {
                isValid = false;
            }
        }
        return isValid;
    }


    private void configFieldCity() {
        TextInputLayout inputLayoutCity = findViewById(R.id.form_field_city);
        validFormField(inputLayoutCity);
    }

    private void configFieldName() {
        TextInputLayout inputLayoutName = findViewById(R.id.form_field_name);
        validFormField(inputLayoutName);
    }

    private void validFormField(TextInputLayout inputLayout) {
        EditText field = inputLayout.getEditText();
        final FormFieldValidator validator = new FormFieldValidator(inputLayout);
        validators.add(validator);
        field.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {

                    validator.validFormField();
                }

            }
        });
    }
}

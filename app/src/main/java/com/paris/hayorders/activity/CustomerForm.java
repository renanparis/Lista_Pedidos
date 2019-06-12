package com.paris.hayorders.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.paris.hayorders.R;
import com.paris.hayorders.dao.CustomerDao;
import com.paris.hayorders.database.CustomerDatabase;
import com.paris.hayorders.model.Customers;
import com.paris.hayorders.validator.FormFieldValidator;

import java.util.ArrayList;
import java.util.List;

public class CustomerForm extends AppCompatActivity {

    public static final String KEY_NEW_CUSTOMER = "insert_customer";
    private List<FormFieldValidator> validators = new ArrayList<>();
    private CustomerDao dao;
    private EditText fieldCity;
    private EditText fieldName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_form);
        dao = CustomerDatabase.getInstance(this).customerDao();
        configFieldName();
        configFieldCity();
        configButtonSaveCustomer();
    }

    private void configButtonSaveCustomer() {
        Button btnSaveCustomer = findViewById(R.id.form_btn_save_customer);
        btnSaveCustomer.setOnClickListener(v -> {

            if (validFields()) {
                Customers customerCreated = createCustomer();
                returnCustomerCreated(customerCreated);
                finish();

            }

        });
    }

    private void returnCustomerCreated(Customers customerCreated) {
        Intent returnResult = new Intent();
        returnResult.putExtra(KEY_NEW_CUSTOMER, customerCreated);
        setResult(Activity.RESULT_OK, returnResult);
    }


    private Customers createCustomer() {
        String name =fieldName.getText().toString();
        String city = fieldCity.getText().toString();
        Customers customer = new Customers();
        customer.setName(name);
        customer.setCity(city);
        return customer;
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
        fieldCity = inputLayoutCity.getEditText();

        validFormField(inputLayoutCity);
    }

    private void configFieldName() {
        TextInputLayout inputLayoutName = findViewById(R.id.form_field_name);
        fieldName = inputLayoutName.getEditText();

        validFormField(inputLayoutName);
    }

    private void validFormField(TextInputLayout inputLayout) {
        EditText field = inputLayout.getEditText();
        final FormFieldValidator validator = new FormFieldValidator(inputLayout);
        validators.add(validator);
        assert field != null;
        field.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {

                validator.validFormField();
            }

        });
    }
}

package com.paris.hayorders.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.paris.hayorders.R;
import com.paris.hayorders.model.Customers;
import com.paris.hayorders.validator.FormFieldValidator;

import java.util.ArrayList;
import java.util.List;

public class CustomerForm extends AppCompatActivity {

    public static final String KEY_RESULT_FORM = "result_form_customer";
    public static final String EDIT_TITLE = "Editar Cliente";
    public static final String INSERT_TITLE = "Novo Cliente";
    public static final int INVALID_POSITION = -1;
    private List<FormFieldValidator> validators = new ArrayList<>();
    private EditText fieldCity;
    private EditText fieldName;
    private Customers customer;
    private int positionReceived;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_form);
        configFieldName();
        configFieldCity();
        loadCustomer();
        configButtonSaveCustomer();
    }

    private void loadCustomer() {
        Intent dataReceived = getIntent();
        if (dataReceived.hasExtra("update_customer")){
            setTitle(EDIT_TITLE);
            customer = dataReceived.getParcelableExtra("update_customer");
            positionReceived = dataReceived.getIntExtra("position", INVALID_POSITION);
            fillFields();
        }else{
            setTitle(INSERT_TITLE);
            customer = new Customers();

        }
    }

    private void fillFields() {
        fieldName.setText(customer.getName());
        fieldCity.setText(customer.getCity());
    }

    private void configButtonSaveCustomer() {
        Button btnSaveCustomer = findViewById(R.id.form_btn_save_customer);
        btnSaveCustomer.setOnClickListener(v -> {

            if (validFields()) {
                createCustomer();
                returnCustomerCreated(customer);
                finish();
            }
        });
    }

    private void returnCustomerCreated(Customers customer) {
        Intent returnResult = new Intent();
        returnResult.putExtra(KEY_RESULT_FORM, customer);
        returnResult.putExtra("position", positionReceived);
        setResult(Activity.RESULT_OK, returnResult);
    }


    private void createCustomer() {
        String name = fieldName.getText().toString();
        String city = fieldCity.getText().toString();
        customer.setName(name);
        customer.setCity(city);

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

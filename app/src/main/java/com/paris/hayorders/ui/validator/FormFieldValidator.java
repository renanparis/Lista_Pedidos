package com.paris.hayorders.ui.validator;

import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class FormFieldValidator {

    public static final String ERROR_TEXT = "Campo Obrigatório";
    private TextInputLayout  inputLayout;
    private EditText field;

    public FormFieldValidator(TextInputLayout inputLayout) {
        this.inputLayout = inputLayout;
        this.field = inputLayout.getEditText();
    }


    public boolean validFormField(){

        String text = field.getText().toString();
        if (text.isEmpty()){
            inputLayout.setError(ERROR_TEXT);
            return false;
        }else {
            removeError();
            return true;
        }

    }

    private void removeError(){

        inputLayout.setError(null);
        inputLayout.setErrorEnabled(false);
    }
}

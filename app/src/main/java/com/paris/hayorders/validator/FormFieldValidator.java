package com.paris.hayorders.validator;

import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class FormFieldValidator {

    private TextInputLayout  inputLayout;
    private EditText field;

    public FormFieldValidator(TextInputLayout inputLayout) {
        this.inputLayout = inputLayout;
        this.field = inputLayout.getEditText();
    }


    public boolean validFormField(){

        String text = field.getText().toString();
        if (text.isEmpty()){
            inputLayout.setError("Compo Obrigatório");
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

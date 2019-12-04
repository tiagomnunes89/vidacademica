package online.vidacademica.view.validation.validators;

import android.content.Context;

import com.google.android.material.textfield.TextInputLayout;

import online.vidacademica.R;
import online.vidacademica.utils.Util;
import online.vidacademica.view.validation.IValidator;

public class EmailValidation implements IValidator {
    private final ValidatorDefaultTextInput validator;
    private final TextInputLayout textInputLayout;
    private final Context context;


    public EmailValidation(Context context, TextInputLayout textInputLayout) {
        this.textInputLayout = textInputLayout;
        this.context = context;
        this.validator = new ValidatorDefaultTextInput(context, this.textInputLayout);
    }

    @Override
    public boolean validation(Boolean setError) {
        if (textInputLayout.getEditText() == null) {
            return false;
        }
        String text = textInputLayout.getEditText().getText().toString();
        if (!Util.validateEmail(text)) {
            if (setError){
                textInputLayout.setError(context.getString(R.string.invalid_email).toUpperCase());
                textInputLayout.getEditText().setError(context.getString(R.string.email_example).toUpperCase());
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isValid() {
        return isValid(true);
    }

    @Override
    public boolean isValid(Boolean setError) {
        if (textInputLayout.getEditText() != null)
            return validator.isValid(setError) && validation(setError);
        else return false;
    }
}
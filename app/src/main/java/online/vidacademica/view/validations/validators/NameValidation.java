package online.vidacademica.view.validations.validators;

import android.content.Context;

import com.google.android.material.textfield.TextInputLayout;

import online.vidacademica.R;
import online.vidacademica.utils.Util;
import online.vidacademica.view.validations.IValidator;

public class NameValidation implements IValidator {
    private final ValidatorDefaultTextInput validator;
    private final TextInputLayout textInputLayout;
    private final Context context;


    public NameValidation(Context context, TextInputLayout textInputLayout) {
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
        if (!Util.validateName(text)) {
            if (setError) {
                textInputLayout.setError(context.getString(R.string.invalid_name).toUpperCase());
                textInputLayout.getEditText().setError(context.getString(R.string.hint_name).toUpperCase());
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
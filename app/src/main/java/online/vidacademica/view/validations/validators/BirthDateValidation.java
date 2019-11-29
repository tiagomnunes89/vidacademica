package online.vidacademica.view.validations.validators;

import android.content.Context;

import com.google.android.material.textfield.TextInputLayout;

import online.vidacademica.R;
import online.vidacademica.utils.DateFormatUtils;
import online.vidacademica.view.validations.IValidator;

public class BirthDateValidation implements IValidator {
    public static final String DATE_FORMAT_BR = "dd/MM/yyyy";
    private static final String BIRTH_DATE_REGEX = "^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$";
    private final ValidatorDefaultTextInput validator;
    private final TextInputLayout textInputLayout;
    private final Context context;


    public BirthDateValidation(Context context, TextInputLayout textInputLayout) {
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
        if (text.matches(BIRTH_DATE_REGEX)) {
            if (DateFormatUtils.isValidBrDate(text)) {
                if (!DateFormatUtils.isAppropriateAge(text)) {
                    if (setError) {
                        textInputLayout.setError(context.getString(R.string.insufficient_age).toUpperCase());
                        textInputLayout.getEditText().setError(context.getString(R.string.birth_date_wrong).toUpperCase());
                        return false;
                    }
                }
            } else {
                if (setError)
                    textInputLayout.setError(context.getString(R.string.invalid_date).toUpperCase());
                return false;
            }
            return true;
        } else {
            if (setError)
                textInputLayout.setError(context.getString(R.string.invalid_date).toUpperCase());
            return false;
        }
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

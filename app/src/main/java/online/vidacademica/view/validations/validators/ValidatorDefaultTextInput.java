package online.vidacademica.view.validations.validators;

import android.content.Context;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import online.vidacademica.R;
import online.vidacademica.utils.StringUtils;
import online.vidacademica.view.validations.IValidator;

public class ValidatorDefaultTextInput implements IValidator {
    private final TextInputLayout textInputLayout;
    private final Context context;

    public ValidatorDefaultTextInput(Context context, TextInputLayout textInputLayout) {
        this.textInputLayout = textInputLayout;
        this.context = context;
    }

    @Override
    public boolean validation(Boolean setError) {
        if (textInputLayout.getEditText() == null) {
            return false;
        }
        if (StringUtils.isEmptyOrNull(textInputLayout.getEditText().getText().toString())) {
            if (setError)
                textInputLayout.setError(context.getString(R.string.required_field).toUpperCase());
            return false;
        }
        return true;
    }

    @Override
    public boolean isValid(Boolean setError) {
        if (!validation(setError)) return false;
        if (setError)
            textInputLayout.setErrorEnabled(false);
        return true;
    }

    @Override
    public boolean isValid() {
        return isValid(true);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (o instanceof TextInputEditText) return Objects.equals(textInputLayout.getEditText(), o);
        ValidatorDefaultTextInput that = (ValidatorDefaultTextInput) o;
        return Objects.equals(textInputLayout.getEditText(), that.textInputLayout.getEditText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(textInputLayout.getEditText());
    }
}

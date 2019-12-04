package online.vidacademica.view.validation;

import android.view.View;

import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import online.vidacademica.view.validation.validators.ValidatorDefaultTextInput;

public class Validator {

    /**
     * Executes all validators and return true if all sucess, false otherwise
     *
     * @return
     */
    public static boolean executeAllValidators(List<IValidator> validationList) {
        return executeAllValidators(validationList, (Class<? extends IValidator>) null);
    }

    /**
     * Executes all validators and return true if all sucess, false otherwise
     *
     * @return
     */
    public static boolean executeAllValidators(List<IValidator> validationList, Class<? extends IValidator> clazzValidateOnly) {
        boolean allSuccess = true;
        for (IValidator validation : validationList) {
            if (!validation.isValid(clazzValidateOnly == validation.getClass()))
                allSuccess = false;
        }
        return allSuccess;
    }

    public static boolean executeAllValidators(List<IValidator> validationList, View view) {
        boolean allSuccess = true;
        for (IValidator validation : validationList) {
            if (!validation.isValid(shouldShowError(validation, view)))
                allSuccess = false;
        }
        return allSuccess;
    }

    private static Boolean shouldShowError(IValidator validation, View view) {
        return isTextInputLayout(view) && isTextInputLayoutValidator(validation) && validation.equals(view);
    }
    private static boolean isTextInputLayout(View view) {
        return view instanceof TextInputLayout;
    }
    private static boolean isTextInputLayoutValidator(IValidator validator) {
        return validator instanceof ValidatorDefaultTextInput;
    }
}
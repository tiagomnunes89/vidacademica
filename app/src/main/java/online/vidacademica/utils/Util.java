package online.vidacademica.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    private DatePickerDialog dialog;
    private static final String REGEX_DDD = "\\([1-9]{2}\\)";
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^((?!.*?\\.\\.)[A-Za-z0-9._-]+@[A-Za-z0-9]+[A-Za-z0-9\\-.]+\\" +
                    ".[A-Za-z0-9\\-.]+[A-Za-z0-9]+)$", Pattern.CASE_INSENSITIVE);
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,10}$");
    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("^\\([1-9]{2}\\) 9 [0-9]{4}[- ]*[0-9]{4}");
    private static final Pattern VALIDATION_FULL_NAME = Pattern.compile("^(([A-ZÁÀÂÃÉÈÊÍÏÓÔÕÖÚÇÑ'`]){2,}( [A-ZÁÀÂÃÉÈÊÍÏÓÔÕÖÚÇÑ'`]{2,}){1,}"
            + "(( [A-ZÁÀÂÃÉÈÊÍÏÓÔÕÖÚÇÑ'`]{1,}( [A-ZÁÀÂÃÉÈÊÍÏÓÔÕÖÚÇÑ'`]{2,}){1,}))* *)$", Pattern.CASE_INSENSITIVE);
    public static final String BIRTH_DATE_REGEX = "^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$";

    public void callDatePickerDialog(Context context, DatePickerDialog.OnDateSetListener onDateSetListener) {

        Calendar cal = Calendar.getInstance();

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        if (dialog == null) {
            dialog = new DatePickerDialog(
                    context,
                    android.R.style.Theme_DeviceDefault_Light_Dialog,
                    onDateSetListener,
                    year, month, day);
        }
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog.show();
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null) return false;
        Matcher matcher = PHONE_NUMBER_PATTERN.matcher(phoneNumber);
        return matcher.find();
    }

    public static boolean validateName(String name) {
        if (name == null) return false;
        Matcher matcher = VALIDATION_FULL_NAME.matcher(name);
        //You must have at least two names, with two characters for each name.
        return matcher.find();
    }

    public static boolean validateEmail(String emailStr) {
        if (emailStr == null) return false;
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static boolean validatePatternPassword(String password) {
        if (password == null) return false;
        Matcher matcher = PASSWORD_PATTERN.matcher(password);
        // The password must be from 8 to 10 digits with less than 1 number and less than 1 letter
        return matcher.find();
    }
}
package online.vidacademica.core;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;

public class Util {

    private static DatePickerDialog dialog;

    public static void callDatePickerDialog(Context context, DatePickerDialog.OnDateSetListener onDateSetListener) {

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
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
    }
}


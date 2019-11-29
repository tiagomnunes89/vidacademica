package online.vidacademica.utils;

import android.annotation.SuppressLint;
import android.widget.DatePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@SuppressLint("SimpleDateFormat")
public class DateFormatUtils {

    private static final String BR_DATE_FORMAT = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))" +
            "\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^" +
            "(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|" +
            "(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])" +
            "(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
    private static final String BR_DATE_TEMPLATE = "dd/MM/yyyy";
    public static final int MINIMUM_AGE = 8;

    public static boolean isValidBrDate(String date) {
        return date.matches(BR_DATE_FORMAT);
    }

    public static Calendar stringToCalendar(String date) {
        SimpleDateFormat format = new SimpleDateFormat(BR_DATE_TEMPLATE);
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(format.parse(date));
            return calendar;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isAppropriateAge(String date) {
        Calendar birthDate = DateFormatUtils.stringToCalendar(date);
        Calendar now = Calendar.getInstance();
        now.set(Calendar.YEAR, now.get(Calendar.YEAR) - MINIMUM_AGE);
        if (birthDate != null) {
            return birthDate.before(now);
        }
        return false;
    }

    public static String calendarToString(Calendar date) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat format = new SimpleDateFormat(BR_DATE_TEMPLATE);
        return format.format(date.getTime());
    }

    public static String onDateSetResultToString(int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat format = new SimpleDateFormat(BR_DATE_TEMPLATE);
        return format.format(calendar.getTime());
    }

    public static String onDateSetResultToString(DatePicker datePicker) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat format = new SimpleDateFormat(BR_DATE_TEMPLATE);
        return format.format(calendar.getTime());
    }
}

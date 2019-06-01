package id.ac.ui.ft.personalizedobdscan.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AppUtil {

    public static String formatDate(Date date) {
        SimpleDateFormat dateId = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
        return dateId.format(date);
    }

    public static String formatDateMonth(Date date) {
        SimpleDateFormat dateId = new SimpleDateFormat("MM-yyyy", Locale.US);
        return dateId.format(date);
    }

    public static String getCurrentTime() {
        Date date = new Date();
        SimpleDateFormat dateId = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        return dateId.format(date);
    }
}

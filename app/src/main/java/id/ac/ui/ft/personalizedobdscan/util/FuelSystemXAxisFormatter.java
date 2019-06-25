package id.ac.ui.ft.personalizedobdscan.util;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.Locale;

public class FuelSystemXAxisFormatter extends ValueFormatter {

    @Override
    public String getAxisLabel(float value, AxisBase axis) {
        int i = Float.valueOf(value).intValue();
        return String.format(Locale.US, "TRIP %d", i);
    }
}

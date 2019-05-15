package id.ac.ui.ft.personalizedobdscan.util;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.List;

public class BrakeAnalysisXAxisFormatter extends ValueFormatter {
    private List<String> dates;

    public void setDates(List<String> dates) {
        this.dates = dates;
    }

    @Override
    public String getAxisLabel(float value, AxisBase axis) {
        int idx = Float.valueOf(value).intValue();
        return dates.get(idx);
    }
}

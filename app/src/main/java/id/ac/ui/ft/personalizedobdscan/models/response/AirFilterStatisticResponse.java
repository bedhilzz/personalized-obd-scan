package id.ac.ui.ft.personalizedobdscan.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class AirFilterStatisticResponse {
    @SerializedName("month")
    @Expose
    private Date month;
    @SerializedName("avg_air_filter")
    @Expose
    private Double avgAirFilter;

    public Date getMonth() {
        return month;
    }

    public void setMonth(Date month) {
        this.month = month;
    }

    public Double getAvgAirFilter() {
        return avgAirFilter;
    }

    public void setAvgAirFilter(Double avgAirFilter) {
        this.avgAirFilter = avgAirFilter;
    }
}

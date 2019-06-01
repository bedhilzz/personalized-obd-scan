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
    @SerializedName("sum_pressure_drop")
    @Expose
    private Double sumPressureDrop;
    @SerializedName("length_data")
    @Expose
    private Integer lengthData;
    @SerializedName("estimated_time_left")
    @Expose
    private Double estimatedTimeLeft;
    @SerializedName("estimated_distance_left")
    @Expose
    private Double estimatedDistanceLeft;

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

    public Double getSumPressureDrop() {
        return sumPressureDrop;
    }

    public void setSumPressureDrop(Double sumPressureDrop) {
        this.sumPressureDrop = sumPressureDrop;
    }

    public Integer getLengthData() {
        return lengthData;
    }

    public void setLengthData(Integer lengthData) {
        this.lengthData = lengthData;
    }

    public Double getEstimatedTimeLeft() {
        return estimatedTimeLeft;
    }

    public void setEstimatedTimeLeft(Double estimatedTimeLeft) {
        this.estimatedTimeLeft = estimatedTimeLeft;
    }

    public Double getEstimatedDistanceLeft() {
        return estimatedDistanceLeft;
    }

    public void setEstimatedDistanceLeft(Double estimatedDistanceLeft) {
        this.estimatedDistanceLeft = estimatedDistanceLeft;
    }
}

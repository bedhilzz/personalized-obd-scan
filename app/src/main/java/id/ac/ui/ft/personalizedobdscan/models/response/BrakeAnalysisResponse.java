package id.ac.ui.ft.personalizedobdscan.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BrakeAnalysisResponse {
    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("avg_reduction_expectancy")
    @Expose
    private Double avgReductionExpectancy;
    @SerializedName("remaining_life")
    @Expose
    private Double remainingLife;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Double getAvgReductionExpectancy() {
        return avgReductionExpectancy;
    }

    public void setAvgReductionExpectancy(Double avgReductionExpectancy) {
        this.avgReductionExpectancy = avgReductionExpectancy;
    }

    public Double getRemainingLife() {
        return remainingLife;
    }

    public void setRemainingLife(Double remainingLife) {
        this.remainingLife = remainingLife;
    }
}

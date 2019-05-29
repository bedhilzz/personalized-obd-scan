package id.ac.ui.ft.personalizedobdscan.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BrakingStatisticResponse {
    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("avg_reduction_expectancy_kampas")
    @Expose
    private Double avgReductionExpectancyKampas;
    @SerializedName("avg_reduction_expectancy_cakram")
    @Expose
    private Double avgReductionExpectancyCakram;
    @SerializedName("remaining_life_kampas")
    @Expose
    private Double remainingLifeKampas;
    @SerializedName("remaining_life_cakram")
    @Expose
    private Double remainingLifeCakram;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Double getAvgReductionExpectancyKampas() {
        return avgReductionExpectancyKampas;
    }

    public void setAvgReductionExpectancyKampas(Double avgReductionExpectancyKampas) {
        this.avgReductionExpectancyKampas = avgReductionExpectancyKampas;
    }

    public Double getAvgReductionExpectancyCakram() {
        return avgReductionExpectancyCakram;
    }

    public void setAvgReductionExpectancyCakram(Double avgReductionExpectancyCakram) {
        this.avgReductionExpectancyCakram = avgReductionExpectancyCakram;
    }

    public Double getRemainingLifeKampas() {
        return remainingLifeKampas;
    }

    public void setRemainingLifeKampas(Double remainingLifeKampas) {
        this.remainingLifeKampas = remainingLifeKampas;
    }

    public Double getRemainingLifeCakram() {
        return remainingLifeCakram;
    }

    public void setRemainingLifeCakram(Double remainingLifeCakram) {
        this.remainingLifeCakram = remainingLifeCakram;
    }
}

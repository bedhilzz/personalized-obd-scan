package id.ac.ui.ft.personalizedobdscan.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BrakingRecommendationResponse {
    @SerializedName("avg_first_week_kampas")
    @Expose
    private Double avgFirstWeekKampas;
    @SerializedName("avg_first_week_cakram")
    @Expose
    private Double avgFirstWeekCakram;

    public Double getAvgFirstWeekKampas() {
        return avgFirstWeekKampas;
    }

    public void setAvgFirstWeekKampas(Double avgFirstWeekKampas) {
        this.avgFirstWeekKampas = avgFirstWeekKampas;
    }

    public Double getAvgFirstWeekCakram() {
        return avgFirstWeekCakram;
    }

    public void setAvgFirstWeekCakram(Double avgFirstWeekCakram) {
        this.avgFirstWeekCakram = avgFirstWeekCakram;
    }
}

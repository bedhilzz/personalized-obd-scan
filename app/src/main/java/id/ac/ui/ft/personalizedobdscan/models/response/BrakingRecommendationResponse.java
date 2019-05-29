package id.ac.ui.ft.personalizedobdscan.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BrakingRecommendationResponse {
    @SerializedName("avg_second_week_kampas")
    @Expose
    private Double avgSecondWeekKampas;
    @SerializedName("avg_second_week_cakram")
    @Expose
    private Double avgSecondWeekCakram;

    public Double getAvgSecondWeekKampas() {
        return avgSecondWeekKampas;
    }

    public void setAvgSecondWeekKampas(Double avgSecondWeekKampas) {
        this.avgSecondWeekKampas = avgSecondWeekKampas;
    }

    public Double getAvgSecondWeekCakram() {
        return avgSecondWeekCakram;
    }

    public void setAvgSecondWeekCakram(Double avgSecondWeekCakram) {
        this.avgSecondWeekCakram = avgSecondWeekCakram;
    }
}

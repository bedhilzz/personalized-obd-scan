package id.ac.ui.ft.personalizedobdscan.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class AirFilterSummaryResponse {
    @SerializedName("timestamp")
    @Expose
    private Date timestamp;
    @SerializedName("avg_caf")
    @Expose
    private Double avgCaf;
    @SerializedName("estimated_time_left")
    @Expose
    private Double estimatedTimeLeft;
    @SerializedName("estimated_distance_left")
    @Expose
    private Double estimatedDistanceLeft;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Double getAvgCaf() {
        return avgCaf;
    }

    public void setAvgCaf(Double avgCaf) {
        this.avgCaf = avgCaf;
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

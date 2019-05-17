package id.ac.ui.ft.personalizedobdscan.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class AirFilterResponse {
    @SerializedName("timestamp")
    @Expose
    private Date timestamp;
    @SerializedName("avg_caf")
    @Expose
    private Double avgCaf;

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
}

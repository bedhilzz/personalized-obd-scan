package id.ac.ui.ft.personalizedobdscan.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FuelSystemResponse {
    @SerializedName("trip")
    @Expose
    private String trip;
    @SerializedName("avg_rpm")
    @Expose
    private Double avgRpm;
    @SerializedName("avg_tps")
    @Expose
    private Double avgTps;
    @SerializedName("trip_cost")
    @Expose
    private Double tripCost;
    @SerializedName("fuel_cost")
    @Expose
    private Double fuelCost;

    public String getTrip() {
        return trip;
    }

    public void setTrip(String trip) {
        this.trip = trip;
    }

    public Double getAvgRpm() {
        return avgRpm;
    }

    public void setAvgRpm(Double avgRpm) {
        this.avgRpm = avgRpm;
    }

    public Double getAvgTps() {
        return avgTps;
    }

    public void setAvgTps(Double avgTps) {
        this.avgTps = avgTps;
    }

    public Double getTripCost() {
        return tripCost;
    }

    public void setTripCost(Double tripCost) {
        this.tripCost = tripCost;
    }

    public Double getFuelCost() {
        return fuelCost;
    }

    public void setFuelCost(Double fuelCost) {
        this.fuelCost = fuelCost;
    }
}

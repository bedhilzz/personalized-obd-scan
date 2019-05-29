package id.ac.ui.ft.personalizedobdscan.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BrakingSummaryResponse {

    @SerializedName("occurrences_within_range")
    @Expose
    private List<Double> occurrencesWithinRange = null;
    @SerializedName("occurrences_below_range")
    @Expose
    private List<Double> occurrencesBelowRange = null;
    @SerializedName("occurrences_above_range")
    @Expose
    private List<Double> occurrencesAboveRange = null;
    @SerializedName("id_occurrence")
    @Expose
    private Integer idOccurrence;
    @SerializedName("attempts")
    @Expose
    private Integer attempts;
    @SerializedName("counter_occurrences_below_range")
    @Expose
    private Integer counterOccurrencesBelowRange;
    @SerializedName("counter_occurrences_within_range")
    @Expose
    private Integer counterOccurrencesWithinRange;
    @SerializedName("counter_occurrences_above_range")
    @Expose
    private Integer counterOccurrencesAboveRange;

    public List<Double> getOccurrencesWithinRange() {
        return occurrencesWithinRange;
    }

    public void setOccurrencesWithinRange(List<Double> occurrencesWithinRange) {
        this.occurrencesWithinRange = occurrencesWithinRange;
    }

    public List<Double> getOccurrencesBelowRange() {
        return occurrencesBelowRange;
    }

    public void setOccurrencesBelowRange(List<Double> occurrencesBelowRange) {
        this.occurrencesBelowRange = occurrencesBelowRange;
    }

    public List<Double> getOccurrencesAboveRange() {
        return occurrencesAboveRange;
    }

    public void setOccurrencesAboveRange(List<Double> occurrencesAboveRange) {
        this.occurrencesAboveRange = occurrencesAboveRange;
    }

    public Integer getIdOccurrence() {
        return idOccurrence;
    }

    public void setIdOccurrence(Integer idOccurrence) {
        this.idOccurrence = idOccurrence;
    }

    public Integer getAttempts() {
        return attempts;
    }

    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
    }

    public Integer getCounterOccurrencesBelowRange() {
        return counterOccurrencesBelowRange;
    }

    public void setCounterOccurrencesBelowRange(Integer counterOccurrencesBelowRange) {
        this.counterOccurrencesBelowRange = counterOccurrencesBelowRange;
    }

    public Integer getCounterOccurrencesWithinRange() {
        return counterOccurrencesWithinRange;
    }

    public void setCounterOccurrencesWithinRange(Integer counterOccurrencesWithinRange) {
        this.counterOccurrencesWithinRange = counterOccurrencesWithinRange;
    }

    public Integer getCounterOccurrencesAboveRange() {
        return counterOccurrencesAboveRange;
    }

    public void setCounterOccurrencesAboveRange(Integer counterOccurrencesAboveRange) {
        this.counterOccurrencesAboveRange = counterOccurrencesAboveRange;
    }

}

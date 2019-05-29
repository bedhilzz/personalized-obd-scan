package id.ac.ui.ft.personalizedobdscan.models.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BrakingRecommendationRequest {
    @SerializedName("user_email")
    @Expose
    private String email;
    @SerializedName("request_time")
    @Expose
    private String requestTime;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }
}

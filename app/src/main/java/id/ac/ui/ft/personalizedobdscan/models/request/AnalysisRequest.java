package id.ac.ui.ft.personalizedobdscan.models.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnalysisRequest {
    @SerializedName("user_email")
    @Expose
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

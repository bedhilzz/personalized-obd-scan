package id.ac.ui.ft.personalizedobdscan.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseResponse<E> {

    @SerializedName("status_code")
    @Expose
    private Integer statusCode;

    @SerializedName("is_success")
    @Expose
    private Boolean isSuccess;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private List<E> data = null;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<E> getData() {
        return data;
    }

    public void setData(List<E> data) {
        this.data = data;
    }

}

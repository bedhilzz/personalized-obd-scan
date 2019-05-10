package id.ac.ui.ft.personalizedobdscan.repository;

import id.ac.ui.ft.personalizedobdscan.models.request.LoginRequest;
import id.ac.ui.ft.personalizedobdscan.models.request.RegisterRequest;
import id.ac.ui.ft.personalizedobdscan.models.response.BaseResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.LoginResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.RegisterResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIService {

    @POST("user/auth/")
    Call<BaseResponse<LoginResponse>> login(@Body LoginRequest loginRequest);

    @POST("user/register/")
    Call<BaseResponse<RegisterResponse>> register(@Body RegisterRequest registerRequest);
}

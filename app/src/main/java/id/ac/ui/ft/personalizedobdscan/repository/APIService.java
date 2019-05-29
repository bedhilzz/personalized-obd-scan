package id.ac.ui.ft.personalizedobdscan.repository;

import id.ac.ui.ft.personalizedobdscan.models.request.AnalysisRequest;
import id.ac.ui.ft.personalizedobdscan.models.request.BrakingRecommendationRequest;
import id.ac.ui.ft.personalizedobdscan.models.request.LoginRequest;
import id.ac.ui.ft.personalizedobdscan.models.request.RegisterRequest;
import id.ac.ui.ft.personalizedobdscan.models.response.AirFilterStatisticResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.AirFilterSummaryResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.BaseResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.BrakingRecommendationResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.BrakingStatisticResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.BrakingSummaryResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.FuelSystemResponse;
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

    @POST("user/get-breaking-data/")
    Call<BaseResponse<BrakingStatisticResponse>> getBrakingStatisticData(@Body AnalysisRequest analysisRequest);

    @POST("user/get-temperature-rise-data/")
    Call<BaseResponse<BrakingSummaryResponse>> getBrakingSummaryData(@Body AnalysisRequest analysisRequest);

    @POST("user/get-breaking-data/summary-recommendation/")
    Call<BaseResponse<BrakingRecommendationResponse>> getBrakingSummaryData(@Body BrakingRecommendationRequest brakingRecommendationRequest);

    @POST("user/get-fuel-system-data/")
    Call<BaseResponse<FuelSystemResponse>> getFuelSystemData(@Body AnalysisRequest analysisRequest);

    @POST("user/get-air-filter-data/estimated/")
    Call<BaseResponse<AirFilterSummaryResponse>> getAirFilterData(@Body AnalysisRequest analysisRequest);

    @POST("user/get-air-filter-data/by-month/")
    Call<BaseResponse<AirFilterStatisticResponse>> getAirFilterMonthlyData(@Body AnalysisRequest analysisRequest);
}

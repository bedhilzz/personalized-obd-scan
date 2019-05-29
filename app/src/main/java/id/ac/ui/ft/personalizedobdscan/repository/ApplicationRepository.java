package id.ac.ui.ft.personalizedobdscan.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

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
import retrofit2.Callback;
import retrofit2.Response;

public class ApplicationRepository {
    private APIService apiService;

    private static class SingletonHelper
    {
        private static final ApplicationRepository INSTANCE = new ApplicationRepository();
    }

    public static ApplicationRepository getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public ApplicationRepository() {
        apiService = RetrofitBuilder.retrofit.create(APIService.class);
    }

    public LiveData<BaseResponse<LoginResponse>> login(final LoginRequest request) {
        final MutableLiveData<BaseResponse<LoginResponse>> data = new MutableLiveData<>();

        apiService.login(request).enqueue(new Callback<BaseResponse<LoginResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<LoginResponse>> call, Response<BaseResponse<LoginResponse>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<LoginResponse>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<BaseResponse<RegisterResponse>> register(final RegisterRequest request) {
        final MutableLiveData<BaseResponse<RegisterResponse>> data = new MutableLiveData<>();

        apiService.register(request).enqueue(new Callback<BaseResponse<RegisterResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<RegisterResponse>> call, Response<BaseResponse<RegisterResponse>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<RegisterResponse>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<BaseResponse<BrakingStatisticResponse>> brakingStatistic(final AnalysisRequest request) {
        final MutableLiveData<BaseResponse<BrakingStatisticResponse>> data = new MutableLiveData<>();

        apiService.getBrakingStatisticData(request).enqueue(new Callback<BaseResponse<BrakingStatisticResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<BrakingStatisticResponse>> call, Response<BaseResponse<BrakingStatisticResponse>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<BrakingStatisticResponse>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<BaseResponse<BrakingSummaryResponse>> brakingSummary(final AnalysisRequest request) {
        final MutableLiveData<BaseResponse<BrakingSummaryResponse>> data = new MutableLiveData<>();

        apiService.getBrakingSummaryData(request).enqueue(new Callback<BaseResponse<BrakingSummaryResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<BrakingSummaryResponse>> call, Response<BaseResponse<BrakingSummaryResponse>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<BrakingSummaryResponse>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<BaseResponse<BrakingRecommendationResponse>> brakingSummary(final BrakingRecommendationRequest request) {
        final MutableLiveData<BaseResponse<BrakingRecommendationResponse>> data = new MutableLiveData<>();

        apiService.getBrakingSummaryData(request).enqueue(new Callback<BaseResponse<BrakingRecommendationResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<BrakingRecommendationResponse>> call, Response<BaseResponse<BrakingRecommendationResponse>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<BrakingRecommendationResponse>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<BaseResponse<FuelSystemResponse>> fuelSystemAnalysis(final AnalysisRequest request) {
        final MutableLiveData<BaseResponse<FuelSystemResponse>> data = new MutableLiveData<>();

        apiService.getFuelSystemData(request).enqueue(new Callback<BaseResponse<FuelSystemResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<FuelSystemResponse>> call, Response<BaseResponse<FuelSystemResponse>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<FuelSystemResponse>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<BaseResponse<AirFilterSummaryResponse>> airFilterSummary(final AnalysisRequest request) {
        final MutableLiveData<BaseResponse<AirFilterSummaryResponse>> data = new MutableLiveData<>();

        apiService.getAirFilterData(request).enqueue(new Callback<BaseResponse<AirFilterSummaryResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<AirFilterSummaryResponse>> call, Response<BaseResponse<AirFilterSummaryResponse>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<AirFilterSummaryResponse>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<BaseResponse<AirFilterStatisticResponse>> airFilterStatistic(final AnalysisRequest request) {
        final MutableLiveData<BaseResponse<AirFilterStatisticResponse>> data = new MutableLiveData<>();

        apiService.getAirFilterMonthlyData(request).enqueue(new Callback<BaseResponse<AirFilterStatisticResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<AirFilterStatisticResponse>> call, Response<BaseResponse<AirFilterStatisticResponse>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<AirFilterStatisticResponse>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}


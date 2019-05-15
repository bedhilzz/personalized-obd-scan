package id.ac.ui.ft.personalizedobdscan.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import id.ac.ui.ft.personalizedobdscan.models.request.BrakeAnalysisRequest;
import id.ac.ui.ft.personalizedobdscan.models.request.LoginRequest;
import id.ac.ui.ft.personalizedobdscan.models.request.RegisterRequest;
import id.ac.ui.ft.personalizedobdscan.models.response.BaseResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.BrakeAnalysisResponse;
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

    public LiveData<BaseResponse<BrakeAnalysisResponse>> brakeAnalysis(final BrakeAnalysisRequest request) {
        final MutableLiveData<BaseResponse<BrakeAnalysisResponse>> data = new MutableLiveData<>();

        apiService.getBreakingData(request).enqueue(new Callback<BaseResponse<BrakeAnalysisResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<BrakeAnalysisResponse>> call, Response<BaseResponse<BrakeAnalysisResponse>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<BrakeAnalysisResponse>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}


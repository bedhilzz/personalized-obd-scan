package id.ac.ui.ft.personalizedobdscan.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import id.ac.ui.ft.personalizedobdscan.models.request.LoginRequest;
import id.ac.ui.ft.personalizedobdscan.models.response.BaseResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.LoginResponse;
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
}


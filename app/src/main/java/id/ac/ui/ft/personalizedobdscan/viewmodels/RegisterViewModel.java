package id.ac.ui.ft.personalizedobdscan.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import id.ac.ui.ft.personalizedobdscan.constant.Constants;
import id.ac.ui.ft.personalizedobdscan.models.request.RegisterRequest;
import id.ac.ui.ft.personalizedobdscan.models.response.BaseResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.RegisterResponse;
import id.ac.ui.ft.personalizedobdscan.repository.ApplicationRepository;

public class RegisterViewModel extends AndroidViewModel {

    public RegisterViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<BaseResponse<RegisterResponse>> register(String name, String email, String password, int age, String sex, String fcmToken) {
        RegisterRequest request = new RegisterRequest();
        request.setName(name);
        request.setEmail(email);
        request.setPassword(password);
        request.setAge(age);
        request.setSex(sex);
        request.setCarId(Constants.NISSAN_JUKE_ID);
        request.setFcmToken(fcmToken);

        return ApplicationRepository.getInstance().register(request);
    }
}

package id.ac.ui.ft.personalizedobdscan.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import id.ac.ui.ft.personalizedobdscan.models.request.LoginRequest;
import id.ac.ui.ft.personalizedobdscan.models.response.BaseResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.LoginResponse;
import id.ac.ui.ft.personalizedobdscan.repository.ApplicationRepository;

public class LauncherViewModel extends AndroidViewModel {

    public LauncherViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<BaseResponse<LoginResponse>> login(String email, String password) {
        LoginRequest request = new LoginRequest();
        request.setEmail(email);
        request.setPassword(password);

        return ApplicationRepository.getInstance().login(request);
    }
}

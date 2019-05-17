package id.ac.ui.ft.personalizedobdscan.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import id.ac.ui.ft.personalizedobdscan.models.request.AnalysisRequest;
import id.ac.ui.ft.personalizedobdscan.models.response.AirFilterResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.BaseResponse;
import id.ac.ui.ft.personalizedobdscan.repository.ApplicationRepository;

public class AirFilterViewModel extends AndroidViewModel {

    public AirFilterViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<BaseResponse<AirFilterResponse>> airFilterAnalysis(String email) {
        AnalysisRequest request = new AnalysisRequest();
        request.setEmail(email);

        return ApplicationRepository.getInstance().airFilterAnalysis(request);
    }
}

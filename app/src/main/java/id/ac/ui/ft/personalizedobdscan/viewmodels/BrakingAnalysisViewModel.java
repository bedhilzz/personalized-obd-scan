package id.ac.ui.ft.personalizedobdscan.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import id.ac.ui.ft.personalizedobdscan.models.request.AnalysisRequest;
import id.ac.ui.ft.personalizedobdscan.models.response.BaseResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.BrakeAnalysisResponse;
import id.ac.ui.ft.personalizedobdscan.repository.ApplicationRepository;

public class BrakingAnalysisViewModel extends AndroidViewModel {
    public List<String> brakeDataDates;

    public BrakingAnalysisViewModel(@NonNull Application application) {
        super(application);
        brakeDataDates = new ArrayList<>();
    }

    public LiveData<BaseResponse<BrakeAnalysisResponse>> brakeAnalysis(String email) {
        AnalysisRequest request = new AnalysisRequest();
        request.setEmail(email);

        LiveData<BaseResponse<BrakeAnalysisResponse>> response = ApplicationRepository.getInstance().brakeAnalysis(request);

        return response;
    }
}

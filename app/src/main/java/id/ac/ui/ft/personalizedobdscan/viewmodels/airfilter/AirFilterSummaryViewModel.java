package id.ac.ui.ft.personalizedobdscan.viewmodels.airfilter;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import id.ac.ui.ft.personalizedobdscan.models.request.AnalysisRequest;
import id.ac.ui.ft.personalizedobdscan.models.response.AirFilterSummaryResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.BaseResponse;
import id.ac.ui.ft.personalizedobdscan.repository.ApplicationRepository;

public class AirFilterSummaryViewModel extends AndroidViewModel {
    public AirFilterSummaryViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<BaseResponse<AirFilterSummaryResponse>> airFilterAnalysis(String email) {
        AnalysisRequest request = new AnalysisRequest();
        request.setEmail(email);

        return ApplicationRepository.getInstance().airFilterSummary(request);
    }
}

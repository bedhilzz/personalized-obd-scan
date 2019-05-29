package id.ac.ui.ft.personalizedobdscan.viewmodels.braking;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import id.ac.ui.ft.personalizedobdscan.models.request.AnalysisRequest;
import id.ac.ui.ft.personalizedobdscan.models.request.BrakingRecommendationRequest;
import id.ac.ui.ft.personalizedobdscan.models.response.BaseResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.BrakingRecommendationResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.BrakingSummaryResponse;
import id.ac.ui.ft.personalizedobdscan.repository.ApplicationRepository;

public class BrakingSummaryViewModel extends AndroidViewModel {
    public BrakingSummaryViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<BaseResponse<BrakingSummaryResponse>> brakingSummary(String email) {
        AnalysisRequest request = new AnalysisRequest();
        request.setEmail(email);

        return ApplicationRepository.getInstance().brakingSummary(request);
    }

    public LiveData<BaseResponse<BrakingRecommendationResponse>> brakingSummary(String email, String requestTime) {
        BrakingRecommendationRequest request = new BrakingRecommendationRequest();
        request.setEmail(email);
        request.setRequestTime(requestTime);

        return ApplicationRepository.getInstance().brakingSummary(request);
    }
}

package id.ac.ui.ft.personalizedobdscan.viewmodels.airfilter;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import id.ac.ui.ft.personalizedobdscan.models.request.AnalysisRequest;
import id.ac.ui.ft.personalizedobdscan.models.response.AirFilterStatisticResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.BaseResponse;
import id.ac.ui.ft.personalizedobdscan.repository.ApplicationRepository;

public class AirFilterStatisticViewModel extends AndroidViewModel {
    public List<String> airFilterDataMonth;

    public AirFilterStatisticViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<BaseResponse<AirFilterStatisticResponse>> airFilterStatistic(String email) {
        AnalysisRequest request = new AnalysisRequest();
        request.setEmail(email);

        return ApplicationRepository.getInstance().airFilterStatistic(request);
    }
}

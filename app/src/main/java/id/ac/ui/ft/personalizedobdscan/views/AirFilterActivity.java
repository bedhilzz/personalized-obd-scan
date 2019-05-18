package id.ac.ui.ft.personalizedobdscan.views;

import android.animation.LayoutTransition;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.Locale;

import id.ac.ui.ft.personalizedobdscan.R;
import id.ac.ui.ft.personalizedobdscan.constant.Constants;
import id.ac.ui.ft.personalizedobdscan.databinding.ActivityAirFilterBinding;
import id.ac.ui.ft.personalizedobdscan.models.response.AirFilterResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.BaseResponse;
import id.ac.ui.ft.personalizedobdscan.util.AppUtil;
import id.ac.ui.ft.personalizedobdscan.util.Percent;
import id.ac.ui.ft.personalizedobdscan.viewmodels.AirFilterViewModel;

public class AirFilterActivity extends AppCompatActivity {
    private AirFilterViewModel viewModel;
    private ActivityAirFilterBinding binding;

    private SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPrefs = getSharedPreferences(Constants.PREF_FILE_NAME, Context.MODE_PRIVATE);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_air_filter);
        binding.airFilterContainer.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        binding.airFilterContainer.setVisibility(View.INVISIBLE);
        initComponent();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void initComponent() {
        initViewModel();
        initAirFilterImage();
        getAirFilterAnalysisData();
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(AirFilterViewModel.class);

        binding.setModel(viewModel);
        binding.setLifecycleOwner(this);
    }

    private void initAirFilterImage() {
        setAirFilterValue(new Percent(0));
    }

    private void getAirFilterAnalysisData() {
        final String email = mPrefs.getString(Constants.PREF_KEY_USER_EMAIL, null);
        viewModel.airFilterAnalysis(email).
                observe(this, new Observer<BaseResponse<AirFilterResponse>>() {
                    @Override
                    public void onChanged(@Nullable BaseResponse<AirFilterResponse> response) {
                        binding.airFilterContainer.setVisibility(View.VISIBLE);
                        if (response != null) {
                            if (response.getIsSuccess()) {
                                setAirFilterAnalysis(response.getData().get(0));
                            } else {
                                showMessage(response.getMessage());
                            }
                        } else {
                            showMessage(getString(R.string.unknown_error));
                        }
                    }
                });
    }

    private void setAirFilterAnalysis(AirFilterResponse response) {
        int condition = response.getAvgCaf().intValue();

        setAirFilterValue(new Percent(condition));

        binding.tvAirFilterConditionDate.setText(AppUtil.formatDate(response.getTimestamp()));
        binding.tvAirFilterCondition.setText(String.format(Locale.US, "%d%%", condition));
        binding.tvAirFilterChangeEstimationMonth.setText(String.format(Locale.US, "%d bulan", response.getEstimatedTimeLeft().intValue()));
        binding.tvAirFilterChangeEstimationKm.setText(String.format(Locale.US, "%d KM", response.getEstimatedDistanceLeft().intValue()));
        if (condition < 30) {
            binding.tvAirFilterSuggestion.setText(R.string.tv_air_filter_suggestion_damaged);
        } else {
            binding.tvAirFilterSuggestion.setText(R.string.tv_air_filter_suggestion_normal);
        }
    }

    private void setAirFilterValue(Percent value) {
        binding.airFilterProgressBar.setCurrentValue(value);
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

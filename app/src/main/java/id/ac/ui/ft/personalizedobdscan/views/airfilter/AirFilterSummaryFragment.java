package id.ac.ui.ft.personalizedobdscan.views.airfilter;

import android.animation.LayoutTransition;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Locale;

import id.ac.ui.ft.personalizedobdscan.R;
import id.ac.ui.ft.personalizedobdscan.constant.Constants;
import id.ac.ui.ft.personalizedobdscan.databinding.FragmentAirFilterSummaryBinding;
import id.ac.ui.ft.personalizedobdscan.models.response.AirFilterSummaryResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.BaseResponse;
import id.ac.ui.ft.personalizedobdscan.util.AppUtil;
import id.ac.ui.ft.personalizedobdscan.util.Percent;
import id.ac.ui.ft.personalizedobdscan.viewmodels.airfilter.AirFilterSummaryViewModel;

public class AirFilterSummaryFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private FragmentAirFilterSummaryBinding binding;
    private AirFilterSummaryViewModel viewModel;

    private SharedPreferences mPrefs;

    public static AirFilterSummaryFragment newInstance() {
        return new AirFilterSummaryFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_air_filter_summary,
                container,
                false
        );

        mPrefs = getActivity().getSharedPreferences(Constants.PREF_FILE_NAME, Context.MODE_PRIVATE);

        binding.airFilterContainer.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        binding.airFilterContainer.setVisibility(View.INVISIBLE);
        binding.airFilterSwipeRefreshLayout.setOnRefreshListener(this);

        initComponent();

        View view = binding.getRoot();

        return view;
    }

    @Override
    public void onRefresh() {
        getAirFilterAnalysisData();
    }

    private void initComponent() {
        initViewModel();
        initAirFilterImage();
        getAirFilterAnalysisData();
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(AirFilterSummaryViewModel.class);

        binding.setModel(viewModel);
        binding.setLifecycleOwner(this);
    }

    private void initAirFilterImage() {
        setAirFilterValue(new Percent(0));
    }

    private void getAirFilterAnalysisData() {
        final String email = mPrefs.getString(Constants.PREF_KEY_USER_EMAIL, null);
        viewModel.airFilterAnalysis(email).
                observe(this, new Observer<BaseResponse<AirFilterSummaryResponse>>() {
                    @Override
                    public void onChanged(@Nullable BaseResponse<AirFilterSummaryResponse> response) {
                        binding.airFilterContainer.setVisibility(View.VISIBLE);
                        binding.airFilterSwipeRefreshLayout.setRefreshing(false);
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

    private void setAirFilterAnalysis(AirFilterSummaryResponse response) {
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
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}

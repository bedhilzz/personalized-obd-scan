package id.ac.ui.ft.personalizedobdscan.views.braking;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
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
import id.ac.ui.ft.personalizedobdscan.databinding.FragmentBrakingSummaryBinding;
import id.ac.ui.ft.personalizedobdscan.models.response.BaseResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.BrakingRecommendationResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.BrakingSummaryResponse;
import id.ac.ui.ft.personalizedobdscan.util.AppUtil;
import id.ac.ui.ft.personalizedobdscan.viewmodels.braking.BrakingSummaryViewModel;
import id.ac.ui.ft.personalizedobdscan.views.maintenance.MaintenanceJournalActivity;

public class BrakingSummaryFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private FragmentBrakingSummaryBinding binding;
    private BrakingSummaryViewModel viewModel;

    private SharedPreferences mPrefs;

    public static BrakingSummaryFragment newInstance() {
        return new BrakingSummaryFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_braking_summary,
                container,
                false
        );
        binding.brakingSummarySwipeRefreshLayout.setOnRefreshListener(this);

        mPrefs = getActivity().getSharedPreferences(Constants.PREF_FILE_NAME, Context.MODE_PRIVATE);

        initComponent();

        View view = binding.getRoot();

        return view;
    }

    @Override
    public void onRefresh() {
        getBrakingSummaryData();
    }

    private void initComponent() {
        initViewModel();
        initSuggestionButton();
        getBrakingSummaryData();
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(BrakingSummaryViewModel.class);
        binding.setModel(viewModel);
        binding.setLifecycleOwner(this);
    }

    private void initSuggestionButton() {
        final Intent intent = new Intent(getActivity(), MaintenanceJournalActivity.class);
        binding.btBrakingSummarySuggestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }

    private void getBrakingSummaryData() {
        final String email = mPrefs.getString(Constants.PREF_KEY_USER_EMAIL, null);
        viewModel.brakingSummary(email).
                observe(this, new Observer<BaseResponse<BrakingSummaryResponse>>() {
                    @Override
                    public void onChanged(@Nullable BaseResponse<BrakingSummaryResponse> response) {
                        binding.brakingSummaryContainer.setVisibility(View.VISIBLE);
                        binding.brakingSummarySwipeRefreshLayout.setRefreshing(false);
                        if (response != null) {
                            if (response.getIsSuccess()) {
                                setBrakingSummaryData(response.getData().get(0));
                            } else {
                                showMessage(response.getMessage());
                            }
                        } else {
                            showMessage(getString(R.string.unknown_error));
                        }
                    }
                });

        String currentTime = AppUtil.getCurrentTime();
        viewModel.brakingSummary(email, currentTime).
                observe(this, new Observer<BaseResponse<BrakingRecommendationResponse>>() {
                    @Override
                    public void onChanged(@Nullable BaseResponse<BrakingRecommendationResponse> response) {
                        binding.brakingSummaryContainer.setVisibility(View.VISIBLE);
                        binding.brakingSummarySwipeRefreshLayout.setRefreshing(false);
                        if (response != null) {
                            if (response.getIsSuccess()) {
                                setBrakingSummaryData(response.getData().get(0));
                            } else {
                                showMessage(response.getMessage());
                            }
                        } else {
                            showMessage(getString(R.string.unknown_error));
                        }
                    }
                });
    }

    private void setBrakingSummaryData(BrakingSummaryResponse response) {
        int withinRange = response.getCounterOccurrencesWithinRange();
        int aboveRange = response.getCounterOccurrencesAboveRange();
        int belowRange = response.getCounterOccurrencesBelowRange();

        String format = "%d kali";
        binding.tvBrakingWithinRange.setText(String.format(Locale.US, format, withinRange));
        binding.tvBrakingAboveRange.setText(String.format(Locale.US, format, aboveRange));
        binding.tvBrakingBelowRange.setText(String.format(Locale.US, format, belowRange));

        if ((withinRange > aboveRange) && (withinRange > belowRange)) {
            binding.tvBrakingSummarySuggestion.setText(R.string.tv_braking_suggestion_normal);
        } else {
            if (aboveRange > belowRange) {
                binding.tvBrakingSummarySuggestion.setText(R.string.tv_braking_suggestion_above_range);
            } else {
                binding.tvBrakingSummarySuggestion.setText(R.string.tv_braking_suggestion_below_range);
            }
        }
    }

    private void setBrakingSummaryData(BrakingRecommendationResponse response) {
        try {
            int brakePadMaintenance = (int) (Constants.BRAKE_PAD_MAINTENANCE_DISTANCE/response.getAvgFirstWeekKampas());
            int brakePadReplacement = (int) (Constants.BRAKE_PAD_LIFESPAN/response.getAvgFirstWeekKampas());
            int brakeDiscMaintenance = (int) (Constants.BRAKE_DISC_MAINTENANCE_DISTANCE/response.getAvgFirstWeekCakram());
            int brakeDiscReplacement = (int) (Constants.BRAKE_DISC_LIFESPAN/response.getAvgFirstWeekCakram());

            String format = "%d hari sekali";
            binding.tvBrakePadMaintenance.setText(String.format(Locale.US, format, brakePadMaintenance));
            binding.tvBrakePadReplacement.setText(String.format(Locale.US, format, brakePadReplacement));
            binding.tvBrakeDiscMaintenance.setText(String.format(Locale.US, format, brakeDiscMaintenance));
            binding.tvBrakeDiscReplacement.setText(String.format(Locale.US, format, brakeDiscReplacement));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}

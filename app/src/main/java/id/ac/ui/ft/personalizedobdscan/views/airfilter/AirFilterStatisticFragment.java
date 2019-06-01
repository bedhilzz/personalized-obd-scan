package id.ac.ui.ft.personalizedobdscan.views.airfilter;

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

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

import id.ac.ui.ft.personalizedobdscan.R;
import id.ac.ui.ft.personalizedobdscan.constant.Constants;
import id.ac.ui.ft.personalizedobdscan.databinding.FragmentAirFilterStatisticBinding;
import id.ac.ui.ft.personalizedobdscan.models.response.AirFilterStatisticResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.BaseResponse;
import id.ac.ui.ft.personalizedobdscan.util.AppUtil;
import id.ac.ui.ft.personalizedobdscan.util.BrakeAnalysisXAxisFormatter;
import id.ac.ui.ft.personalizedobdscan.viewmodels.airfilter.AirFilterStatisticViewModel;

public class AirFilterStatisticFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private FragmentAirFilterStatisticBinding binding;
    private AirFilterStatisticViewModel viewModel;

    private SharedPreferences mPrefs;


    private BarChart airFilterDistanceReductionChart;
    private BarChart airFilterDurationReductionChart;

    public static AirFilterStatisticFragment newInstance() {
        return new AirFilterStatisticFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_air_filter_statistic,
                container,
                false
        );
        binding.airFilterAnalysisSwipeRefreshLayout.setOnRefreshListener(this);

        mPrefs = getActivity().getSharedPreferences(Constants.PREF_FILE_NAME, Context.MODE_PRIVATE);

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
        initChart();
        getAirFilterAnalysisData();
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(AirFilterStatisticViewModel.class);
        binding.setModel(viewModel);
        binding.setLifecycleOwner(this);
    }

    private void initChart() {
        airFilterDistanceReductionChart = binding.barChartAirFilterDistanceReduction;
        airFilterDurationReductionChart = binding.barChartAirFilterDurationReduction;

        airFilterDistanceReductionChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        airFilterDistanceReductionChart.getXAxis().setGranularityEnabled(true);
        airFilterDistanceReductionChart.getXAxis().setGranularity(1f);
        airFilterDistanceReductionChart.getDescription().setEnabled(false);
        airFilterDistanceReductionChart.setFitBars(true);

        airFilterDurationReductionChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        airFilterDurationReductionChart.getXAxis().setGranularityEnabled(true);
        airFilterDurationReductionChart.getXAxis().setGranularity(1f);
        airFilterDurationReductionChart.getDescription().setEnabled(false);
        airFilterDurationReductionChart.setFitBars(true);
    }

    private void getAirFilterAnalysisData() {
        final String email = mPrefs.getString(Constants.PREF_KEY_USER_EMAIL, null);
        viewModel.airFilterStatistic(email).
                observe(this, new Observer<BaseResponse<AirFilterStatisticResponse>>() {
                    @Override
                    public void onChanged(@Nullable BaseResponse<AirFilterStatisticResponse> response) {
                        binding.airFilterAnalysisSwipeRefreshLayout.setRefreshing(false);
                        if (response != null) {
                            if (response.getIsSuccess()) {
                                initChartDates(response.getData());
                                initAirFilterDistanceReductionBarChart(response.getData());
                                initAirFilterDurationReductionBarChart(response.getData());
                            } else {
                                showMessage(response.getMessage());
                            }
                        } else {
                            showMessage(getString(R.string.unknown_error));
                        }
                    }
                });
    }

    private void initChartDates(List<AirFilterStatisticResponse> responses) {
        List<String> dates = new ArrayList<>();

        for (AirFilterStatisticResponse e : responses) {
            dates.add(AppUtil.formatDateMonth(e.getMonth()));
        }

        viewModel.airFilterDataMonth = dates;
    }

    private void initAirFilterDistanceReductionBarChart(List<AirFilterStatisticResponse> responses) {
        List<BarEntry> entries = new ArrayList<>();

        float idx = 0f;
        for (AirFilterStatisticResponse e : responses) {
            entries.add(new BarEntry(idx, e.getEstimatedDistanceLeft().floatValue()));
            idx += 1f;
        }

        BrakeAnalysisXAxisFormatter formatter = new BrakeAnalysisXAxisFormatter();
        formatter.setDates(viewModel.airFilterDataMonth);

        BarDataSet set = new BarDataSet(entries, getString(R.string.tv_bar_chart_air_filter_distance_reduction_label));
        set.setColors(getResources().getColor(R.color.colorPrimaryDark));

        BarData data = new BarData(set);
        data.setBarWidth(0.9f);

        airFilterDistanceReductionChart.getXAxis().setValueFormatter(formatter);
        airFilterDistanceReductionChart.setData(data);
        airFilterDistanceReductionChart.animateXY(1000, 1000, Easing.EaseInOutCubic);
    }

    private void initAirFilterDurationReductionBarChart(List<AirFilterStatisticResponse> responses) {
        List<BarEntry> entries = new ArrayList<>();

        float idx = 0f;
        for (AirFilterStatisticResponse e : responses) {
            entries.add(new BarEntry(idx, e.getEstimatedTimeLeft().floatValue()));
            idx += 1f;
        }

        BrakeAnalysisXAxisFormatter formatter = new BrakeAnalysisXAxisFormatter();
        formatter.setDates(viewModel.airFilterDataMonth);

        BarDataSet set = new BarDataSet(entries, getString(R.string.tv_bar_chart_air_filter_duration_reduction_label));
        set.setColors(getResources().getColor(R.color.colorPrimaryDark));

        BarData data = new BarData(set);
        data.setBarWidth(0.9f);

        airFilterDurationReductionChart.getXAxis().setValueFormatter(formatter);
        airFilterDurationReductionChart.setData(data);
        airFilterDurationReductionChart.animateXY(1000, 1000, Easing.EaseInOutCubic);
    }

    private void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}

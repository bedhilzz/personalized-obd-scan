package id.ac.ui.ft.personalizedobdscan.views;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
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
import id.ac.ui.ft.personalizedobdscan.databinding.ActivityBrakingAnalysisBinding;
import id.ac.ui.ft.personalizedobdscan.models.response.BaseResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.BrakeAnalysisResponse;
import id.ac.ui.ft.personalizedobdscan.util.BrakeAnalysisXAxisFormatter;
import id.ac.ui.ft.personalizedobdscan.viewmodels.BrakingAnalysisViewModel;

public class BrakingAnalysisActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private ActivityBrakingAnalysisBinding binding;
    private BrakingAnalysisViewModel viewModel;
    private SharedPreferences mPrefs;

    private BarChart padReductionChart;
    private BarChart padRemainingLifeChart;
    private BarChart discReductionChart;
    private BarChart discRemainingLifeChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPrefs = getSharedPreferences(Constants.PREF_FILE_NAME, Context.MODE_PRIVATE);

        binding = DataBindingUtil.setContentView(this, R.layout.fragment_braking_statistic);
        binding.brakingAnalysisSwipeRefreshLayout.setOnRefreshListener(this);

        initComponent();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onRefresh() {
        getBrakingAnalysisData();
    }

    private void initComponent() {
        initViewModel();
        initChart();
        getBrakingAnalysisData();
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(BrakingAnalysisViewModel.class);

        binding.setModel(viewModel);
        binding.setLifecycleOwner(this);
    }

    private void initChart() {
        padReductionChart = binding.barChartAverageReductionPad;
        padRemainingLifeChart = binding.barChartRemainingLifePad;
        discReductionChart = binding.barChartAverageReductionDisc;
        discRemainingLifeChart = binding.barChartRemainingLifeDisc;

        padReductionChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        padReductionChart.getXAxis().setGranularityEnabled(true);
        padReductionChart.getXAxis().setGranularity(1f);
        padReductionChart.getDescription().setEnabled(false);
        padReductionChart.setFitBars(true);

        padRemainingLifeChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        padRemainingLifeChart.getXAxis().setGranularityEnabled(true);
        padRemainingLifeChart.getXAxis().setGranularity(1f);
        padRemainingLifeChart.getDescription().setEnabled(false);
        padRemainingLifeChart.setFitBars(true);

        discReductionChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        discReductionChart.getXAxis().setGranularityEnabled(true);
        discReductionChart.getXAxis().setGranularity(1f);
        discReductionChart.getDescription().setEnabled(false);
        discReductionChart.setFitBars(true);

        discRemainingLifeChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        discRemainingLifeChart.getXAxis().setGranularityEnabled(true);
        discRemainingLifeChart.getXAxis().setGranularity(1f);
        discRemainingLifeChart.getDescription().setEnabled(false);
        discRemainingLifeChart.setFitBars(true);
    }

    private void getBrakingAnalysisData() {
        final String email = mPrefs.getString(Constants.PREF_KEY_USER_EMAIL, null);
        viewModel.brakeAnalysis(email).
                observe(this, new Observer<BaseResponse<BrakeAnalysisResponse>>() {
                    @Override
                    public void onChanged(@Nullable BaseResponse<BrakeAnalysisResponse> response) {
                        binding.brakingAnalysisSwipeRefreshLayout.setRefreshing(false);
                        if (response != null) {
                            if (response.getIsSuccess()) {
                                initChartDates(response.getData());
                                initPadAverageReductionExpectancyBarChart(response.getData());
                                initDiscAverageReductionExpectancyBarChart(response.getData());
                                initPadRemainingLifeBarChart(response.getData());
                                initDiscRemainingLifeBarChart(response.getData());
                            } else {
                                showMessage(response.getMessage());
                            }
                        } else {
                            showMessage(getString(R.string.unknown_error));
                        }
                    }
                });
    }

    private void initChartDates(List<BrakeAnalysisResponse> responses) {
        List<String> dates = new ArrayList<>();

        for (BrakeAnalysisResponse e : responses) {
            dates.add(e.getDay());
        }

        viewModel.brakeDataDates = dates;
    }

    private void initPadAverageReductionExpectancyBarChart(List<BrakeAnalysisResponse> responses) {
        List<BarEntry> entries = new ArrayList<>();

        float idx = 0f;
        for (BrakeAnalysisResponse e : responses) {
            entries.add(new BarEntry(idx, e.getAvgReductionExpectancyKampas().floatValue()));
            idx += 1f;
        }

        BrakeAnalysisXAxisFormatter formatter = new BrakeAnalysisXAxisFormatter();
        formatter.setDates(viewModel.brakeDataDates);

        BarDataSet set = new BarDataSet(entries, getString(R.string.tv_bar_chart_pad_reduction_label));
        set.setColors(getResources().getColor(R.color.colorPrimaryDark));

        BarData data = new BarData(set);
        data.setBarWidth(0.9f);

        padReductionChart.getXAxis().setValueFormatter(formatter);
        padReductionChart.setData(data);
        padReductionChart.animateXY(1000, 1000, Easing.EaseInOutCubic);
    }

    private void initPadRemainingLifeBarChart(List<BrakeAnalysisResponse> responses) {
        List<BarEntry> entries = new ArrayList<>();

        int idx = 0;
        for (BrakeAnalysisResponse e : responses) {
            entries.add(new BarEntry(idx++, e.getRemainingLifeKampas().floatValue()));
        }


        BrakeAnalysisXAxisFormatter formatter = new BrakeAnalysisXAxisFormatter();
        formatter.setDates(viewModel.brakeDataDates);

        BarDataSet set = new BarDataSet(entries, getString(R.string.tv_bar_chart_pad_remaining_life_label));
        set.setColors(getResources().getColor(R.color.colorPrimaryDark));

        BarData data = new BarData(set);
        data.setBarWidth(0.9f);

        padRemainingLifeChart.getXAxis().setValueFormatter(formatter);
        padRemainingLifeChart.setData(data);
        padRemainingLifeChart.animateXY(1000, 1000, Easing.EaseInOutCubic);
    }

    private void initDiscAverageReductionExpectancyBarChart(List<BrakeAnalysisResponse> responses) {
        List<BarEntry> entries = new ArrayList<>();

        int idx = 0;
        for (BrakeAnalysisResponse e : responses) {
            entries.add(new BarEntry(idx++, e.getAvgReductionExpectancyCakram().floatValue()));
        }


        BrakeAnalysisXAxisFormatter formatter = new BrakeAnalysisXAxisFormatter();
        formatter.setDates(viewModel.brakeDataDates);

        BarDataSet set = new BarDataSet(entries, getString(R.string.tv_bar_chart_disc_reduction_label));
        set.setColors(getResources().getColor(R.color.colorRed));

        BarData data = new BarData(set);
        data.setBarWidth(0.9f);

        discReductionChart.getXAxis().setValueFormatter(formatter);
        discReductionChart.setData(data);
        discReductionChart.animateXY(1000, 1000, Easing.EaseInOutCubic);
    }

    private void initDiscRemainingLifeBarChart(List<BrakeAnalysisResponse> responses) {
        List<BarEntry> entries = new ArrayList<>();

        int idx = 0;
        for (BrakeAnalysisResponse e : responses) {
            entries.add(new BarEntry(idx++, e.getRemainingLifeCakram().floatValue()));
        }


        BrakeAnalysisXAxisFormatter formatter = new BrakeAnalysisXAxisFormatter();
        formatter.setDates(viewModel.brakeDataDates);

        BarDataSet set = new BarDataSet(entries, getString(R.string.tv_bar_chart_disc_remaining_life_label));
        set.setColors(getResources().getColor(R.color.colorRed));

        BarData data = new BarData(set);
        data.setBarWidth(0.9f);


        discRemainingLifeChart.getXAxis().setValueFormatter(formatter);
        discRemainingLifeChart.setData(data);
        discRemainingLifeChart.animateXY(1000, 1000, Easing.EaseInOutCubic);
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

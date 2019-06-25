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
import id.ac.ui.ft.personalizedobdscan.databinding.ActivityFuelSystemBinding;
import id.ac.ui.ft.personalizedobdscan.models.response.BaseResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.FuelSystemResponse;
import id.ac.ui.ft.personalizedobdscan.util.FuelSystemXAxisFormatter;
import id.ac.ui.ft.personalizedobdscan.viewmodels.FuelSystemViewModel;

public class FuelSystemActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private ActivityFuelSystemBinding binding;
    private FuelSystemViewModel viewModel;
    private SharedPreferences mPrefs;

    private BarChart fuelCostChart;
    private BarChart tripCostChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPrefs = getSharedPreferences(Constants.PREF_FILE_NAME, Context.MODE_PRIVATE);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_fuel_system);
        binding.fuelAnalysisSwipeRefreshLayout.setOnRefreshListener(this);

        initComponent();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onRefresh() {
        getFuelAnalysisData();
    }

    private void initComponent() {
        initViewModel();
        initChart();
        getFuelAnalysisData();
    }

    private void initChart() {
        fuelCostChart = binding.barChartFuelCost;
        tripCostChart = binding.barChartTripCost;

        fuelCostChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        fuelCostChart.getXAxis().setGranularityEnabled(true);
        fuelCostChart.getXAxis().setGranularity(1f);
        fuelCostChart.getDescription().setEnabled(false);
        fuelCostChart.setFitBars(true);

        tripCostChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        tripCostChart.getXAxis().setGranularityEnabled(true);
        tripCostChart.getXAxis().setGranularity(1f);
        tripCostChart.getDescription().setEnabled(false);
        tripCostChart.setFitBars(true);
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(FuelSystemViewModel.class);

        binding.setModel(viewModel);
        binding.setLifecycleOwner(this);
    }

    private void getFuelAnalysisData() {
        final String email = mPrefs.getString(Constants.PREF_KEY_USER_EMAIL, null);
        viewModel.fuelSystemAnalysis(email).
                observe(this, new Observer<BaseResponse<FuelSystemResponse>>() {
                    @Override
                    public void onChanged(@Nullable BaseResponse<FuelSystemResponse> response) {
                        binding.fuelAnalysisSwipeRefreshLayout.setRefreshing(false);
                        if (response != null) {
                            if (response.getIsSuccess()) {
                                initFuelCostLineChart(response.getData());
                                initTripCostLineChart(response.getData());
                            } else {
                                showMessage(response.getMessage());
                            }
                        } else {
                            showMessage(getString(R.string.unknown_error));
                        }
                    }
                });
    }

    private void initFuelCostLineChart(List<FuelSystemResponse> responses) {
        List<BarEntry> entries = new ArrayList<>();

        float idx = 1f;
        for (FuelSystemResponse e : responses) {
            entries.add(new BarEntry(idx, e.getFuelCost().floatValue()));
            idx += 1f;
        }

        BarDataSet set = new BarDataSet(entries, getString(R.string.tv_line_chart_fuel_cost));
        set.setColor(getResources().getColor(R.color.colorPrimaryDark));

        BarData data = new BarData(set);
        data.setBarWidth(0.9f);

        fuelCostChart.getXAxis().setValueFormatter(new FuelSystemXAxisFormatter());
        fuelCostChart.setData(data);
        fuelCostChart.animateXY(1000, 1000, Easing.EaseInOutCubic);;
    }

    private void initTripCostLineChart(List<FuelSystemResponse> responses) {
        List<BarEntry> entries = new ArrayList<>();

        float idx = 1f;
        for (FuelSystemResponse e : responses) {
            entries.add(new BarEntry(idx, e.getTripCost().floatValue()));
            idx += 1f;
        }

        BarDataSet set = new BarDataSet(entries, getString(R.string.tv_line_chart_trip_cost));
        set.setColor(getResources().getColor(R.color.colorPrimaryDark));

        BarData data = new BarData(set);
        data.setBarWidth(0.9f);

        tripCostChart.getXAxis().setValueFormatter(new FuelSystemXAxisFormatter());
        tripCostChart.setData(data);
        tripCostChart.animateXY(1000, 1000, Easing.EaseInOutCubic);;
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

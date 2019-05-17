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

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import id.ac.ui.ft.personalizedobdscan.R;
import id.ac.ui.ft.personalizedobdscan.constant.Constants;
import id.ac.ui.ft.personalizedobdscan.databinding.ActivityFuelSystemBinding;
import id.ac.ui.ft.personalizedobdscan.models.response.BaseResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.FuelSystemResponse;
import id.ac.ui.ft.personalizedobdscan.viewmodels.FuelSystemViewModel;

public class FuelSystemActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private ActivityFuelSystemBinding binding;
    private FuelSystemViewModel viewModel;
    private SharedPreferences mPrefs;

    private LineChart fuelCostChart;
    private LineChart tripCostChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPrefs = getSharedPreferences(Constants.PREF_FILE_NAME, Context.MODE_PRIVATE);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_fuel_system);

        fuelCostChart = binding.lineChartFuelCost;
        tripCostChart = binding.lineChartTripCost;

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
        getFuelAnalysisData();
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
        List<Entry> entries = new ArrayList<>();

        float idx = 0f;
        for (FuelSystemResponse e : responses) {
            entries.add(new Entry(idx, e.getFuelCost().floatValue()));
            idx += 1f;
        }

        LineDataSet set = new LineDataSet(entries, getString(R.string.tv_line_chart_fuel_cost));

        LineData data = new LineData(set);

        fuelCostChart.getXAxis().setGranularity(1f);
        fuelCostChart.getDescription().setEnabled(false);
        fuelCostChart.setData(data);
        fuelCostChart.invalidate();
    }

    private void initTripCostLineChart(List<FuelSystemResponse> responses) {
        List<Entry> entries = new ArrayList<>();

        float idx = 0f;
        for (FuelSystemResponse e : responses) {
            entries.add(new Entry(idx, e.getTripCost().floatValue()));
            idx += 1f;
        }

        LineDataSet set = new LineDataSet(entries, getString(R.string.tv_line_chart_trip_cost));

        LineData data = new LineData(set);

        tripCostChart.getXAxis().setGranularity(1f);
        tripCostChart.getDescription().setEnabled(false);
        tripCostChart.setData(data);
        tripCostChart.invalidate();
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

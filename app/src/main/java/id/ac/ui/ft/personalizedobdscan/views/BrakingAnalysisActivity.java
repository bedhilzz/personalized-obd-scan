package id.ac.ui.ft.personalizedobdscan.views;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

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

public class BrakingAnalysisActivity extends AppCompatActivity {
    private ActivityBrakingAnalysisBinding binding;
    private BrakingAnalysisViewModel viewModel;
    private SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPrefs = getSharedPreferences(Constants.PREF_FILE_NAME, Context.MODE_PRIVATE);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_braking_analysis);

        initComponent();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void initComponent() {
        initViewModel();
        getBrakingAnalysisData();
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(BrakingAnalysisViewModel.class);

        binding.setModel(viewModel);
        binding.setLifecycleOwner(this);
    }

    private void getBrakingAnalysisData() {
        final String email = mPrefs.getString(Constants.PREF_KEY_USER_EMAIL, null);
        viewModel.brakeAnalysis(email).
                observe(this, new Observer<BaseResponse<BrakeAnalysisResponse>>() {
                    @Override
                    public void onChanged(@Nullable BaseResponse<BrakeAnalysisResponse> response) {
                        if (response != null) {
                            if (response.getIsSuccess()) {
                                initAverageReductionExpectancyBarChart(response.getData());
                                initRemainingLifeBarChart(response.getData());
                            } else {
                                showMessage(response.getMessage());
                            }
                        } else {
                            showMessage(getString(R.string.unknown_error));
                        }
                    }
                });
    }

    private void initAverageReductionExpectancyBarChart(List<BrakeAnalysisResponse> responses) {
        List<BarEntry> entries = new ArrayList<>();
        List<String> dates = new ArrayList<>();

        int idx = 0;
        for (BrakeAnalysisResponse e : responses) {
            entries.add(new BarEntry(idx++, e.getAvgReductionExpectancy().floatValue()));
            dates.add(e.getDay());
        }

        viewModel.brakeDataDates = dates;

        BrakeAnalysisXAxisFormatter formatter = new BrakeAnalysisXAxisFormatter();
        formatter.setDates(dates);

        BarDataSet set = new BarDataSet(entries, getString(R.string.tv_bar_chart_braking_analysis_average_reduction));
        set.setColors(getResources().getColor(R.color.colorPrimaryDark));

        BarData data = new BarData(set);
        data.setBarWidth(0.9f);

        BarChart chart = binding.barChartAverageReduction;
        chart.getXAxis().setValueFormatter(formatter);
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.getDescription().setEnabled(false);
        chart.setData(data);
        chart.setFitBars(true);
        chart.invalidate();
    }

    private void initRemainingLifeBarChart(List<BrakeAnalysisResponse> responses) {
        List<BarEntry> entries = new ArrayList<>();

        int idx = 0;
        for (BrakeAnalysisResponse e : responses) {
            entries.add(new BarEntry(idx++, e.getRemainingLife().floatValue()));
        }


        BrakeAnalysisXAxisFormatter formatter = new BrakeAnalysisXAxisFormatter();
        formatter.setDates(viewModel.brakeDataDates);

        BarDataSet set = new BarDataSet(entries, getString(R.string.tv_bar_chart_braking_analysis_remaining_life));
        set.setColors(getResources().getColor(R.color.colorRed));

        BarData data = new BarData(set);
        data.setBarWidth(0.9f);

        BarChart chart = binding.barChartRemainingLife;
        chart.getXAxis().setValueFormatter(formatter);
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.getDescription().setEnabled(false);
        chart.setData(data);
        chart.setFitBars(true);
        chart.invalidate();
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

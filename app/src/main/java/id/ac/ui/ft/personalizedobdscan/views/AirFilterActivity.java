package id.ac.ui.ft.personalizedobdscan.views;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import id.ac.ui.ft.personalizedobdscan.R;
import id.ac.ui.ft.personalizedobdscan.databinding.ActivityAirFilterBinding;
import id.ac.ui.ft.personalizedobdscan.viewmodels.AirFilterViewModel;

public class AirFilterActivity extends AppCompatActivity {
    private AirFilterViewModel viewModel;
    private ActivityAirFilterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_air_filter);

        initComponent();
    }

    private void initComponent() {
        initViewModel();
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(AirFilterViewModel.class);

        binding.setModel(viewModel);
        binding.setLifecycleOwner(this);
    }
}

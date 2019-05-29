package id.ac.ui.ft.personalizedobdscan.views.airfilter;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import id.ac.ui.ft.personalizedobdscan.R;
import id.ac.ui.ft.personalizedobdscan.databinding.ActivityAirFilterBinding;
import id.ac.ui.ft.personalizedobdscan.util.ViewPagerAdapter;
import id.ac.ui.ft.personalizedobdscan.viewmodels.airfilter.AirFilterViewModel;

public class AirFilterActivity extends AppCompatActivity {
    private AirFilterViewModel viewModel;
    private ActivityAirFilterBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_air_filter);
        setupViewPager(binding.pager);
        binding.tabs.setupWithViewPager(binding.pager);

        initComponent();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void initComponent() {
        initViewModel();
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(AirFilterViewModel.class);

        binding.setModel(viewModel);
        binding.setLifecycleOwner(this);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(AirFilterSummaryFragment.newInstance(), getString(R.string.tab_1_text));
        adapter.addFragment(AirFilterStatisticFragment.newInstance(), getString(R.string.tab_2_text));
        viewPager.setAdapter(adapter);
    }
}

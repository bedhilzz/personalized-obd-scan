package id.ac.ui.ft.personalizedobdscan.views.braking;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import id.ac.ui.ft.personalizedobdscan.R;
import id.ac.ui.ft.personalizedobdscan.databinding.ActivityBrakingAnalysisBinding;
import id.ac.ui.ft.personalizedobdscan.util.ViewPagerAdapter;
import id.ac.ui.ft.personalizedobdscan.viewmodels.braking.BrakingAnalysisViewModel;

public class BrakingAnalysisActivity extends AppCompatActivity {
    private ActivityBrakingAnalysisBinding binding;

    private BrakingAnalysisViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_braking_analysis);
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

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(BrakingSummaryFragment.newInstance(), getString(R.string.tab_1_text));
        adapter.addFragment(BrakingStatisticFragment.newInstance(), getString(R.string.tab_2_text));
        viewPager.setAdapter(adapter);
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(BrakingAnalysisViewModel.class);

        binding.setModel(viewModel);
        binding.setLifecycleOwner(this);
    }
}

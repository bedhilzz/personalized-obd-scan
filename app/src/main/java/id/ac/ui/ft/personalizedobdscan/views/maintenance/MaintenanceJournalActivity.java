package id.ac.ui.ft.personalizedobdscan.views.maintenance;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import id.ac.ui.ft.personalizedobdscan.R;
import id.ac.ui.ft.personalizedobdscan.databinding.ActivityMaintenanceJournalBinding;
import id.ac.ui.ft.personalizedobdscan.viewmodels.MaintenanceJournalViewModel;

public class MaintenanceJournalActivity extends AppCompatActivity implements MaintenanceJournalOptionAdapter.MaintenanceJournalOptionListener {

    private ActivityMaintenanceJournalBinding binding;
    private MaintenanceJournalOptionAdapter adapter;
    private MaintenanceJournalViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding = DataBindingUtil.
                setContentView(MaintenanceJournalActivity.this, R.layout.activity_maintenance_journal);

        initComponent();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onMaintenanceJournalOptionClicked(int optionId, String title) {
        Intent intent = new Intent(this, MaintenanceDamageListActivity.class);
        intent.putExtra("option_id", optionId);
        intent.putExtra("option_title", title);
        startActivity(intent);
    }

    private void initComponent() {
        initViewModel();
        initAdapter();
    }

    private void initAdapter() {
        binding.maintenanceJournalOptions.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MaintenanceJournalOptionAdapter(viewModel.getOptions(), this);
        binding.maintenanceJournalOptions.setAdapter(adapter);
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(MaintenanceJournalViewModel.class);

        binding.setUser(viewModel);
        binding.setLifecycleOwner(this);
    }
}

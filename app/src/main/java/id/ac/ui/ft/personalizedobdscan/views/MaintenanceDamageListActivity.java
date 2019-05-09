package id.ac.ui.ft.personalizedobdscan.views;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import id.ac.ui.ft.personalizedobdscan.R;
import id.ac.ui.ft.personalizedobdscan.databinding.ActivityMaintenanceDamageListBinding;
import id.ac.ui.ft.personalizedobdscan.viewmodels.MaintenanceDamageListViewModel;

public class MaintenanceDamageListActivity extends AppCompatActivity implements MaintenanceDamageListAdapter.MaintenanceDamageOptionListener {

    private MaintenanceDamageListViewModel viewModel;
    private ActivityMaintenanceDamageListBinding binding;
    private MaintenanceDamageListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String title = getIntent().getExtras().getString("option_title");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(title);

        binding = DataBindingUtil.
                setContentView(MaintenanceDamageListActivity.this, R.layout.activity_maintenance_damage_list);

        initComponent();
    }

    @Override
    public void onMaintenanceDamageOptionClicked(int optionId, String title) {
        Toast.makeText(MaintenanceDamageListActivity.this, title, Toast.LENGTH_SHORT).show();
    }

    private void initComponent() {
        initViewModel();
        initAdapter();
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(MaintenanceDamageListViewModel.class);

        binding.setUser(viewModel);
        binding.setLifecycleOwner(this);
    }

    private void initAdapter() {
        int optionId = getIntent().getExtras().getInt("option_id");
        viewModel.initDamageList(optionId);

        binding.maintenanceDamageList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MaintenanceDamageListAdapter(viewModel.getDamageList(), this);
        binding.maintenanceDamageList.setAdapter(adapter);
    }
}

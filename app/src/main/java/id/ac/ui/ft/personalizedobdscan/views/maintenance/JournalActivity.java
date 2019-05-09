package id.ac.ui.ft.personalizedobdscan.views.maintenance;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Locale;

import id.ac.ui.ft.personalizedobdscan.R;
import id.ac.ui.ft.personalizedobdscan.databinding.ActivityJournalBinding;
import id.ac.ui.ft.personalizedobdscan.viewmodels.JournalViewModel;

public class JournalActivity extends AppCompatActivity {
    private ActivityJournalBinding binding;
    private JournalViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding = DataBindingUtil.
                setContentView(JournalActivity.this, R.layout.activity_journal);

        initComponent();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void initComponent() {
        initViewModel();
        initDataBinding();
    }

    private void initDataBinding() {
        String key = String.format(Locale.US, "journal_%d_%d_", viewModel.optionId, viewModel.damageId);

        String component = getStringByKey(key + "component");
        String damage = getStringByKey(key + "damage");
        String findings = getStringByKey(key + "findings");
        String impacts = getStringByKey(key + "impacts");
        String causes = getStringByKey(key + "causes");
        String actions = getStringByKey(key + "action");
        String preventions = getStringByKey(key + "prevention");

        binding.tvDamage.setText(damage);
        binding.tvComponent.setText(component);
        binding.tvFindings.setText(findings);
        binding.tvImpacts.setText(impacts);
        binding.tvCauses.setText(causes);
        binding.tvActions.setText(actions);
        binding.tvPreventions.setText(preventions);
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(JournalViewModel.class);
        viewModel.title = getIntent().getExtras().getString("option_title");
        getSupportActionBar().setTitle(viewModel.title);

        viewModel.optionId = getIntent().getExtras().getInt("option_id");
        viewModel.damageId = getIntent().getExtras().getInt("damage_id");
    }

    private String getStringByKey(String key) {
        String retString = key;
        int id = getResources().getIdentifier(key, "string", getPackageName());
        if (id != 0) {
            retString = getString(id);
        }

        return retString;
    }
}

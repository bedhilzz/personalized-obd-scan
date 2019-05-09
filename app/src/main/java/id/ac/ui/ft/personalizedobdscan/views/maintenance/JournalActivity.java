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
    private int optionId;
    private int damageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        optionId = getIntent().getExtras().getInt("option_id");
        damageId = getIntent().getExtras().getInt("damage_id");

        binding = DataBindingUtil.
                setContentView(JournalActivity.this, R.layout.activity_journal);

        initComponent();
    }

    private void initComponent() {
        initDataBinding();
        initViewModel();
    }

    private void initDataBinding() {
        String key = String.format(Locale.US, "journal_%d_%d_", optionId, damageId);

        String findings = getStringByKey(key + "findings");
        String impacts = getStringByKey(key + "impacts");
        String causes = getStringByKey(key + "causes");
        String actions = getStringByKey(key + "action");
        String preventions = getStringByKey(key + "prevention");

        binding.tvFindings.setText(findings);
        binding.tvImpacts.setText(impacts);
        binding.tvCauses.setText(causes);
        binding.tvActions.setText(actions);
        binding.tvPreventions.setText(preventions);
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(JournalViewModel.class);
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

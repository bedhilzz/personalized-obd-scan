package id.ac.ui.ft.personalizedobdscan.views;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import id.ac.ui.ft.personalizedobdscan.R;
import id.ac.ui.ft.personalizedobdscan.databinding.ActivityLauncherBinding;

public class LauncherActivity extends AppCompatActivity {

    private ActivityLauncherBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.
                setContentView(LauncherActivity.this, R.layout.activity_launcher);

        initComponent();
    }

    private void initComponent() {
        initRegisterButton();
        initLoginButton();
    }

    private void initRegisterButton() {
        final Intent  intent = new Intent(this, RegisterActivity.class);
        binding.tvPleaseRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }

    private void initLoginButton() {
        final Intent  intent = new Intent(this, HomeActivity.class);
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
                finish();
            }
        });
    }
}

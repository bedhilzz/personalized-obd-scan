package id.ac.ui.ft.personalizedobdscan.views;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import id.ac.ui.ft.personalizedobdscan.R;
import id.ac.ui.ft.personalizedobdscan.databinding.ActivityLauncherBinding;
import id.ac.ui.ft.personalizedobdscan.models.response.BaseResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.LoginResponse;
import id.ac.ui.ft.personalizedobdscan.viewmodels.LauncherViewModel;

public class LauncherActivity extends AppCompatActivity {

    private ActivityLauncherBinding binding;
    private LauncherViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.
                setContentView(LauncherActivity.this, R.layout.activity_launcher);

        binding.etEmailLogin.setText("dwi@nanda.com");
        binding.etPasswordLogin.setText("dwinanda");

        initComponent();
    }

    private void initComponent() {
        initViewModel();
        initRegisterButton();
        initLoginButton();
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(LauncherViewModel.class);

        binding.setUser(viewModel);
        binding.setLifecycleOwner(this);
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
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doLogin();
            }
        });
    }

    private void doLogin() {
        final Intent  intent = new Intent(this, HomeActivity.class);

        final String email = binding.etEmailLogin.getText().toString();
        final String password = binding.etPasswordLogin.getText().toString();

        viewModel.login(email, password).observe(this, new Observer<BaseResponse<LoginResponse>>() {
            @Override
            public void onChanged(@Nullable BaseResponse<LoginResponse> loginResponse) {
                if (loginResponse != null) {
                    if (loginResponse.getIsSuccess()) {
                        startActivity(intent);
                        finish();
                    } else {
                        showMessage(loginResponse.getMessage());
                    }
                } else {
                    showMessage(getString(R.string.unknown_error));
                }
            }
        });
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

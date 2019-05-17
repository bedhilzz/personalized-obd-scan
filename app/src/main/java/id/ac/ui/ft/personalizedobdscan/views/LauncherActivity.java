package id.ac.ui.ft.personalizedobdscan.views;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import id.ac.ui.ft.personalizedobdscan.R;
import id.ac.ui.ft.personalizedobdscan.constant.Constants;
import id.ac.ui.ft.personalizedobdscan.databinding.ActivityLauncherBinding;
import id.ac.ui.ft.personalizedobdscan.models.response.BaseResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.LoginResponse;
import id.ac.ui.ft.personalizedobdscan.viewmodels.LauncherViewModel;

public class LauncherActivity extends AppCompatActivity {

    private ActivityLauncherBinding binding;
    private LauncherViewModel viewModel;
    private SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPrefs = getSharedPreferences(Constants.PREF_FILE_NAME, Context.MODE_PRIVATE);

        binding = DataBindingUtil.
                setContentView(LauncherActivity.this, R.layout.activity_launcher);

        checkLoginState();
        initComponent();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Constants.PERFORM_REGISTRATION) {
                binding.etEmailLogin.setText(data.getStringExtra("email"));
                binding.etPasswordLogin.setText(data.getStringExtra("password"));
            }
        }
    }

    private void initComponent() {
        initViewModel();
        initRegisterButton();
        initLoginButton();
    }

    private void checkLoginState() {
        String email = mPrefs.getString(Constants.PREF_KEY_USER_EMAIL, null);
        String token = mPrefs.getString(Constants.PREF_KEY_FCM_TOKEN, null);

        if (email != null && token != null) {
            final Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(LauncherViewModel.class);

        binding.setUser(viewModel);
        binding.setLifecycleOwner(this);
    }

    private void initRegisterButton() {
        final Intent intent = new Intent(this, RegisterActivity.class);
        binding.tvPleaseRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(intent, Constants.PERFORM_REGISTRATION);
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
        final Intent intent = new Intent(this, HomeActivity.class);

        final String email = binding.etEmailLogin.getText().toString();
        final String password = binding.etPasswordLogin.getText().toString();

        viewModel.login(email, password).observe(this, new Observer<BaseResponse<LoginResponse>>() {
            @Override
            public void onChanged(@Nullable BaseResponse<LoginResponse> loginResponse) {
                if (loginResponse != null) {
                    if (loginResponse.getIsSuccess()) {
                        LoginResponse userData = loginResponse.getData().get(0);

                        String email = userData.getEmail();
                        String fcmToken = userData.getFcmToken();

                        mPrefs.edit().putString(Constants.PREF_KEY_USER_EMAIL, email).apply();
                        mPrefs.edit().putString(Constants.PREF_KEY_FCM_TOKEN, fcmToken).apply();

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

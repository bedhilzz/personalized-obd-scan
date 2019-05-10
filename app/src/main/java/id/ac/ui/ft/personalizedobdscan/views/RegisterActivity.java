package id.ac.ui.ft.personalizedobdscan.views;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import id.ac.ui.ft.personalizedobdscan.R;
import id.ac.ui.ft.personalizedobdscan.constant.Constants;
import id.ac.ui.ft.personalizedobdscan.databinding.ActivityRegisterBinding;
import id.ac.ui.ft.personalizedobdscan.models.response.BaseResponse;
import id.ac.ui.ft.personalizedobdscan.models.response.RegisterResponse;
import id.ac.ui.ft.personalizedobdscan.viewmodels.RegisterViewModel;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    private RegisterViewModel viewModel;
    private SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPrefs = getSharedPreferences(Constants.PREF_FILE_NAME, Context.MODE_PRIVATE);

        binding = DataBindingUtil.
                setContentView(RegisterActivity.this, R.layout.activity_register);

        initComponent();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void initComponent() {
        initViewModel();
        initSpinner();
        initRegisterButton();
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);

        binding.setUser(viewModel);
        binding.setLifecycleOwner(this);
    }

    private void initSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.
                createFromResource(this, R.array.cars_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.carSelectorRegister.setAdapter(adapter);
    }

    private void initRegisterButton() {
        binding.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestFcmToken();
            }
        });
    }

    private void doRegister() {
        try {
            int sexId = binding.radioSexSelector.getCheckedRadioButtonId();

            final String name = binding.etNameRegister.getText().toString();
            final String email = binding.etEmailRegister.getText().toString();
            final String password = binding.etPasswordRegister.getText().toString();
            final int age = Integer.parseInt(binding.etAgeRegister.getText().toString());
            final String sex = getSex(sexId);
            final String fcmToken = mPrefs.getString(Constants.PREF_KEY_FCM_TOKEN, null);

            viewModel.register(name, email, password, age, sex, fcmToken).
                    observe(this, new Observer<BaseResponse<RegisterResponse>>() {
                @Override
                public void onChanged(@Nullable BaseResponse<RegisterResponse> registerResponse) {
                    if (registerResponse != null) {
                        if (registerResponse.getIsSuccess()) {
                            Intent intent = new Intent();
                            intent.putExtra("email", email);
                            intent.putExtra("password", password);
                            setResult(Activity.RESULT_OK, intent);
                            showMessage(getString(R.string.registration_successful));
                            finish();
                        } else {
                            showMessage(registerResponse.getMessage());
                        }
                    } else {
                        showMessage(getString(R.string.unknown_error));
                    }
                }
            });
        } catch (Exception e) {
            showMessage(getString(R.string.validation_error));
        }
    }

    private String getSex(int sexId) {
        String sex = "";
        switch (sexId) {
            case R.id.sex_male:
                sex = "m";
                break;
            case R.id.sex_female:
                sex = "f";
                break;
        }
        return sex;
    }

    private void requestFcmToken() {
        final String fcmToken = mPrefs.getString(Constants.PREF_KEY_FCM_TOKEN, null);

        if (fcmToken == null) {
            FirebaseInstanceId.getInstance().getInstanceId()
            .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                @Override
                public void onComplete(@NonNull Task<InstanceIdResult> task) {
                    if (task.isSuccessful()) {
                        String fcmToken = task.getResult().getToken();
                        mPrefs.edit().putString(Constants.PREF_KEY_FCM_TOKEN, fcmToken).apply();
                        doRegister();
                    } else {
                        showMessage(getString(R.string.unknown_error));
                    }
                }
            });
        } else {
            doRegister();
        }
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

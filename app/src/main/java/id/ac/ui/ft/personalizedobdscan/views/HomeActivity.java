package id.ac.ui.ft.personalizedobdscan.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;

import id.ac.ui.ft.personalizedobdscan.R;
import id.ac.ui.ft.personalizedobdscan.constant.Constants;
import id.ac.ui.ft.personalizedobdscan.views.maintenance.MaintenanceJournalActivity;

public class HomeActivity extends AppCompatActivity {

    GridLayout mainGrid;
    private SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mainGrid = findViewById(R.id.mainGrid);

        mPrefs = getSharedPreferences(Constants.PREF_FILE_NAME, Context.MODE_PRIVATE);

        initComponent();
    }

    private void initComponent() {
        initAnalysisCardView();
        initMaintenanceJournalCardView();
        initLogoutCardView();
    }

    private void initAnalysisCardView() {
        CardView cardView=(CardView) mainGrid.getChildAt(0);
        final Intent intent = new Intent(this, AnalysisActivity.class);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }

    private void initMaintenanceJournalCardView() {
        CardView cardView=(CardView) mainGrid.getChildAt(2);
        final Intent intent = new Intent(this, MaintenanceJournalActivity.class);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }

    private void initLogoutCardView() {
        CardView cardView=(CardView) mainGrid.getChildAt(3);
        final Intent intent = new Intent(this, LauncherActivity.class);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPrefs.edit().remove(Constants.PREF_KEY_USER_EMAIL).apply();
                mPrefs.edit().remove(Constants.PREF_KEY_FCM_TOKEN).apply();
                startActivity(intent);
                finish();
            }
        });
    }
}

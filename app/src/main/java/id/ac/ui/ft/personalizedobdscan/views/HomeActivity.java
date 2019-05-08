package id.ac.ui.ft.personalizedobdscan.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;

import id.ac.ui.ft.personalizedobdscan.R;

public class HomeActivity extends AppCompatActivity {

    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mainGrid = findViewById(R.id.mainGrid);

        initComponent();
    }

    private void initComponent() {
        initAnalysisCardView();
        initMaintenanceJournalCardView();
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
}

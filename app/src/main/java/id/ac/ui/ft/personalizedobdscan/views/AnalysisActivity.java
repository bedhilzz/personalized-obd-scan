package id.ac.ui.ft.personalizedobdscan.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;

import id.ac.ui.ft.personalizedobdscan.R;

public class AnalysisActivity extends AppCompatActivity {

    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mainGrid = findViewById(R.id.mainGrid);

        initComponent();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void initComponent() {
        initBrakingAnalysisCardView();
    }

    private void initBrakingAnalysisCardView() {
        CardView cardView=(CardView) mainGrid.getChildAt(0);
        final Intent intent = new Intent(this, BrakingAnalysisActivity.class);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }
}

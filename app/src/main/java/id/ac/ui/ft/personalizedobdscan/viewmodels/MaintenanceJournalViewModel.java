package id.ac.ui.ft.personalizedobdscan.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.ArrayList;

import id.ac.ui.ft.personalizedobdscan.models.MaintenanceJournalOption;

public class MaintenanceJournalViewModel extends AndroidViewModel {
    private ArrayList<MaintenanceJournalOption> options;

    public MaintenanceJournalViewModel(@NonNull Application application) {
        super(application);
        initOptions();
    }

    private void initOptions() {
        options = new ArrayList<>();
        options.add(new MaintenanceJournalOption(1, "Brake Disc", "Get service and maintenance journal about brake disc"));
        options.add(new MaintenanceJournalOption(2, "Brake Drum", "Get service and maintenance journal about brake drum"));
        options.add(new MaintenanceJournalOption(3, "Brake Cylinder", "Get service and maintenance journal about brake cylinder"));
    }

    public ArrayList<MaintenanceJournalOption> getOptions() {
        return options;
    }
}

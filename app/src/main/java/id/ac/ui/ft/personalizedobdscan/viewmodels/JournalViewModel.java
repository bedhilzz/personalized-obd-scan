package id.ac.ui.ft.personalizedobdscan.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

public class JournalViewModel extends AndroidViewModel {
    public int damageId;
    public int optionId;
    public String title;

    public JournalViewModel(@NonNull Application application) {
        super(application);
    }
}

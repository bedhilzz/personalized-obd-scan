package id.ac.ui.ft.personalizedobdscan.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.ArrayList;

import id.ac.ui.ft.personalizedobdscan.models.MaintenanceDamage;

public class MaintenanceDamageListViewModel extends AndroidViewModel {
    public String title;
    public int optionId;
    private ArrayList<MaintenanceDamage> damageList;

    public MaintenanceDamageListViewModel(@NonNull Application application) {
        super(application);
    }

    public void initDamageList(int optionId) {
        damageList = new ArrayList<>();
        int i = 0;
        switch (optionId) {
            case 1:
                damageList.add(new MaintenanceDamage(++i, "Uneven wear"));
                damageList.add(new MaintenanceDamage(++i, "Edge pitting"));
                damageList.add(new MaintenanceDamage(++i, "Crack formation (due to high thermal load)"));
                damageList.add(new MaintenanceDamage(++i, "Wear limit"));
                damageList.add(new MaintenanceDamage(++i, "Disregarding wear limit"));
                damageList.add(new MaintenanceDamage(++i, "Cracking due to thermal overloading"));
                damageList.add(new MaintenanceDamage(++i, "Brake thermal destruction"));
                damageList.add(new MaintenanceDamage(++i, "Pad impression on Brake Disc"));
                damageList.add(new MaintenanceDamage(++i, "Edge contact between backplate and Brake Disc"));
                damageList.add(new MaintenanceDamage(++i, "Severe corrosion in the Brake Calliper"));
                damageList.add(new MaintenanceDamage(++i, "Overheated boots"));
                break;
            case 2:
                damageList.add(new MaintenanceDamage(++i, "Scoring and uneven wear (due to dirt penetration)"));
                damageList.add(new MaintenanceDamage(++i, "Scoring at rivet hole intervals"));
                damageList.add(new MaintenanceDamage(++i, "Scorches (heat spotting) and heat cracks"));
                damageList.add(new MaintenanceDamage(++i, "Cold fracture of brake drum"));
                damageList.add(new MaintenanceDamage(++i, "Brake contact surface corrosion"));
                damageList.add(new MaintenanceDamage(++i, "Scoring and uneven wear (due to dirt penetration)"));
                damageList.add(new MaintenanceDamage(++i, "Thermal overloading of the brake linings"));
                damageList.add(new MaintenanceDamage(++i, "Brake lining below the permissible thickness"));
                break;
            case 3:
                damageList.add(new MaintenanceDamage(++i, "Brake cylinder mounting face torn out due to loose screw connection"));
                damageList.add(new MaintenanceDamage(++i, "Corrosion in the brake cylinder"));
                damageList.add(new MaintenanceDamage(++i, "Leakage"));
                break;
        }
    }

    public ArrayList<MaintenanceDamage> getDamageList() {
        return damageList;
    }
}

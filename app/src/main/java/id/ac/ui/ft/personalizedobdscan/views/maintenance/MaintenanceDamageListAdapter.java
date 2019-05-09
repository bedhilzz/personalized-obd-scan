package id.ac.ui.ft.personalizedobdscan.views.maintenance;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import id.ac.ui.ft.personalizedobdscan.R;
import id.ac.ui.ft.personalizedobdscan.models.MaintenanceDamage;

public class MaintenanceDamageListAdapter extends RecyclerView.Adapter<MaintenanceDamageListAdapter.MaintenanceDamageViewHolder> {
    private ArrayList<MaintenanceDamage> damageList;
    private MaintenanceDamageOptionListener context;

    public static class MaintenanceDamageViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        private MaintenanceDamageListAdapter adapter;

        public MaintenanceDamageViewHolder(View view, MaintenanceDamageListAdapter adapter) {
            super(view);
            name = view.findViewById(R.id.maintenance_journal_damage_name);
            this.adapter = adapter;
        }
    }

    public MaintenanceDamageListAdapter(ArrayList<MaintenanceDamage> damageList, MaintenanceDamageOptionListener context) {
        this.damageList = damageList;
        this.context = context;
    }

    @NonNull
    @Override
    public MaintenanceDamageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.maintenance_damage_viewholder, viewGroup, false);
        return new MaintenanceDamageListAdapter.MaintenanceDamageViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull MaintenanceDamageViewHolder maintenanceDamageViewHolder, int i) {
        MaintenanceDamage option = damageList.get(i);

        final int optionId = option.getId();
        final String name = option.getName();

        maintenanceDamageViewHolder.name.setText(name);
        maintenanceDamageViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.onMaintenanceDamageOptionClicked(optionId, name);
            }
        });
    }

    @Override
    public int getItemCount() {
        return damageList.size();
    }

    public interface MaintenanceDamageOptionListener {
        void onMaintenanceDamageOptionClicked(int optionId, String title);
    }
}

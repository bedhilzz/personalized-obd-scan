package id.ac.ui.ft.personalizedobdscan.views.maintenance;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import id.ac.ui.ft.personalizedobdscan.R;
import id.ac.ui.ft.personalizedobdscan.models.MaintenanceJournalOption;

public class MaintenanceJournalOptionAdapter extends RecyclerView.Adapter<MaintenanceJournalOptionAdapter.MaintenanceJournalOptionViewHolder> {
    private ArrayList<MaintenanceJournalOption> options;
    private MaintenanceJournalOptionListener context;

    public static class MaintenanceJournalOptionViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView description;

        private MaintenanceJournalOptionAdapter adapter;

        public MaintenanceJournalOptionViewHolder(View view, MaintenanceJournalOptionAdapter adapter) {
            super(view);
            name = view.findViewById(R.id.maintenance_journal_option_name);
            description = view.findViewById(R.id.maintenance_journal_option_description);
            this.adapter = adapter;
        }

    }

    public MaintenanceJournalOptionAdapter(ArrayList<MaintenanceJournalOption> options, MaintenanceJournalOptionListener context) {
        this.options = options;
        this.context = context;
    }

    @NonNull
    @Override
    public MaintenanceJournalOptionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.maintenance_journal_viewholder, viewGroup, false);
        return new MaintenanceJournalOptionAdapter.MaintenanceJournalOptionViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull MaintenanceJournalOptionViewHolder maintenanceJournalOptionViewHolder, int i) {
        MaintenanceJournalOption option = options.get(i);

        final int optionId = option.getId();
        final String name = option.getName();
        String description = option.getDescription();

        maintenanceJournalOptionViewHolder.name.setText(name);
        maintenanceJournalOptionViewHolder.description.setText(description);

        maintenanceJournalOptionViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.onMaintenanceJournalOptionClicked(optionId, name);
            }
        });
    }

    @Override
    public int getItemCount() {
        return options.size();
    }

    public interface MaintenanceJournalOptionListener {
        void onMaintenanceJournalOptionClicked(int optionId, String title);
    }
}

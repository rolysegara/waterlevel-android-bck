package com.visionet.indigo_waterlevel.modulreservoirdetail.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.visionet.indigo_waterlevel.R;
import com.visionet.indigo_waterlevel.baserealm.reservoirs_level.model.RealmModelReservoirsLevel;
import com.visionet.indigo_waterlevel.modulmap.utils.ReservoirsLevelUtils;

import io.realm.RealmList;

public class StatusLevelAdapter extends RecyclerView.Adapter<StatusLevelAdapter.ViewHolder> {

    private Context context;
    private RealmList<RealmModelReservoirsLevel> realmModelReservoirsLevels;

    public StatusLevelAdapter(RealmList<RealmModelReservoirsLevel> realmModelReservoirsLevels, Context context) {
        super();
        this.realmModelReservoirsLevels = realmModelReservoirsLevels;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_status_level, viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {

        final RealmModelReservoirsLevel realmModelReservoirsLevel = realmModelReservoirsLevels.get(i);

        viewHolder.ivStatusColor.setImageResource(
                ReservoirsLevelUtils.getReservoirLevelColorResIdCircle(
                        realmModelReservoirsLevel.getName()
                ));
        viewHolder.tvStatusName.setText(realmModelReservoirsLevel.getName());
        viewHolder.tvLevel.setText(
                String.valueOf(realmModelReservoirsLevel.getLevelMin() + " - " +
                String.valueOf(realmModelReservoirsLevel.getLevelMax() + " cm "))
        );

    }

    @Override
    public int getItemCount() {
        return realmModelReservoirsLevels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivStatusColor;
        public TextView tvStatusName;
        public TextView tvLevel;

        public ViewHolder(final View itemView) {
            super(itemView);

            ivStatusColor = itemView.findViewById(R.id.iv_status_color);
            tvStatusName = itemView.findViewById(R.id.tv_status_name);
            tvLevel = itemView.findViewById(R.id.tv_level);

        }
    }

}
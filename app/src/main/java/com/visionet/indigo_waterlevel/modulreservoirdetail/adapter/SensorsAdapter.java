package com.visionet.indigo_waterlevel.modulreservoirdetail.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.visionet.indigo_waterlevel.R;
import com.visionet.indigo_waterlevel.baserealm.reservoir.model.RealmModelReservoir;
import com.visionet.indigo_waterlevel.baserealm.reservoir.model.RealmModelReservoirSensor;
import com.visionet.indigo_waterlevel.modulmap.utils.ReservoirsLevelUtils;

import io.realm.RealmList;

public class SensorsAdapter extends RecyclerView.Adapter<SensorsAdapter.ViewHolder> {

    private Context context;
    private RealmList<RealmModelReservoirSensor> realmModelReservoirSensors;

    public SensorsAdapter(RealmList<RealmModelReservoirSensor> realmModelReservoirSensors, Context context) {
        super();
        this.realmModelReservoirSensors = realmModelReservoirSensors;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_sensor, viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {

        final RealmModelReservoirSensor realmModelReservoirSensor = realmModelReservoirSensors.get(i);

        if(i % 2 == 0){
            viewHolder.llSensor.setBackgroundColor(Color.parseColor("#FAFAFA"));
        }else{
            viewHolder.llSensor.setBackgroundColor(Color.parseColor("#F2F2F2"));
        }

        viewHolder.tvSensorName.setText(realmModelReservoirSensor.getSensorName());
        viewHolder.tvLevel.setText(String.format("%.1f", realmModelReservoirSensor.getLastLevel()) + " cm");
        viewHolder.tvStatus.setText(realmModelReservoirSensor.getLastStatus());
        viewHolder.tvStatus.setBackground(ContextCompat.getDrawable(context,
                ReservoirsLevelUtils.getReservoirLevelColorResId(realmModelReservoirSensor.getLastStatus())));

    }

    @Override
    public int getItemCount() {
        return realmModelReservoirSensors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout llSensor;
        public TextView tvSensorName;
        public TextView tvLevel;
        public TextView tvStatus;

        public ViewHolder(final View itemView) {
            super(itemView);

            llSensor = itemView.findViewById(R.id.ll_sensor);
            tvSensorName = itemView.findViewById(R.id.tv_sensor_name);
            tvLevel = itemView.findViewById(R.id.tv_level);
            tvStatus = itemView.findViewById(R.id.tv_status);

        }
    }

}
package com.visionet.indigo_waterlevel.modulreservoir.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.visionet.indigo_waterlevel.R;
import com.visionet.indigo_waterlevel.baseutils.ScreenSizeAndOientation;
import com.visionet.indigo_waterlevel.modulreservoir.model.ModelAdapterReservoir;

import java.util.List;

public class ReservoirAdapter extends RecyclerView.Adapter<ReservoirAdapter.ViewHolder> {

    private Context context;
    private List<ModelAdapterReservoir> modelAdapterReservoirs;
    private SnapHelper snapHelper;

    public ReservoirAdapter(List<ModelAdapterReservoir> modelAdapterReservoirs, Context context) {
        super();
        this.modelAdapterReservoirs = modelAdapterReservoirs;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_reservoir, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);

        snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(viewHolder.recyclerView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {

        final ModelAdapterReservoir modelAdapterReservoir = modelAdapterReservoirs.get(i);

        viewHolder.textViewTitle.setText(modelAdapterReservoir.getTitle());

        viewHolder.recyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);
        viewHolder.recyclerView.setLayoutManager(mLayoutManager);
        ReservoirDetailAdapter reservoirDetailAdapter =
                new ReservoirDetailAdapter(modelAdapterReservoir.getRealmModelReservoirs(), context);
        viewHolder.recyclerView.setAdapter(reservoirDetailAdapter);
        reservoirDetailAdapter.notifyDataSetChanged();

        /*viewHolder.textViewViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ViewAllVoucherActivity.class);
                intent.putExtra("idcategory", String.valueOf(exploreVoucher.getId()));
                v.getContext().startActivity(intent);
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return modelAdapterReservoirs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout rel_out;
        public TextView textViewTitle;
        public RecyclerView recyclerView;

        public ViewHolder(final View itemView) {
            super(itemView);

            rel_out = itemView.findViewById(R.id.rel_out);
            textViewTitle = itemView.findViewById(R.id.tv_title);
            recyclerView = itemView.findViewById(R.id.recyclerview);

        }

    }
}
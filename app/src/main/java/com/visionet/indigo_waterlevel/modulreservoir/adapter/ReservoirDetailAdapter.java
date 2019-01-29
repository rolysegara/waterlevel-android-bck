package com.visionet.indigo_waterlevel.modulreservoir.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.makeramen.roundedimageview.RoundedImageView;
import com.visionet.indigo_waterlevel.R;
import com.visionet.indigo_waterlevel.basecomponentutils.CircleTransform;
import com.visionet.indigo_waterlevel.baserealm.reservoir.model.RealmModelReservoir;
import com.visionet.indigo_waterlevel.baseutils.ScreenSizeAndOientation;
import com.visionet.indigo_waterlevel.modulhome.ImageViewActivity;
import com.visionet.indigo_waterlevel.modulmap.utils.ReservoirsLevelUtils;
import com.visionet.indigo_waterlevel.modulmap.utils.ReservoirsUtils;
import com.visionet.indigo_waterlevel.modulreservoirdetail.DetailReservoirActivity;

import java.util.List;
import java.util.Random;

import io.realm.RealmList;

import static com.visionet.indigo_waterlevel.basehelper.AppData.ID_RESERVOIR;
import static com.visionet.indigo_waterlevel.baseutils.BitmapUtils.convertBase64StringToBitmap;
import static com.visionet.indigo_waterlevel.baseutils.Dimens.getPixels;

public class ReservoirDetailAdapter extends RecyclerView.Adapter<ReservoirDetailAdapter.ViewHolder> {

    private Context context;
    private RealmList<RealmModelReservoir> realmModelReservoirs;

    public ReservoirDetailAdapter(RealmList<RealmModelReservoir> realmModelReservoirs, Context context) {
        super();
        this.realmModelReservoirs = realmModelReservoirs;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_reservoir_detail, viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(v);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailReservoirActivity.class);
                i.putExtra(ID_RESERVOIR, realmModelReservoirs.get(viewHolder.getAdapterPosition()).getId());
                Log.d("ReservoirId", String.valueOf(realmModelReservoirs.get(viewHolder.getAdapterPosition()).getId()) + " - " +
                    String.valueOf(viewHolder.getAdapterPosition()));
                context.startActivity(i);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        String imageUrl = "";
        String reservoirName = "";
        String reservoirLocation = "";
        String reservoirDescription = "";

        final RealmModelReservoir realmModelReservoir = realmModelReservoirs.get(i);

        if(!realmModelReservoir.getRealmModelReservoirPhotos().isEmpty()) {
            Glide.with(context)
                    .asBitmap()
                    .load(realmModelReservoir.getRealmModelReservoirPhotos().get(0).getPhoto())
                    .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(new BitmapImageViewTarget(viewHolder.image) {
                        @Override
                        protected void setResource(Bitmap resource) {

                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            circularBitmapDrawable.setCornerRadius(getPixels(context));
                            viewHolder.image.setImageDrawable(circularBitmapDrawable);
                        }
                    });
            imageUrl = realmModelReservoir.getRealmModelReservoirPhotos().get(0).getPhoto();
        }else{
            Glide.with(context)
                    .load(ContextCompat.getDrawable(context, R.drawable.ic_reservoir))
                    .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(viewHolder.image);
        }

        final String finalImageUrl = imageUrl;
        if(realmModelReservoir.getName() != null) {
            reservoirName = realmModelReservoir.getName();
        }
        if(realmModelReservoir.getAddress() != null) {
            reservoirLocation = realmModelReservoir.getAddress();
        }
        if(realmModelReservoir.getDescription() != null) {
            reservoirDescription = realmModelReservoir.getDescription();
        }
        final String finalReservoirName = reservoirName;
        final String finalReservoirLocation = reservoirLocation;
        final String finalReservoirDescription = reservoirDescription;
        viewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(realmModelReservoir.getName() != null){

                }

                Intent i = new Intent(context, ImageViewActivity.class);
                i.putExtra("imageUrl", finalImageUrl);
                i.putExtra("reservoirName", finalReservoirName);
                i.putExtra("reservoirLocation", finalReservoirLocation);
                i.putExtra("reservoirDescription", finalReservoirDescription);

                View sharedView = viewHolder.image;
                String transitionName = context.getResources().getString(R.string.blue_name);

                ActivityOptions transitionActivityOptions = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation((Activity)context, sharedView, transitionName);
                    context.startActivity(i, transitionActivityOptions.toBundle());
                }else{
                    context.startActivity(i);
                }
            }
        });

        switch (realmModelReservoir.getLastStatusAverage()){
            case "Aman":
                    viewHolder.tv_status.setText(realmModelReservoir.getLastStatusAverage());
                break;
            case "Normal":
                viewHolder.tv_status.setText(realmModelReservoir.getLastStatusAverage());
                break;
            case "Siaga":
                    viewHolder.tv_status.setText(realmModelReservoir.getLastStatusAverage());
                break;
            case "Waspada":
                    viewHolder.tv_status.setText(realmModelReservoir.getLastStatusAverage());
                break;
            case "Bahaya":
                    viewHolder.tv_status.setText(realmModelReservoir.getLastStatusAverage());
                break;
            default:
                viewHolder.tv_status.setText(realmModelReservoir.getLastStatusAverage());
                break;
        }
        viewHolder.tv_status.setBackground(ContextCompat.getDrawable(context,
                ReservoirsLevelUtils.getReservoirLevelColorResId(realmModelReservoir.getLastStatusAverage())));
        viewHolder.tv_name.setText(realmModelReservoir.getName());
        viewHolder.tv_ketinggian.setText(String.format("%.2f", realmModelReservoir.getLastLevelAverage()) + " cm");
        viewHolder.tv_distance.setText(realmModelReservoir.getDistanceDescription());

    }

    @Override
    public int getItemCount() {
        return realmModelReservoirs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public TextView tv_status;
        public TextView tv_name;
        public TextView tv_ketinggian;
        public TextView tv_pump_status;
        public TextView tv_distance;
        public TextView tv_from_your_location;

        public ViewHolder(final View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            tv_status = itemView.findViewById(R.id.tv_status);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_ketinggian = itemView.findViewById(R.id.tv_ketinggian);
            tv_pump_status = itemView.findViewById(R.id.tv_pump_status);
            tv_distance = itemView.findViewById(R.id.tv_distance);
            tv_from_your_location = itemView.findViewById(R.id.tv_from_your_location);

        }
    }

}

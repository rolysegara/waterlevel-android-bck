package com.visionet.indigo_waterlevel.modulreservoirdetail.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.visionet.indigo_waterlevel.R;
import com.visionet.indigo_waterlevel.baserealm.reservoir.model.RealmModelReservoir;
import com.visionet.indigo_waterlevel.baserealm.reservoir.model.RealmModelReservoirPhoto;
import com.visionet.indigo_waterlevel.baserealm.reservoir.model.RealmModelReservoirSensor;
import com.visionet.indigo_waterlevel.modulmap.utils.ReservoirsLevelUtils;
import com.visionet.indigo_waterlevel.modulreservoirdetail.ImageGalleryActivity;

import io.realm.RealmList;

import static com.visionet.indigo_waterlevel.baseutils.Dimens.getPixels;

public class PhotoReservoirAdapter extends RecyclerView.Adapter<PhotoReservoirAdapter.ViewHolder> {

    private Context context;
    private RealmModelReservoir realmModelReservoir;
    private RealmList<RealmModelReservoirPhoto> realmModelReservoirPhotos;

    public PhotoReservoirAdapter(RealmModelReservoir realmModelReservoir, Context context) {
        super();
        this.realmModelReservoir = realmModelReservoir;
        this.realmModelReservoirPhotos = realmModelReservoir.getRealmModelReservoirPhotos();
        this.context = context;
    }

    @Override
    public PhotoReservoirAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_photo_reservoir, viewGroup, false);
        final PhotoReservoirAdapter.ViewHolder viewHolder = new PhotoReservoirAdapter.ViewHolder(v);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ImageGalleryActivity.class);
                i.putExtra("imageUrl", realmModelReservoirPhotos.get(viewHolder.getAdapterPosition()).getPhoto());
                i.putExtra("reservoirName", realmModelReservoir.getName());
                i.putExtra("reservoirLocation", realmModelReservoir.getAddress());

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

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final PhotoReservoirAdapter.ViewHolder viewHolder, final int i) {

        final RealmModelReservoirPhoto realmModelReservoirPhoto = realmModelReservoirPhotos.get(i);

        Glide.with(context)
                .asBitmap()
                .load(realmModelReservoirPhoto.getPhoto())
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

    }

    @Override
    public int getItemCount() {
        return realmModelReservoirPhotos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;

        public ViewHolder(final View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);

        }
    }

}

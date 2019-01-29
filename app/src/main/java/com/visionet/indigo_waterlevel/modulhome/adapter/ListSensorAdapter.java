package com.visionet.indigo_waterlevel.modulhome.adapter;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.skyfishjy.library.RippleBackground;
import com.visionet.indigo_waterlevel.modulhome.ImageViewActivity;
import com.visionet.indigo_waterlevel.R;

import java.util.List;

public class ListSensorAdapter extends RecyclerView.Adapter<ListSensorAdapter.MyViewHolder> {

    private Context context;
    //private List<DataSensor> dataSensors;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView
                    textViewNamaSensor,
                    textViewLokasi,
                    textViewStatus,
                    textViewKetinggianAir,
                    textViewOptions;
        public ImageView imageWarning;
        public RoundedImageView image;
        public RippleBackground rippleBackground;

        public MyViewHolder(View view) {
            super(view);
            textViewNamaSensor = (TextView) view.findViewById(R.id.textViewNamaSensor);
            textViewLokasi = (TextView) view.findViewById(R.id.textViewLokasi);
            textViewStatus = (TextView) view.findViewById(R.id.textViewStatus);
            textViewKetinggianAir = (TextView) view.findViewById(R.id.textViewKetinggianAir);
            image = (RoundedImageView) view.findViewById(R.id.image);
            imageWarning = (ImageView) view.findViewById(R.id.imageWarning);
            rippleBackground=(RippleBackground) view.findViewById(R.id.content);
            textViewOptions = (TextView) view.findViewById(R.id.textViewOptions);
        }
    }


    /*public ListSensorAdapter(Context context, List<DataSensor> dataSensors) {
        this.context = context;
        this.dataSensors = dataSensors;
    }*/

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_sensor, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        //final DataSensor dataSensor = dataSensors.get(position);

        switch ("AMAN"){
            case "AMAN":
                holder.textViewStatus.setTextColor(Color.parseColor("#00C853"));
                holder.imageWarning.setVisibility(View.GONE);
                break;
            case "SIAGA":
                holder.textViewStatus.setTextColor(Color.parseColor("#FF9800"));
                holder.imageWarning.setVisibility(View.GONE);
                break;
            case "BAHAYA":
                holder.textViewStatus.setTextColor(Color.parseColor("#F44336"));
                /*manageBlinkEffect(
                        holder.textViewStatus,
                        Color.parseColor("#FFFFFF"),
                        Color.parseColor("#F44336"));*/

                manageBlinkEffect(holder.imageWarning);

                holder.rippleBackground.startRippleAnimation();

                break;
        }

        holder.textViewNamaSensor.setText("");
        holder.textViewLokasi.setText("");
        holder.textViewStatus.setText("");
        holder.textViewKetinggianAir.setText("");
        holder.image.setImageResource(0);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ImageViewActivity.class);
                i.putExtra("imageId", "");
                i.putExtra("sensorName", "");
                i.putExtra("sensorLocation", "");

                View sharedView = holder.image;
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

        holder.textViewOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu(context, holder.textViewOptions);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    private void manageBlinkEffect(View v) {
        ObjectAnimator anim = ObjectAnimator.ofInt(
                v, "visibility",
                View.VISIBLE,
                View.INVISIBLE,
                View.VISIBLE);
        anim.setDuration(700);
        anim.setEvaluator(new ArgbEvaluator());
        //anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        anim.start();
    }

    /*private void manageBlinkEffect(View v, int color1, int color2) {
        ObjectAnimator anim = ObjectAnimator.ofInt(
                v, "textColor",
                color1,
                color2,
                Color.WHITE);
        anim.setDuration(1000);
        anim.setEvaluator(new ArgbEvaluator());
        //anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        anim.start();
    }*/

    private void showMenu(Context context, View v){
        //creating a popup menu
        PopupMenu popup = new PopupMenu(context, v);
        //inflating menu from xml resource
        popup.inflate(R.menu.menu_image_view);
        //adding click listener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_item_show_detail:
                        //handle menu1 click
                        break;
                    case R.id.menu_item_show_location:
                        //handle menu2 click
                        break;
                    case R.id.menu_item_show_report:
                        //handle menu3 click
                        break;
                }
                return false;
            }
        });
        //displaying the popup
        popup.show();
    }


}

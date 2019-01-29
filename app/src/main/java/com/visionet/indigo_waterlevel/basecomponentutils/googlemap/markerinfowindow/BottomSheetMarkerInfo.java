package com.visionet.indigo_waterlevel.basecomponentutils.googlemap.markerinfowindow;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.thunder413.datetimeutils.DateTimeUtils;
import com.visionet.indigo_waterlevel.R;
import com.visionet.indigo_waterlevel.baserealm.reservoir.model.RealmModelReservoir;
import com.visionet.indigo_waterlevel.modulmap.utils.ReservoirsLevelUtils;

import java.util.Date;

import static com.visionet.indigo_waterlevel.basehelper.DateTimeFormat.FORMAT_DAY_LONG_MONTH_YEAR;
import static com.visionet.indigo_waterlevel.basehelper.DateTimeFormat.FORMAT_WITH_FULL_TIME;
import static com.visionet.indigo_waterlevel.baseutils.Dimens.getScreenDensity;

public class BottomSheetMarkerInfo {

    private static RelativeLayout rlBottomSheet;
    private static RelativeLayout rlBottomSheetMediumDensity;
    private static RelativeLayout rlBottomSheetLowDensity;
    private static BottomSheetBehavior sheetBehavior;

    private static LinearLayout bottomSheetLlDetailItem;
    private static TextView bottomSheetTvDay;
    private static TextView bottomSheetTvTime;
    private static ImageView bottomSheetIvStatusColor;
    private static TextView bottomSheetTvStatus;
    private static TextView bottomSheetTvName;
    private static TextView bottomSheetTvDate;
    private static TextView bottomSheetTvKetinggian;
    private static TextView bottomSheetTvPumpStatus;
    private static TextView bottomSheetTvDistance;
    private static ImageView bottomSheetIvBackgroundStatus;
    private static ImageView bottomSheetIvMarker;

    private static LinearLayout bottomSheetLowDensityLlDetailItem;
    private static TextView bottomSheetLowDensityTvDay;
    private static TextView bottomSheetLowDensityTvTime;
    private static ImageView bottomSheetLowDensityIvStatusColor;
    private static TextView bottomSheetLowDensityTvStatus;
    private static TextView bottomSheetLowDensityTvName;
    private static TextView bottomSheetLowDensityTvDate;
    private static TextView bottomSheetLowDensityTvKetinggian;
    private static TextView bottomSheetLowDensityTvPumpStatus;
    private static TextView bottomSheetLowDensityTvDistance;
    private static ImageView bottomSheetLowDensityIvBackgroundStatus;
    private static ImageView bottomSheetLowDensityIvMarker;

    private static LinearLayout bottomSheetMediumDensityLlDetailItem;
    private static TextView bottomSheetMediumDensityTvDay;
    private static TextView bottomSheetMediumDensityTvTime;
    private static ImageView bottomSheetMediumDensityIvStatusColor;
    private static TextView bottomSheetMediumDensityTvStatus;
    private static TextView bottomSheetMediumDensityTvName;
    private static TextView bottomSheetMediumDensityTvDate;
    private static TextView bottomSheetMediumDensityTvKetinggian;
    private static TextView bottomSheetMediumDensityTvPumpStatus;
    private static TextView bottomSheetMediumDensityTvDistance;
    private static ImageView bottomSheetMediumDensityIvBackgroundStatus;
    private static ImageView bottomSheetMediumDensityIvMarker;

    public static BottomSheetBehavior initViewBottomSheetMarkerInfo(Context context, View rootView, RealmModelReservoir realmModelReservoir){
        rlBottomSheet = rootView.findViewById(R.id.rl_bottom_sheet);
        rlBottomSheetMediumDensity = rootView.findViewById(R.id.rl_bottom_sheet_medium_density);
        rlBottomSheetLowDensity = rootView.findViewById(R.id.rl_bottom_sheet_low_density);

        bottomSheetLlDetailItem = rootView.findViewById(R.id.bottom_sheet_ll_detail_item);
        bottomSheetTvDay = rootView.findViewById(R.id.bottom_sheet_tv_day);
        bottomSheetTvTime = rootView.findViewById(R.id.bottom_sheet_tv_time);
        bottomSheetIvStatusColor = rootView.findViewById(R.id.bottom_sheet_iv_status_color);
        bottomSheetTvStatus = rootView.findViewById(R.id.bottom_sheet_tv_status);
        bottomSheetTvName = rootView.findViewById(R.id.bottom_sheet_tv_name);
        bottomSheetTvDate = rootView.findViewById(R.id.bottom_sheet_tv_date);
        bottomSheetTvKetinggian = rootView.findViewById(R.id.bottom_sheet_tv_ketinggian);
        bottomSheetTvPumpStatus = rootView.findViewById(R.id.bottom_sheet_tv_pump_status);
        bottomSheetTvDistance = rootView.findViewById(R.id.bottom_sheet_tv_distance);
        bottomSheetIvBackgroundStatus = rootView.findViewById(R.id.bottom_sheet_iv_background_status);
        bottomSheetIvMarker = rootView.findViewById(R.id.bottom_sheet_iv_marker);

        bottomSheetLowDensityLlDetailItem = rootView.findViewById(R.id.bottom_sheet_low_density_ll_detail_item);
        bottomSheetLowDensityTvDay = rootView.findViewById(R.id.bottom_sheet_low_density_tv_day);
        bottomSheetLowDensityTvTime = rootView.findViewById(R.id.bottom_sheet_low_density_tv_time);
        bottomSheetLowDensityIvStatusColor = rootView.findViewById(R.id.bottom_sheet_low_density_iv_status_color);
        bottomSheetLowDensityTvStatus = rootView.findViewById(R.id.bottom_sheet_low_density_tv_status);
        bottomSheetLowDensityTvName = rootView.findViewById(R.id.bottom_sheet_low_density_tv_name);
        bottomSheetLowDensityTvDate = rootView.findViewById(R.id.bottom_sheet_low_density_tv_date);
        bottomSheetLowDensityTvKetinggian = rootView.findViewById(R.id.bottom_sheet_low_density_tv_ketinggian);
        bottomSheetLowDensityTvPumpStatus = rootView.findViewById(R.id.bottom_sheet_low_density_tv_pump_status);
        bottomSheetLowDensityTvDistance = rootView.findViewById(R.id.bottom_sheet_low_density_tv_distance);
        bottomSheetLowDensityIvBackgroundStatus = rootView.findViewById(R.id.bottom_sheet_low_density_iv_background_status);
        bottomSheetLowDensityIvMarker = rootView.findViewById(R.id.bottom_sheet_low_density_iv_marker);

        bottomSheetMediumDensityLlDetailItem = rootView.findViewById(R.id.bottom_sheet_medium_density_ll_detail_item);
        bottomSheetMediumDensityTvDay = rootView.findViewById(R.id.bottom_sheet_medium_density_tv_day);
        bottomSheetMediumDensityTvTime = rootView.findViewById(R.id.bottom_sheet_medium_density_tv_time);
        bottomSheetMediumDensityIvStatusColor = rootView.findViewById(R.id.bottom_sheet_medium_density_iv_status_color);
        bottomSheetMediumDensityTvStatus = rootView.findViewById(R.id.bottom_sheet_medium_density_tv_status);
        bottomSheetMediumDensityTvName = rootView.findViewById(R.id.bottom_sheet_medium_density_tv_name);
        bottomSheetMediumDensityTvDate = rootView.findViewById(R.id.bottom_sheet_medium_density_tv_date);
        bottomSheetMediumDensityTvKetinggian = rootView.findViewById(R.id.bottom_sheet_medium_density_tv_ketinggian);
        bottomSheetMediumDensityTvPumpStatus = rootView.findViewById(R.id.bottom_sheet_medium_density_tv_pump_status);
        bottomSheetMediumDensityTvDistance = rootView.findViewById(R.id.bottom_sheet_medium_density_tv_distance);
        bottomSheetMediumDensityIvBackgroundStatus = rootView.findViewById(R.id.bottom_sheet_medium_density_iv_background_status);
        bottomSheetMediumDensityIvMarker = rootView.findViewById(R.id.bottom_sheet_medium_density_iv_marker);

        if(getScreenDensity(context) > 3) {
            rlBottomSheet.setVisibility(View.VISIBLE);
            rlBottomSheetMediumDensity.setVisibility(View.GONE);
            rlBottomSheetLowDensity.setVisibility(View.GONE);
            sheetBehavior = BottomSheetBehavior.from(rlBottomSheet);
        }else
        if(getScreenDensity(context) > 2) {
            rlBottomSheet.setVisibility(View.GONE);
            rlBottomSheetMediumDensity.setVisibility(View.VISIBLE);
            rlBottomSheetLowDensity.setVisibility(View.GONE);
            sheetBehavior = BottomSheetBehavior.from(rlBottomSheetMediumDensity);
        }else{
            rlBottomSheet.setVisibility(View.GONE);
            rlBottomSheetMediumDensity.setVisibility(View.GONE);
            rlBottomSheetLowDensity.setVisibility(View.VISIBLE);
            sheetBehavior = BottomSheetBehavior.from(rlBottomSheetLowDensity);
        }
        sheetBehavior.setPeekHeight(0);
        sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED: {

                    }
                    break;
                    case BottomSheetBehavior.STATE_COLLAPSED: {

                    }
                    break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });

        Log.d("Density", String.valueOf(getScreenDensity(context)));

        //bottomSheetTvDay;
        bottomSheetTvTime.setText(DateTimeUtils.formatWithPattern(new Date(), FORMAT_WITH_FULL_TIME));
        bottomSheetIvStatusColor.setImageResource(
                ReservoirsLevelUtils.getReservoirLevelColorResIdCircle(
                        realmModelReservoir.getLastStatusAverage()));
        bottomSheetTvStatus.setText(realmModelReservoir.getLastStatusAverage());
        bottomSheetTvName.setText(realmModelReservoir.getName());
        bottomSheetTvDate.setText(DateTimeUtils.formatWithPattern(new Date(), FORMAT_DAY_LONG_MONTH_YEAR));
        bottomSheetTvKetinggian.setText(String.format("%.2f", realmModelReservoir.getLastLevelAverage()) + " cm");
        //bottomSheetTvPumpStatus;
        bottomSheetTvDistance.setText(realmModelReservoir.getDistanceDescription());
        bottomSheetIvBackgroundStatus.setImageResource(
                ReservoirsLevelUtils.getReservoirLevelResourceMarkerInfoBackground(
                        context,
                        realmModelReservoir.getLastStatusAverage()));
        bottomSheetIvMarker.setImageResource(
                ReservoirsLevelUtils.getReservoirLevelResourceMarkerInfoStroke(
                        context,
                        realmModelReservoir.getLastStatusAverage()));

        //bottomSheetLowDensityTvDay;
        bottomSheetLowDensityTvTime.setText(DateTimeUtils.formatWithPattern(new Date(), FORMAT_WITH_FULL_TIME));
        bottomSheetLowDensityIvStatusColor.setImageResource(
                ReservoirsLevelUtils.getReservoirLevelColorResIdCircle(
                        realmModelReservoir.getLastStatusAverage()));
        bottomSheetLowDensityTvStatus.setText(realmModelReservoir.getLastStatusAverage());
        bottomSheetLowDensityTvName.setText(realmModelReservoir.getName());
        bottomSheetLowDensityTvDate.setText(DateTimeUtils.formatWithPattern(new Date(), FORMAT_DAY_LONG_MONTH_YEAR));
        bottomSheetLowDensityTvKetinggian.setText(String.format("%.2f", realmModelReservoir.getLastLevelAverage()) + " cm");
        //bottomSheetLowDensityTvPumpStatus;
        bottomSheetLowDensityTvDistance.setText(realmModelReservoir.getDistanceDescription());
        bottomSheetLowDensityIvBackgroundStatus.setImageResource(
                ReservoirsLevelUtils.getReservoirLevelResourceMarkerInfoBackground(
                        context,
                        realmModelReservoir.getLastStatusAverage()));
        bottomSheetLowDensityIvMarker.setImageResource(
                ReservoirsLevelUtils.getReservoirLevelResourceMarkerInfoStroke(
                        context,
                        realmModelReservoir.getLastStatusAverage()));

        //bottomSheetMediumDensityTvDay;
        bottomSheetMediumDensityTvTime.setText(DateTimeUtils.formatWithPattern(new Date(), FORMAT_WITH_FULL_TIME));
        bottomSheetMediumDensityIvStatusColor.setImageResource(
                ReservoirsLevelUtils.getReservoirLevelColorResIdCircle(
                        realmModelReservoir.getLastStatusAverage()));
        bottomSheetMediumDensityTvStatus.setText(realmModelReservoir.getLastStatusAverage());
        bottomSheetMediumDensityTvName.setText(realmModelReservoir.getName());
        bottomSheetMediumDensityTvDate.setText(DateTimeUtils.formatWithPattern(new Date(), FORMAT_DAY_LONG_MONTH_YEAR));
        bottomSheetMediumDensityTvKetinggian.setText(String.format("%.2f", realmModelReservoir.getLastLevelAverage()) + " cm");
        //bottomSheetMediumDensityTvPumpStatus;
        bottomSheetMediumDensityTvDistance.setText(realmModelReservoir.getDistanceDescription());
        bottomSheetMediumDensityIvBackgroundStatus.setImageResource(
                ReservoirsLevelUtils.getReservoirLevelResourceMarkerInfoBackground(
                        context,
                        realmModelReservoir.getLastStatusAverage()));
        bottomSheetMediumDensityIvMarker.setImageResource(
                ReservoirsLevelUtils.getReservoirLevelResourceMarkerInfoStroke(
                        context,
                        realmModelReservoir.getLastStatusAverage()));

        return sheetBehavior;
    }
}

package com.visionet.indigo_waterlevel.modulmap.utils;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;

import com.visionet.indigo_waterlevel.R;
import com.visionet.indigo_waterlevel.baseapp.BaseApplication;
import com.visionet.indigo_waterlevel.baserealm.reservoirs_level.controller.RealmControllerReservoirsLevel;
import com.visionet.indigo_waterlevel.baserealm.reservoirs_level.model.RealmModelReservoirsLevel;

import io.realm.RealmList;
import io.realm.RealmResults;

public class ReservoirsLevelUtils {
    public static void saveReservoirsLevel(
            RealmModelReservoirsLevel realmModelReservoirsLevel){

        RealmControllerReservoirsLevel realmControllerReservoirsLevel =
                new RealmControllerReservoirsLevel(new BaseApplication());
        realmControllerReservoirsLevel.insertReservoirsLevel(realmModelReservoirsLevel);
    }

    public static void saveReservoirsLevelWithoutRealmTransaction(
            RealmModelReservoirsLevel realmModelReservoirsLevel){

        RealmControllerReservoirsLevel realmControllerReservoirsLevel =
                new RealmControllerReservoirsLevel(new BaseApplication());
        realmControllerReservoirsLevel.insertReservoirsLevelWithoutRealmTransaction(realmModelReservoirsLevel);
    }

    public static RealmResults<RealmModelReservoirsLevel> getReservoirsLevel(){

        RealmControllerReservoirsLevel realmControllerReservoirsLevel =
                new RealmControllerReservoirsLevel(new BaseApplication());
        return realmControllerReservoirsLevel.getReservoirsLevel();
    }

    public static RealmResults<RealmModelReservoirsLevel> getReservoirsLevelByReservoirId(int reservoirId){

        RealmControllerReservoirsLevel realmControllerReservoirsLevel =
                new RealmControllerReservoirsLevel(new BaseApplication());
        return realmControllerReservoirsLevel.getReservoirsLevelByReservoirId(reservoirId);
    }

    public static RealmResults<RealmModelReservoirsLevel> getReservoirsLevelByLevelMinDescending(int reservoirId){
        RealmControllerReservoirsLevel realmControllerReservoirsLevel =
                new RealmControllerReservoirsLevel(new BaseApplication());
        return realmControllerReservoirsLevel.getReservoirsLevelByLevelMinDescending(reservoirId);
    }

    public static RealmModelReservoirsLevel getReservoirsLevelByLevelAverage(int reservoirId, double levelAverage){
        RealmControllerReservoirsLevel realmControllerReservoirsLevel =
                new RealmControllerReservoirsLevel(new BaseApplication());
        return realmControllerReservoirsLevel.getReservoirsLevelByLevelAverage(reservoirId, levelAverage);
    }

    public static void saveReservoirsLevel(
            RealmList<RealmModelReservoirsLevel> realmModelReservoirsLevels){

        RealmControllerReservoirsLevel realmControllerReservoirsLevel =
                new RealmControllerReservoirsLevel(new BaseApplication());
        realmControllerReservoirsLevel.insertReservoirsLevel(realmModelReservoirsLevels);
    }

    public static void saveReservoirsLevelWithoutRealmTransaction(
            RealmList<RealmModelReservoirsLevel> realmModelReservoirsLevels){

        RealmControllerReservoirsLevel realmControllerReservoirsLevel =
                new RealmControllerReservoirsLevel(new BaseApplication());
        realmControllerReservoirsLevel.insertReservoirsLevelWithoutRealmTransaction(realmModelReservoirsLevels);
    }

    public static void clearReservoirsLevel(){
        RealmControllerReservoirsLevel realmControllerReservoirsLevel =
                new RealmControllerReservoirsLevel(new BaseApplication());
        realmControllerReservoirsLevel.clearAll(RealmModelReservoirsLevel.class);
    }

    public static RealmList<RealmModelReservoirsLevel> getReservoirLevelListByLevelMinDescending(int reservoirId){
        RealmResults<RealmModelReservoirsLevel> reservoirResult =
                ReservoirsLevelUtils.getReservoirsLevelByLevelMinDescending(reservoirId);

        if(reservoirResult != null) {
            RealmList<RealmModelReservoirsLevel> realmModelReservoirsLevels = new RealmList<>();
            for (RealmModelReservoirsLevel reservoirsLevel : reservoirResult) {
                realmModelReservoirsLevels.add(reservoirsLevel);
            }
            return realmModelReservoirsLevels;
        }else{
            return null;
        }
    }

    public static String getReservoirLevelStatusName(
            int reservoirId, double levelAverage){
            RealmModelReservoirsLevel realmModelReservoirsLevel =
                    getReservoirsLevelByLevelAverage(reservoirId, levelAverage);

            if(realmModelReservoirsLevel != null){
                return realmModelReservoirsLevel.getName();
            }else{
                return "";
            }
    }

    public static int getReservoirLevelColorResId(String levelStatus){

        switch (levelStatus.toLowerCase()){
            case "aman":
                return R.drawable.bg_status_green_gradient;
            case "normal":
                return R.drawable.bg_status_green_gradient;
            case "siaga":
                return R.drawable.bg_status_yellow_gradient;
            case "siaga i":
                return R.drawable.bg_status_yellow_gradient;
            case "siaga ii":
                return R.drawable.bg_status_yellow_gradient;
            case "siaga iii":
                return R.drawable.bg_status_yellow_gradient;
            case "siaga 1":
                return R.drawable.bg_status_yellow_gradient;
            case "siaga 2":
                return R.drawable.bg_status_yellow_gradient;
            case "siaga 3":
                return R.drawable.bg_status_yellow_gradient;
            case "waspada":
                return R.drawable.bg_status_orange_gradient;
            case "awas":
                return R.drawable.bg_status_red_gradient;
            case "bahaya":
                return R.drawable.bg_status_red_gradient;
            default:
                return R.drawable.bg_status_grey_gradient;
        }

    }

    public static int getReservoirLevelColorResIdCircle(String levelStatus){

        switch (levelStatus.toLowerCase()){
            case "aman":
                return R.drawable.bg_status_circle_green_gradient;
            case "normal":
                return R.drawable.bg_status_circle_green_gradient;
            case "siaga":
                return R.drawable.bg_status_circle_yellow_gradient;
            case "siaga i":
                return R.drawable.bg_status_circle_yellow_gradient;
            case "siaga ii":
                return R.drawable.bg_status_circle_yellow_gradient;
            case "siaga iii":
                return R.drawable.bg_status_circle_yellow_gradient;
            case "siaga 1":
                return R.drawable.bg_status_circle_yellow_gradient;
            case "siaga 2":
                return R.drawable.bg_status_circle_yellow_gradient;
            case "siaga 3":
                return R.drawable.bg_status_circle_yellow_gradient;
            case "waspada":
                return R.drawable.bg_status_circle_orange_gradient;
            case "awas":
                return R.drawable.bg_status_circle_red_gradient;
            case "bahaya":
                return R.drawable.bg_status_circle_red_gradient;
            default:
                return R.drawable.bg_status_circle_grey_gradient;
        }

    }

    public static int getReservoirLevelColor(String levelStatus){

        switch (levelStatus.toLowerCase()){
            case "aman":
                return Color.parseColor("#93D665");
            case "normal":
                return Color.parseColor("#93D665");
            case "siaga":
                return Color.parseColor("#F4EC78");
            case "siaga i":
                return Color.parseColor("#F4EC78");
            case "siaga ii":
                return Color.parseColor("#F4EC78");
            case "siaga iii":
                return Color.parseColor("#F4EC78");
            case "siaga 1":
                return Color.parseColor("#F4EC78");
            case "siaga 2":
                return Color.parseColor("#F4EC78");
            case "siaga 3":
                return Color.parseColor("#F4EC78");
            case "waspada":
                return Color.parseColor("#FF9B5B");
            case "awas":
                return Color.parseColor("#FC4C49");
            case "bahaya":
                return Color.parseColor("#FC4C49");
            default:
                return Color.parseColor("#cccccc");
        }

    }

    public static int getReservoirLevelResourceMarker(Context context, String levelStatus){

        switch (levelStatus.toLowerCase()){
            case "aman":
                return R.drawable.ic_marker_reservoir_aman;
            case "normal":
                return R.drawable.ic_marker_reservoir_aman;
            case "siaga":
                return R.drawable.ic_marker_reservoir_siaga;
            case "siaga i":
                return R.drawable.ic_marker_reservoir_siaga;
            case "siaga ii":
                return R.drawable.ic_marker_reservoir_siaga;
            case "siaga iii":
                return R.drawable.ic_marker_reservoir_siaga;
            case "siaga 1":
                return R.drawable.ic_marker_reservoir_siaga;
            case "siaga 2":
                return R.drawable.ic_marker_reservoir_siaga;
            case "siaga 3":
                return R.drawable.ic_marker_reservoir_siaga;
            case "waspada":
                return R.drawable.ic_marker_reservoir_waspada;
            case "awas":
                return R.drawable.ic_marker_reservoir_bahaya;
            case "bahaya":
                return R.drawable.ic_marker_reservoir_bahaya;
            default:
                return R.drawable.ic_marker_reservoir_unavailable;
        }

    }

    public static int getReservoirLevelResourceMarkerInfoStroke(Context context, String levelStatus){

        switch (levelStatus.toLowerCase()){
            case "aman":
                return R.drawable.ic_marker_reservoir_aman_stroke;
            case "normal":
                return R.drawable.ic_marker_reservoir_aman_stroke;
            case "siaga":
                return R.drawable.ic_marker_reservoir_siaga_stroke;
            case "siaga i":
                return R.drawable.ic_marker_reservoir_siaga_stroke;
            case "siaga ii":
                return R.drawable.ic_marker_reservoir_siaga_stroke;
            case "siaga iii":
                return R.drawable.ic_marker_reservoir_siaga_stroke;
            case "siaga 1":
                return R.drawable.ic_marker_reservoir_siaga_stroke;
            case "siaga 2":
                return R.drawable.ic_marker_reservoir_siaga_stroke;
            case "siaga 3":
                return R.drawable.ic_marker_reservoir_siaga_stroke;
            case "waspada":
                return R.drawable.ic_marker_reservoir_waspada_stroke;
            case "awas":
                return R.drawable.ic_marker_reservoir_bahaya_stroke;
            case "bahaya":
                return R.drawable.ic_marker_reservoir_bahaya_stroke;
            default:
                return R.drawable.ic_marker_reservoir_unavailable_stroke;
        }

    }

    public static int getReservoirLevelResourceMarkerInfoBackground(Context context, String levelStatus){

        switch (levelStatus.toLowerCase()){
            case "aman":
                return R.drawable.bg_marker_info_reservoir_aman;
            case "normal":
                return R.drawable.bg_marker_info_reservoir_aman;
            case "siaga":
                return R.drawable.bg_marker_info_reservoir_siaga;
            case "siaga i":
                return R.drawable.bg_marker_info_reservoir_siaga;
            case "siaga ii":
                return R.drawable.bg_marker_info_reservoir_siaga;
            case "siaga iii":
                return R.drawable.bg_marker_info_reservoir_siaga;
            case "siaga 1":
                return R.drawable.bg_marker_info_reservoir_siaga;
            case "siaga 2":
                return R.drawable.bg_marker_info_reservoir_siaga;
            case "siaga 3":
                return R.drawable.bg_marker_info_reservoir_siaga;
            case "waspada":
                return R.drawable.bg_marker_info_reservoir_waspada;
            case "awas":
                return R.drawable.bg_marker_info_reservoir_bahaya;
            case "bahaya":
                return R.drawable.bg_marker_info_reservoir_bahaya;
            default:
                return R.drawable.bg_marker_info_reservoir_unavailable;
        }

    }

    public static long getReservoirLevelPercentageLevel(int reservoirId, double levelAverage){
        long percentage = 0;
        RealmList<RealmModelReservoirsLevel> realmModelReservoirsLevels =
                getReservoirLevelListByLevelMinDescending(reservoirId);

        if(realmModelReservoirsLevels != null && !realmModelReservoirsLevels.isEmpty()){
            if(levelAverage <= realmModelReservoirsLevels.get(0).getLevelMin()) {
                return percentage = Math.round(levelAverage / realmModelReservoirsLevels.get(0).getLevelMin() * 90);
            }else{
                return percentage = 95;
            }
        }

        return percentage;
    }

}
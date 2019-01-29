package com.visionet.indigo_waterlevel.basecomponentutils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.typeface.IIcon;
import com.visionet.indigo_waterlevel.R;
import com.visionet.indigo_waterlevel.basehelper.AppData;
import com.yarolegovich.lovelydialog.LovelyInfoDialog;
import com.yarolegovich.lovelydialog.LovelyProgressDialog;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;
import com.yarolegovich.lovelydialog.LovelyTextInputDialog;

public class DialogBuildersUtils {

    private Context context;
    private LovelyProgressDialog progressDialog;

    public DialogBuildersUtils(Context context){
        this.context = context;

        setProgressDialog();
    }

    /*public LovelyStandardDialog informationDialog(){
        return new LovelyStandardDialog(context, LovelyStandardDialog.ButtonLayout.VERTICAL)
                .setTopColorRes(R.color.indigo)
                .setButtonsColorRes(R.color.darkDeepOrange)
                .setIcon(R.drawable.ic_star_border_white_36dp)
                .setTitle(R.string.rate_title)
                .setMessage(R.string.rate_message)
                .setPositiveButton(android.R.string.ok, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "positive clicked", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .show();
    }*/

    public void informationDialog(String messsage){
        new LovelyInfoDialog(context)
                .setTopColorRes(R.color.colorTopInformation)
                .setIcon(getIconic(FontAwesome.Icon.faw_info_circle))
                .setCancelable(false)
                .setTitle(R.string.information)
                .setMessage(messsage)
                .show();
    }

    public LovelyStandardDialog informationDialogButton(String message){
        return new LovelyStandardDialog(context, LovelyStandardDialog.ButtonLayout.VERTICAL)
                .setTopColorRes(R.color.colorTopInformation)
                .setButtonsColorRes(R.color.indigo)
                .setIcon(getIconic(FontAwesome.Icon.faw_info_circle))
                .setTitle(R.string.information)
                .setMessage(message)
                .setCancelable(false);
    }

    public LovelyStandardDialog questionDialogButton(String title, String message){
        return new LovelyStandardDialog(context, LovelyStandardDialog.ButtonLayout.VERTICAL)
                .setTopColorRes(R.color.colorTopQuestion)
                .setButtonsColorRes(R.color.colorButtonQuestion)
                .setIcon(getIconic(FontAwesome.Icon.faw_question_circle))
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false);
    }

    public LovelyTextInputDialog textInputDialog(String title, String message, Drawable icon){
        return new LovelyTextInputDialog(context, R.style.EditTextTintTheme)
                .setTopColorRes(R.color.darkDeepOrange)
                .setTitle(title)
                .setMessage(message)
                .setIcon(icon);
    }

    public void errorDialog(String message){
        new LovelyInfoDialog(context)
                .setTopColorRes(R.color.colorTopError)
                .setIcon(getIconic(FontAwesome.Icon.faw_exclamation_circle))
                .setCancelable(false)
                .setTitle(R.string.error)
                .setMessage(message)
                .show();
    }

    public void errorDialog(String title, String message){
        new LovelyInfoDialog(context)
                .setTopColorRes(R.color.colorTopError)
                .setIcon(getIconic(FontAwesome.Icon.faw_exclamation_circle))
                .setCancelable(false)
                .setTitle(title)
                .setMessage(message)
                .show();
    }

    public AlertDialog watchedDialog(final Context context, String message, final Class reservoirList){
        /*final Dialog dialog;
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_info_bookmarked);
        //dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
        dialog.setCancelable(false);

        *//*TextView tvMessage = dialog.findViewById(R.id.tv_message);
        Button btSeeAll = dialog.findViewById(R.id.bt_see_all);
        TextView tvGoBack = dialog.findViewById(R.id.tv_go_back);

        tvMessage.setText(message);
        tvGoBack.setText(Html.fromHtml("<u>" + context.getString(R.string.go_back) + "</u>"));

        btSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, reservoirList);
                intent.putExtra(AppData.FROM_BOOKMARK, true);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });

        tvGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });*//*

        return dialog;*/

        ViewGroup viewGroup = ((Activity) context).findViewById(android.R.id.content);
        final View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_info_bookmarked, viewGroup, false);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialogView);

        final AlertDialog alertDialog = builder.create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;

        TextView tvMessage = dialogView.findViewById(R.id.tv_message);
        Button btSeeAll = dialogView.findViewById(R.id.bt_see_all);
        TextView tvGoBack = dialogView.findViewById(R.id.tv_go_back);

        tvMessage.setText(message);
        tvGoBack.setText(Html.fromHtml("<u>" + context.getString(R.string.go_back) + "</u>"));

        btSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, reservoirList);
                intent.putExtra(AppData.FROM_BOOKMARK, true);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });

        tvGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        return alertDialog;
    }

    public AlertDialog unwatchedDialog(final Context context, String message){
        /*final Dialog dialog;
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_info_unbookmarked);
        //dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
        dialog.setCancelable(false);

        *//*TextView tvMessage = dialog.findViewById(R.id.tv_message);
        TextView tvGoBack = dialog.findViewById(R.id.tv_go_back);

        tvMessage.setText(message);
        tvGoBack.setText(Html.fromHtml("<u>" + context.getString(R.string.go_back) + "</u>"));

        tvGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });*//*

        return dialog;*/

        ViewGroup viewGroup = ((Activity) context).findViewById(android.R.id.content);
        final View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_info_unbookmarked, viewGroup, false);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialogView);

        final AlertDialog alertDialog = builder.create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;

        TextView tvMessage = dialogView.findViewById(R.id.tv_message);
        TextView tvGoBack = dialogView.findViewById(R.id.tv_go_back);

        tvMessage.setText(message);
        tvGoBack.setText(Html.fromHtml("<u>" + context.getString(R.string.go_back) + "</u>"));

        tvGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        return alertDialog;
    }

    /*public void singleChoiceDialog(){
        ArrayAdapter<DonationOption> adapter = new DonationAdapter(this, loadDonationOptions());
        new LovelyChoiceDialog(this)
                .setTopColorRes(R.color.darkGreen)
                .setTitle(R.string.donate_title)
                .setIcon(R.drawable.ic_local_atm_white_36dp)
                .setMessage(R.string.donate_message)
                .setItems(adapter, new LovelyChoiceDialog.OnItemSelectedListener<DonationOption>() {
                    @Override
                    public void onItemSelected(int position, DonationOption item) {
                        Toast.makeText(context, getString(R.string.you_donated, item.amount),Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    public void multiChoiceDialog(){
        String[] items = getResources().getStringArray(R.array.food);
        new LovelyChoiceDialog(context, R.style.CheckBoxTintTheme)
                .setTopColorRes(R.color.darkRed)
                .setTitle(R.string.order_food_title)
                .setIcon(R.drawable.ic_food_white_36dp)
                .setItemsMultiChoice(items, new LovelyChoiceDialog.OnItemsSelectedListener<String>() {
                    @Override
                    public void onItemsSelected(List<Integer> positions, List<String> items) {
                        Toast.makeText(MainActivity.this,
                                getString(R.string.you_ordered, TextUtils.join("\n", items)),
                                Toast.LENGTH_SHORT)
                                .show();
                    }
                })
                .setConfirmButtonText(R.string.confirm)
                .show();
    }

    public void textInputDialog(){
        new LovelyTextInputDialog(context, R.style.EditTextTintTheme)
                .setTopColorRes(R.color.darkDeepOrange)
                .setTitle(R.string.text_input_title)
                .setMessage(R.string.text_input_message)
                .setIcon(R.drawable.ic_assignment_white_36dp)
                .setInputFilter(R.string.text_input_error_message, new LovelyTextInputDialog.TextFilter() {
                    @Override
                    public boolean check(String text) {
                        return text.matches("\\w+");
                    }
                })
                .setConfirmButton(android.R.string.ok, new LovelyTextInputDialog.OnTextInputConfirmListener() {
                    @Override
                    public void onTextInputConfirmed(String text) {
                        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }*/

    private void setProgressDialog(){
        progressDialog = new LovelyProgressDialog(context)
                .setIcon(getIconic(FontAwesome.Icon.faw_mixcloud))
                .setTitle("Loading..")
                .setTitleGravity(Gravity.CENTER)
                .setCancelable(false)
                .setTopColorRes(R.color.colorTopProgressDialog);
    }

    public void setProgressDialog(String processName){
        progressDialog = new LovelyProgressDialog(context)
                .setIcon(getIconic(FontAwesome.Icon.faw_mixcloud))
                .setTitle(processName)
                .setTitleGravity(Gravity.CENTER)
                .setCancelable(false)
                .setTopColorRes(R.color.colorTopProgressDialog);
    }

    public LovelyProgressDialog progressDialog(){
        progressDialog.dismiss();
        return progressDialog;
    }

    /*public void customDialog(){
        new LovelyCustomDialog(context)
                .setView(R.layout.item_donate_option)
                .setTopColorRes(R.color.darkDeepOrange)
                .setTitle(R.string.text_input_title)
                .setMessage(R.string.text_input_message)
                .setIcon(R.drawable.ic_assignment_white_36dp)
                .configureView( ... )
                .setListener(R.id.ld_btn_yes,  ... )
                .setInstanceStateManager( ... )
                .show();
    }*/

    public IconicsDrawable getIconic(IIcon icon){
        return new IconicsDrawable(context)
                .icon(icon)
                .color(Color.WHITE)
                .sizeDp(36);
    }

}

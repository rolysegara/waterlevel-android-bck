package com.visionet.indigo_waterlevel.basecomponentutils.datedialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.github.thunder413.datetimeutils.DateTimeUtils;
import com.visionet.indigo_waterlevel.BuildConfig;
import com.visionet.indigo_waterlevel.R;

import java.lang.reflect.Field;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DateDialog {

    private Context context;
    private String datePattern;
    private DatePickerDialog datePickerDialog;

    private DateDialogListener listener;

    public DateDialog(Context context, String datePattern){
        this.context = context;
        this.datePattern = datePattern;
    }

    public void setDateDialogListener(DateDialogListener listener) {
        this.listener = listener;
    }

    public void showDateDialogFullDay(){
        Calendar newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                listener.onDateSelected(DateTimeUtils.formatWithPattern(newDate.getTime(), datePattern));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE,
                context.getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == DialogInterface.BUTTON_NEGATIVE) {
                            listener.onCancelled();
                            dialog.dismiss();
                        }
                    }
                });

        datePickerDialog.show();
    }

    public void showDateDialogMonthOnly(){
        Calendar newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(context, AlertDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                listener.onDateSelected(DateTimeUtils.formatWithPattern(newDate.getTime(), datePattern));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE,
            context.getString(R.string.cancel), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (which == DialogInterface.BUTTON_NEGATIVE) {
                        listener.onCancelled();
                        dialog.dismiss();
                    }
                }
            });

        if (android.os.Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
            datePickerDialog.getDatePicker().findViewById(context.getResources().getIdentifier("day", "id", "android")).setVisibility(View.GONE);
        }
        datePickerDialog.show();
    }

    public void showDateDialogYearOnly(){
        Calendar newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(context, AlertDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                listener.onDateSelected(DateTimeUtils.formatWithPattern(newDate.getTime(), datePattern));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE,
                context.getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == DialogInterface.BUTTON_NEGATIVE) {
                            listener.onCancelled();
                            dialog.dismiss();
                        }
                    }
                });

        if (android.os.Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
            datePickerDialog.getDatePicker().findViewById(context.getResources().getIdentifier("day", "id", "android")).setVisibility(View.GONE);
            datePickerDialog.getDatePicker().findViewById(context.getResources().getIdentifier("month", "id", "android")).setVisibility(View.GONE);
        }
        datePickerDialog.show();
    }

    public interface DateDialogListener {
        public void onDateSelected(String dateFormatted);
        public void onCancelled();
    }
}

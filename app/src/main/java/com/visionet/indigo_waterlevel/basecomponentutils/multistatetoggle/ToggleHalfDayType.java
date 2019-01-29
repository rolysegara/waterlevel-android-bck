package com.visionet.indigo_waterlevel.basecomponentutils.multistatetoggle;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;

import com.visionet.indigo_waterlevel.R;

import static com.visionet.indigo_waterlevel.basecomponentutils.chart.constant.ConstantValue.AFTER_NOON;
import static com.visionet.indigo_waterlevel.basecomponentutils.chart.constant.ConstantValue.BEFORE_NOON;

public class ToggleHalfDayType implements View.OnClickListener {

    private int position = 0;
    private Context context;
    private Button buttons[];

    private ToggleListener listener;

    public ToggleHalfDayType(Context context, Button buttons[]){
        this.context = context;
        this.buttons = buttons;

        this.listener = null;

        for(int i = 0; i < buttons.length; i++){
            buttons[i].setOnClickListener(this);
        }
    }

    public void setToggleListener(ToggleListener listener) {
        this.listener = listener;
    }

    public void setSelectedToggle(int position){
        setToggle(position);
    }

    public void setSelectedToggle(String periodType){
        switch (periodType){
            case BEFORE_NOON: {
                setToggle(0);
            }
            break;
            case AFTER_NOON: {
                setToggle(1);
            }
            break;
        }
    }


    @Override
    public void onClick(View v) {

        if(v.getId() == buttons[0].getId()){
            setToggle(0);
            listener.onToggleSwitched(BEFORE_NOON);
        }else
        if(v.getId() == buttons[1].getId()){
            setToggle(1);
            listener.onToggleSwitched(AFTER_NOON);
        }
    }

    private void setToggle(int position){
        for(int i = 0; i < buttons.length; i++){
            buttons[i].setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));
            buttons[i].setTextColor(Color.parseColor("#DCDCDC"));
        }

        this.position = position;
        buttons[position].setBackground(ContextCompat.getDrawable(context, R.drawable.bg_button_report_am_pm));
        buttons[position].setTextColor(ContextCompat.getColor(context, R.color.white));
    }

    public interface ToggleListener {
        public void onToggleSwitched(String periodType);
    }

    public int getPosition() {
        return position;
    }
}
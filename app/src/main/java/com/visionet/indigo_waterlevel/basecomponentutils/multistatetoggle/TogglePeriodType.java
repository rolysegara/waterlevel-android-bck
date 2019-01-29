package com.visionet.indigo_waterlevel.basecomponentutils.multistatetoggle;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.visionet.indigo_waterlevel.R;

import static com.visionet.indigo_waterlevel.basecomponentutils.chart.constant.ConstantValue.DAILY;
import static com.visionet.indigo_waterlevel.basecomponentutils.chart.constant.ConstantValue.MONTHLY;
import static com.visionet.indigo_waterlevel.basecomponentutils.chart.constant.ConstantValue.WEEKLY;
import static com.visionet.indigo_waterlevel.basecomponentutils.chart.constant.ConstantValue.YEARLY;

public class TogglePeriodType implements View.OnClickListener {

    private Context context;
    private Button buttons[];

    private ToggleListener listener;

    public TogglePeriodType(Context context, Button buttons[]){
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
            case DAILY: {
                setToggle(0);
            }
            break;
            case WEEKLY: {
                setToggle(1);
            }
            break;
            case MONTHLY: {
                setToggle(2);
            }
            break;
            case YEARLY: {
                setToggle(3);
            }
            break;
        }
    }


    @Override
    public void onClick(View v) {

        if(v.getId() == buttons[0].getId()){
            setToggle(0);
            listener.onToggleSwitched(DAILY);
        }else
        if(v.getId() == buttons[1].getId()){
            setToggle(1);
            listener.onToggleSwitched(WEEKLY);
        }else
        if(v.getId() == buttons[2].getId()){
            setToggle(2);
            listener.onToggleSwitched(MONTHLY);
        }else
        if(v.getId() == buttons[3].getId()){
            setToggle(3);
            listener.onToggleSwitched(YEARLY);
        }
    }

    private void setToggle(int position){
        for(int i = 0; i < buttons.length; i++){
            buttons[i].setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));
            buttons[i].setTextColor(Color.parseColor("#DCDCDC"));
        }

        buttons[position].setBackground(ContextCompat.getDrawable(context, R.drawable.bg_button_report));
        buttons[position].setTextColor(ContextCompat.getColor(context, R.color.white));
    }

    public interface ToggleListener {
        public void onToggleSwitched(String periodType);
    }

}

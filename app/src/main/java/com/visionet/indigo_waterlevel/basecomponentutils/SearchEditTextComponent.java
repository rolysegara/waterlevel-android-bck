package com.visionet.indigo_waterlevel.basecomponentutils;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.visionet.indigo_waterlevel.R;
import com.visionet.indigo_waterlevel.baseutils.SoftKeyboardUtils;

import java.util.Timer;
import java.util.TimerTask;

public class SearchEditTextComponent {

    Context context;
    EditText edSearchView;
    ImageView ivSearch;
    private Timer timer;

    private SearchEditTextComponentListener listener;

    public SearchEditTextComponent (Context context, EditText edSearchView, ImageView ivSearch) {
        this.context = context;
        this.edSearchView = edSearchView;
        this.ivSearch = ivSearch;
        this.listener = null;

        this.edSearchView.addTextChangedListener(searchTextWatcher);
        this.ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearKeyword();
            }
        });
    }

    public void setSearchEditTextComponentListener(SearchEditTextComponentListener listener) {
        this.listener = listener;
    }

    private void clearKeyword(){
        if(ivSearch != null && edSearchView != null) {
            ivSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edSearchView.setText("");
                    SoftKeyboardUtils.hideSoftKeyboard(edSearchView, context);
                    ivSearch.setImageResource(R.drawable.ic_location);

                    if (listener != null){
                        listener.onClearKeyword();
                    }
                }
            });
        }
    }

    private TextWatcher searchTextWatcher = new TextWatcher() {
        @Override
        public void afterTextChanged(final Editable arg0) {

            ivSearch.setImageResource(R.drawable.ic_close);

            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    ((Activity)context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            SoftKeyboardUtils.hideSoftKeyboard(edSearchView, context);

                            String keyword = edSearchView.getText().toString().trim();
                            if(!keyword.isEmpty() && keyword.length() > 2) {
                                if (listener != null){
                                    listener.onSearch(edSearchView.getText().toString().trim());
                                }
                            }

                            if(edSearchView.getText().toString().trim().length() == 0){
                                ivSearch.setImageResource(R.drawable.ic_location);

                                if (listener != null){
                                    listener.onClearKeyword();
                                }
                            }
                        }
                    });
                }
            }, 1000);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (timer != null) {
                timer.cancel();
            }
        }
    };

    public interface SearchEditTextComponentListener {
        public void onClearKeyword();
        public void onSearch(String keyword);
    }

}

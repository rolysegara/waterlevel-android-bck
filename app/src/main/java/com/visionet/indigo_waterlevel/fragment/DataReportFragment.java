package com.visionet.indigo_waterlevel.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.visionet.indigo_waterlevel.R;

public class DataReportFragment extends Fragment {

    public DataReportFragment() {
        // Required empty public constructor
    }

    public static DataReportFragment newInstance(String param1, String param2) {
        DataReportFragment fragment = new DataReportFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report, container, false);
    }
}

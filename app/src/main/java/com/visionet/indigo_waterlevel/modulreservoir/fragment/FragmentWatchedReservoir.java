package com.visionet.indigo_waterlevel.modulreservoir.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.visionet.indigo_waterlevel.R;
import com.visionet.indigo_waterlevel.baseapp.BaseFragment;
import com.visionet.indigo_waterlevel.baserealm.reservoir.model.RealmModelReservoir;
import com.visionet.indigo_waterlevel.baseutils.LocationUtils;
import com.visionet.indigo_waterlevel.modulmap.utils.ReservoirsUtils;
import com.visionet.indigo_waterlevel.modulreservoir.adapter.ReservoirAdapter;
import com.visionet.indigo_waterlevel.modulreservoir.adapter.ReservoirDetailAdapter;
import com.visionet.indigo_waterlevel.modulreservoir.model.ModelAdapterReservoir;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmResults;

public class FragmentWatchedReservoir extends BaseFragment{

    RecyclerView recyclerView;
    LinearLayout llEmptyWatched;
    View rootView;
    ReservoirDetailAdapter reservoirDetailAdapter;
    RealmResults<RealmModelReservoir> realmModelReservoirs;

    public FragmentWatchedReservoir() {
        // Required empty public constructor
    }

    public static FragmentWatchedReservoir newInstance(String param1, String param2) {
        FragmentWatchedReservoir fragment = new FragmentWatchedReservoir();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        realmModelReservoirs = ReservoirsUtils.getReservoirsBookmarked(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_reservoir_watched, container, false);

        initRecyclerView();

        return rootView;
    }

    private void initRecyclerView(){
        llEmptyWatched = rootView.findViewById(R.id.empty_watched);
        recyclerView = rootView.findViewById(R.id.recyclerview);

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);

        if(!realmModelReservoirs.isEmpty()) {
            RealmList<RealmModelReservoir> realmModelReservoirsList = new RealmList<>();
            for(RealmModelReservoir realmModelReservoir : realmModelReservoirs){
                realmModelReservoirsList.add(realmModelReservoir);
            }

            reservoirDetailAdapter = new ReservoirDetailAdapter(realmModelReservoirsList, getContext());
            recyclerView.setAdapter(reservoirDetailAdapter);
            llEmptyWatched.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }else{
            llEmptyWatched.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
            //tvKoordinat.setText(LocationUtils.getStringLocationPreferences(getContext())[0] +","+
            //        LocationUtils.getStringLocationPreferences(getContext())[1]);
            Log.d("VisibleFragment", "Visible..");
        }
    }

    @Override
    public void onResume(){
        super.onResume();

        initRecyclerView();
        Log.d("ResumeFragment", "Resume..");
    }

}

package com.visionet.indigo_waterlevel.modulreservoir.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.visionet.indigo_waterlevel.R;
import com.visionet.indigo_waterlevel.baseapp.BaseFragment;
import com.visionet.indigo_waterlevel.basecomponentutils.SearchEditTextComponent;
import com.visionet.indigo_waterlevel.baserealm.reservoir.model.RealmModelReservoir;
import com.visionet.indigo_waterlevel.modulhome.HomeActivity;
import com.visionet.indigo_waterlevel.modulhome.listener.OnHomeLocationReceivedListener;
import com.visionet.indigo_waterlevel.modulmap.utils.ReservoirsUtils;
import com.visionet.indigo_waterlevel.modulreservoir.adapter.ReservoirAdapter;
import com.visionet.indigo_waterlevel.modulreservoir.model.ModelAdapterReservoir;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmResults;

public class FragmentAllReservoir extends BaseFragment implements OnHomeLocationReceivedListener {

    RecyclerView recyclerView;
    LinearLayout llEmptyReservoir;
    View rootView;
    ReservoirAdapter reservoirAdapter;

    EditText edSearch;
    ImageView ivSearch;

    public FragmentAllReservoir() {
    }

    public static FragmentAllReservoir newInstance(String param1, String param2) {
        FragmentAllReservoir fragment = new FragmentAllReservoir();
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
        rootView = inflater.inflate(R.layout.fragment_reservoir_all, container, false);

        RealmResults<RealmModelReservoir> realmModelReservoirs = ReservoirsUtils.getReservoirs();
        initRecyclerView(realmModelReservoirs, false);
        initSearchView();

        Activity activity = getActivity();
        if (activity instanceof HomeActivity) {
            ((HomeActivity) getActivity()).setOnHomeLocationReceivedListener(this);
        }

        return rootView;
    }

    private void initSearchView() {
        edSearch = rootView.findViewById(R.id.search_edittext);
        ivSearch = rootView.findViewById(R.id.iv_search);

        SearchEditTextComponent searchEditTextComponent =
                new SearchEditTextComponent(getContext(), edSearch, ivSearch);
        searchEditTextComponent.setSearchEditTextComponentListener(
                new SearchEditTextComponent.SearchEditTextComponentListener() {
                    @Override
                    public void onClearKeyword() {
                        RealmResults<RealmModelReservoir> realmModelReservoirs = ReservoirsUtils.getReservoirs();
                        initRecyclerView(realmModelReservoirs, false);
                    }

                    @Override
                    public void onSearch(String keyword) {
                        RealmResults<RealmModelReservoir> realmModelReservoirs =
                                ReservoirsUtils.getReservoirsByName(keyword);
                        initRecyclerView(realmModelReservoirs, true);
                    }
                });
    }

    private void initRecyclerView(RealmResults<RealmModelReservoir> realmModelReservoirs, boolean isSearching) {
        llEmptyReservoir = rootView.findViewById(R.id.empty_reservoir);
        recyclerView = rootView.findViewById(R.id.recyclerview);

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);

        ModelAdapterReservoir modelAdapterReservoir;
        List<ModelAdapterReservoir> modelAdapterReservoirs = new ArrayList<>();
        if (!realmModelReservoirs.isEmpty()) {
            if (isSearching) {
                RealmList<RealmModelReservoir> resultReservoir = new RealmList<>();

                for (RealmModelReservoir realmModelReservoir : realmModelReservoirs) {
                    resultReservoir.add(realmModelReservoir);
                }

                modelAdapterReservoir = new ModelAdapterReservoir();
                modelAdapterReservoir.setTitle(getString(R.string.result));
                modelAdapterReservoir.setRealmModelReservoirs(resultReservoir);
                modelAdapterReservoirs.add(modelAdapterReservoir);
            } else {
                RealmList<RealmModelReservoir> nearestReservoir = new RealmList<>();
                RealmList<RealmModelReservoir> othersReservoir = new RealmList<>();

                for (RealmModelReservoir realmModelReservoir : realmModelReservoirs) {
                    nearestReservoir.add(realmModelReservoir);
                    break;
                }
                modelAdapterReservoir = new ModelAdapterReservoir();
                modelAdapterReservoir.setTitle(getString(R.string.nearest));
                modelAdapterReservoir.setRealmModelReservoirs(nearestReservoir);
                modelAdapterReservoirs.add(modelAdapterReservoir);

                if (ReservoirsUtils.getReservoirs().size() > 1) {
                    int i = 0;
                    for (RealmModelReservoir realmModelReservoir : realmModelReservoirs) {
                        if (i != 0) {
                            othersReservoir.add(realmModelReservoir);
                        }
                        i++;
                    }
                }
                modelAdapterReservoir = new ModelAdapterReservoir();
                modelAdapterReservoir.setTitle(getString(R.string.others));
                modelAdapterReservoir.setRealmModelReservoirs(othersReservoir);
                modelAdapterReservoirs.add(modelAdapterReservoir);
            }

            reservoirAdapter = new ReservoirAdapter(modelAdapterReservoirs, getContext());
            recyclerView.setAdapter(reservoirAdapter);

            llEmptyReservoir.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            llEmptyReservoir.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onHomeLocationReceived(String latitude, String longitude) {
        /*tvKoordinat.setText(latitude+","+
                longitude);*/
        Log.d("GPSLocation_fragment", latitude + "," +
                longitude);
    }
}

package com.visionet.indigo_waterlevel.modulreservoir.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.visionet.indigo_waterlevel.R;
import com.visionet.indigo_waterlevel.baseapp.BaseFragment;
import com.visionet.indigo_waterlevel.basefragmentadapter.FragmentAdapter;
import com.visionet.indigo_waterlevel.basehelper.AppData;
import com.visionet.indigo_waterlevel.modulhome.HomeActivity;
import com.visionet.indigo_waterlevel.modulhome.listener.OnHomeLocationReceivedListener;

public class ReservoirFragment extends BaseFragment implements OnHomeLocationReceivedListener {

    TextView tvKoordinat;
    boolean fromBookmark = false;

    public ReservoirFragment() {
        // Required empty public constructor
    }

    public static ReservoirFragment newInstance(boolean fromBookmark) {
        ReservoirFragment fragment = new ReservoirFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(AppData.FROM_BOOKMARK, fromBookmark);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_reservoir, container, false);
        tvKoordinat = rootView.findViewById(R.id.tv_koordinat);

        if(getArguments() != null){
            fromBookmark = (boolean) getArguments().getSerializable(AppData.FROM_BOOKMARK);
        }

        Activity activity = getActivity();
        if(activity instanceof HomeActivity){
            ((HomeActivity)getActivity()).setOnHomeLocationReceivedListener(this);
        }

        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        TabLayout tabs = (TabLayout) rootView.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        if(fromBookmark){
            tabs.setScrollPosition(1,0f,true);
            viewPager.setCurrentItem(1);
        }

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_list_reservoir, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_filter:
                Toast.makeText(getActivity(), "Filter", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        FragmentAdapter adapter = new FragmentAdapter(getChildFragmentManager());
        adapter.addFragment(new FragmentAllReservoir(), getString(R.string.all));
        adapter.addFragment(new FragmentWatchedReservoir(), getString(R.string.watched));
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onHomeLocationReceived(String latitude, String longitude) {
        tvKoordinat.setText(latitude+","+
                longitude);
        Log.d("GPSLocation_fragment", latitude+","+
                longitude);
    }
}

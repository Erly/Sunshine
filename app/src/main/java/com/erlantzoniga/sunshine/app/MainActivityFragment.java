package com.erlantzoniga.sunshine.app;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ArrayList<String> forecastEntries = new ArrayList<>(14);
        forecastEntries.add(0, "Today - Sunny - 88/63");
        forecastEntries.add(1, "Tomorrow - Foggy - 70/46");
        forecastEntries.add(2, "Weds - Cloudy - 72/63");
        forecastEntries.add(3, "Thurs - Rainy - 64/51");
        forecastEntries.add(4, "Fri - Foggy - 70/46");
        forecastEntries.add(5, "Sat - Sunny - 76/68");
        forecastEntries.add(6, "Sun - Sunny - 88/63");
        forecastEntries.add(7, "Mon - Cloudy - 73/64");
        forecastEntries.add(8, "Tue - Sunny - 89/68");
        forecastEntries.add(9, "Weds - Cloudy - 75/63");
        forecastEntries.add(10, "Thurs - Rainy - 70/60");
        forecastEntries.add(11, "Fri - Rainy - 68/56");
        forecastEntries.add(12, "Sat - Foggy - 73/66");
        forecastEntries.add(13, "Sun - Sunny - 88/63");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item_forecast, R.id.list_item_forecast_textview, forecastEntries);

        ListView forecastListView = (ListView) rootView.findViewById(R.id.listview_forecast);
        forecastListView.setAdapter(arrayAdapter);
        return rootView;
    }
}

package com.erlantzoniga.sunshine.app;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


/**
 * A placeholder fragment containing a simple view.
 */
public class ForecastFragment extends Fragment {

    public ForecastFragment() {
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

        new FetchWeatherTask().execute("http://api.openweathermap.org/data/2.5/forecast/daily?q=48980,es&cnt=10&mode=json&units=metric");

        return rootView;
    }

    public class FetchWeatherTask extends AsyncTask<String, Void, String> {

        private final String LOG_TAG = FetchWeatherTask.class.getSimpleName();

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            String forecastJsonStr = null;

            try {
                URL url = new URL(params[0]);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                if (inputStream == null) return null;

                reader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuffer buffer = new StringBuffer();
                String  line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) return null;
                forecastJsonStr = buffer.toString();
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error", e);
                return null;
            } finally {
                if (urlConnection != null) urlConnection.disconnect();
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }
            return forecastJsonStr;
        }

        @Override
        protected void onPostExecute(String result) {

        }
    }
}

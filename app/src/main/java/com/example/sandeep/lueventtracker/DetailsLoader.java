package com.example.sandeep.lueventtracker;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by sandeep on 3/15/2017.
 */

public class DetailsLoader extends AsyncTaskLoader<List<EventDetails>> {

    private String mUrl;
    public DetailsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
        Log.v("DetailsLoader", "In onStartLoading");
    }

    @Override
    public List<EventDetails> loadInBackground() {
        Log.v("DetailsLoader", "In loadInBackground");
        // Perform the network request, parse the response, and extract a list of events.
        List<EventDetails> events = Utils.fetchEventDetails(mUrl);
        return events;
    }
}

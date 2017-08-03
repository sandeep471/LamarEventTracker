package com.example.sandeep.lueventtracker;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by sandeep on 3/15/2017.
 */

public class EventsLoader extends AsyncTaskLoader<List<EventList>> {

    private String mUrl;
    public EventsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
        Log.v("EventsLoader", "In onStartLoading");
    }

    @Override
    public List<EventList> loadInBackground() {
        Log.v("EventsLoader", "In loadInBackground");
        // Perform the network request, parse the response, and extract a list of events.
        List<EventList> events = Utils.fetchEventList(mUrl);
        return events;
    }
}

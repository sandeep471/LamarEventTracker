package com.example.sandeep.lueventtracker;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<EventList>> {

    private static final int EVENT_LOADER_ID = 1;
    public static final String LOG_TAG = MainActivity.class.getName();

    private TextView mEmptyStateTextView;
    private ProgressBar spinner;
    private EventAdapter eventAdapter;
    private String mUrl = "http://events.lamar.edu/calendar-listing.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView eventListView = (ListView) findViewById(R.id.list);
        spinner = (ProgressBar) findViewById(R.id.loading_spinner);
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_text_view);

        eventListView.setEmptyView(mEmptyStateTextView);

        eventAdapter = new EventAdapter(this, new ArrayList<EventList>());
        eventListView.setAdapter(eventAdapter);

        eventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EventList eventList = eventAdapter.getItem(position);
                String url = eventList.getmUrl();

                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });

        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if (isConnected) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(EVENT_LOADER_ID, null, this);
        } else {
            spinner.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet);
        }
    }

    @Override
    public Loader<List<EventList>> onCreateLoader(int id, Bundle args) {
        return new EventsLoader(this, mUrl);
    }

    @Override
    public void onLoadFinished(Loader<List<EventList>> loader, List<EventList> data) {
        Log.v(LOG_TAG, "In onLoadFinished");

        spinner.setVisibility(View.GONE);
        mEmptyStateTextView.setText(R.string.no_events);
        eventAdapter.clear();

        if (data != null && !data.isEmpty()) {
            eventAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<EventList>> loader) {
        Log.v(LOG_TAG, "In onLoaderReset");
        eventAdapter.clear();
    }

}

package com.example.sandeep.lueventtracker;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class DetailsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<EventDetails>>{

    private static final int EVENT_LOADER_ID = 2;
    private String url;
    private String destination;
    private ProgressBar spinner;
    private LinearLayout dateLayout;
    private LinearLayout timeLayout;
    private LinearLayout locationLayout;
    private LinearLayout detailsLayout;
    private LinearLayout contactLayout;
    private TextView title;
    private TextView date;
    private TextView time;
    private TextView location;
    private TextView details;
    private TextView contact;
    private Button navigate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        Log.v("DetailsActivity ", url);

        spinner = (ProgressBar) findViewById(R.id.loading_details_spinner);

        dateLayout = (LinearLayout) findViewById(R.id.date_layout);
        timeLayout = (LinearLayout) findViewById(R.id.time_layout);
        locationLayout = (LinearLayout) findViewById(R.id.location_layout);
        detailsLayout = (LinearLayout) findViewById(R.id.details_layout);
        contactLayout = (LinearLayout) findViewById(R.id.contacts_layout);

        dateLayout.setVisibility(View.GONE);
        timeLayout.setVisibility(View.GONE);
        locationLayout.setVisibility(View.GONE);
        detailsLayout.setVisibility(View.GONE);
        contactLayout.setVisibility(View.GONE);

        title = (TextView) findViewById(R.id.event_detail_title);
        date = (TextView) findViewById(R.id.date_value);
        time = (TextView) findViewById(R.id.time_value);
        location = (TextView) findViewById(R.id.location_value);
        details = (TextView) findViewById(R.id.details_value);
        contact = (TextView) findViewById(R.id.contact_value);

        navigate = (Button) findViewById(R.id.navigation_button);

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
            //mEmptyStateTextView.setText(R.string.no_internet);
            title.setText("Problem fetching data");
        }
    }

    @Override
    public Loader<List<EventDetails>> onCreateLoader(int id, Bundle args) {
        return new DetailsLoader(this, url);
    }

    @Override
    public void onLoadFinished(Loader<List<EventDetails>> loader, List<EventDetails> data) {
        Log.v("DetailsActivity", "In onLoadFinished");

        spinner.setVisibility(View.GONE);

        if (data != null && !data.isEmpty()) {

            title.setText(data.get(0).getmTitle());

            if(data.get(0).getmDate() != null){
                date.setText(data.get(0).getmDate());
                dateLayout.setVisibility(View.VISIBLE);
            }
            if(data.get(0).getmTime() != null){
                time.setText(data.get(0).getmTime());
                timeLayout.setVisibility(View.VISIBLE);
            }
            if(data.get(0).getmLocation() != null){
                destination = data.get(0).getmLocation();
                location.setText(data.get(0).getmLocation());
                locationLayout.setVisibility(View.VISIBLE);
            }
            if(data.get(0).getmDetails() != null){
                details.setText(data.get(0).getmDetails());
                detailsLayout.setVisibility(View.VISIBLE);
            }
            if(data.get(0).getmContact() != null){
                contact.setText(data.get(0).getmContact());
                contactLayout.setVisibility(View.VISIBLE);
            }
        }
        if(destination.contains("N/A") || destination == null || destination == ""){
            navigate.setBackgroundColor(Color.parseColor("#C0C0C0"));
        }
    }

    @Override
    public void onLoaderReset(Loader<List<EventDetails>> loader) {

    }

    public void onClickNavigation(View view){

        if(destination.contains("LU Softball Complex")){
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?daddr=30.035326,-94.075240"));
            startActivity(intent);
        }
        else if(destination.contains("Mary and John Gray Library")){
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?daddr=30.042324,-94.075202"));
            startActivity(intent);
        }
        else if(destination.contains("Vincent-Beck Stadium")){
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?daddr=30.034931,-94.074988"));
            startActivity(intent);
        }
        else if(destination.contains("McDonald Gym")){
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?daddr=30.043798,-94.077327"));
            startActivity(intent);
        }
        else if(destination.contains("Dishman Art")){
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?daddr=30.046241,-94.075777"));
            startActivity(intent);
        }
        else if(destination.contains("Montagne Center")){
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?daddr=30.043917,-94.070782"));
            startActivity(intent);
        }
        else if(destination.contains("Galloway")){
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?daddr=30.044216,-94.073481"));
            startActivity(intent);
        }
        else if(destination.contains("Richard L. Price") || destination.contains("John Gray Center")){
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?daddr=30.036581,-94.075480"));
            startActivity(intent);
        }
        else if(destination.contains("University Theatre")){
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?daddr=30.045456,-94.075167"));
            startActivity(intent);
        }
        else if(destination.contains("Wayne A. Reaud")){
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?daddr=30.036254,-94.073132"));
            startActivity(intent);
        }
        else if(destination.contains("Maes")){
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?daddr=30.040618,-94.073375"));
            startActivity(intent);
        }
        else if(destination.contains("Cherry")){
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?daddr=30.041310,-94.074308"));
            startActivity(intent);
        }
        else if(destination.contains("Wimberly")){
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?daddr=30.043455,-94.073292"));
            startActivity(intent);
        }
        else if(destination.contains("Provost-Umphrey Stadium")){
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?daddr=30.043192,-94.069934"));
            startActivity(intent);
        }
        else if(destination.contains("Brooks-Shivers Dining Hall")){
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?daddr=30.041407,-94.076639"));
            startActivity(intent);
        }
        else if(destination.contains("A-2, A-5 Parking Lots")){
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?daddr=30.045048,-94.071910"));
            startActivity(intent);
        }
        else if(destination.contains("TX")){
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("google.navigation:q="+destination));
            startActivity(intent);
        }
        else{
            //do nothing
        }
    }
}

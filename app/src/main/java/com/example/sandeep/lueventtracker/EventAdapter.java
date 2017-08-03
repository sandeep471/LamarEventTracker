package com.example.sandeep.lueventtracker;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sandeep on 3/15/2017.
 */

public class EventAdapter extends ArrayAdapter<EventList> {

    public EventAdapter(Context context, List<EventList> events) {
        super(context, 0, events);
    }

    public View getView(int position, View convertView, ViewGroup parent){

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.event_list, parent, false);
        }

        EventList eventList = getItem(position);

        TextView month = (TextView) listItemView.findViewById(R.id.month);
        TextView date = (TextView) listItemView.findViewById(R.id.date);
        TextView title = (TextView) listItemView.findViewById(R.id.event_title);
        TextView description = (TextView) listItemView.findViewById(R.id.event_desc);

        month.setText(eventList.getmMonth());
        date.setText(eventList.getmDay());
        title.setText(eventList.getmTitle());
        description.setText(eventList.getmSummary());

        return listItemView;
    }
}

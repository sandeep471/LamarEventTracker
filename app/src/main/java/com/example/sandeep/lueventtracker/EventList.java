package com.example.sandeep.lueventtracker;

/**
 * Created by sandeep on 3/15/2017.
 */

public class EventList {
    private String mTitle;
    private String mSummary;
    private String mMonth;
    private String mDay;
    private String mUrl;

    public EventList(String title, String summary, String month, String date, String url){
        mTitle = title;
        mSummary = summary;
        mMonth = month;
        mDay = date;
        mUrl = url;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmSummary() {
        return mSummary;
    }

    public String getmMonth() {
        return mMonth;
    }

    public String getmDay() {
        return mDay;
    }

    public String getmUrl() {
        return mUrl;
    }
}

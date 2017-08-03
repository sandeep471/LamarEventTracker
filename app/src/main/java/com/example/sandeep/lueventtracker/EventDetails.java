package com.example.sandeep.lueventtracker;

/**
 * Created by sandeep on 3/15/2017.
 */

public class EventDetails {
    private String mTitle;
    private String mDate;
    private String mTime;
    private String mCategory;
    private String mLocation;
    private String mDetails;
    private String mContact;

    public EventDetails(String title, String date, String time, String category, String location, String details, String contact){
        mTitle = title;
        mDate = date;
        mTime = time;
        mCategory = category;
        mLocation = location;
        mDetails = details;
        mContact = contact;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmTime() {
        return mTime;
    }

    public String getmCategory() {
        return mCategory;
    }

    public String getmLocation() {
        return mLocation;
    }

    public String getmDetails() {
        return mDetails;
    }

    public String getmContact() {
        return mContact;
    }
}

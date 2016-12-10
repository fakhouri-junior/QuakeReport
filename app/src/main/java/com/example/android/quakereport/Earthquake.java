package com.example.android.quakereport;

/**
 * Created by salim on 13/10/2016.
 */
public class Earthquake {

    private double magnitude;
    private String place;
    private long timeInMilliseconds;
    private String mUrl;

    public Earthquake(double magnitude, String place, long timeInMilliseconds, String mUrl){
        this.magnitude = magnitude;
        this.place = place;
        this.timeInMilliseconds = timeInMilliseconds;
        this.mUrl = mUrl;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public String getPlace() {
        return place;
    }

    public long getTimeInMilliseconds() {
        return timeInMilliseconds;
    }

    public String getmUrl() {
        return mUrl;
    }
}

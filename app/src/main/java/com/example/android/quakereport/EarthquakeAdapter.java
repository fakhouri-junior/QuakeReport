package com.example.android.quakereport;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import android.graphics.drawable.GradientDrawable;


/**
 * Created by salim on 13/10/2016.
 */
public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(Context context, ArrayList<Earthquake> earthquakes){
        super(context, 0, earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View list_item_view = convertView;
        if (list_item_view == null){
            list_item_view = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent, false);
        }

        // get the item
        Earthquake earthquake = getItem(position);
        // fill in the data
        TextView magnitudeTextView = (TextView) list_item_view.findViewById(R.id.magnitude);
        // use formatMagnitude method
        // we first get the double value from the object
        double magnitude = earthquake.getMagnitude();

        magnitudeTextView.setText(formatMagnitude(magnitude));

        GradientDrawable gradientDrawable = (GradientDrawable) magnitudeTextView.getBackground();
        int magnitudeColor = getMagnitudeColor(magnitude);
        gradientDrawable.setColor(magnitudeColor);

        /*
         * Deal with the place string, duing manipulation
         * 2 cases
         * 1- 74km NW of Rumoi,Japan
         * 2- no number given just "pacific-anteractic-ridge" so we fill the first text view with "Near the"
         */
        TextView kmNearTextView = (TextView) list_item_view.findViewById(R.id.location_offset);
        TextView placeTextView = (TextView) list_item_view.findViewById(R.id.primary_location);

        String place = earthquake.getPlace();

        if(place.contains("km")){
            // case 1
            // get the index of the word "of"
            int indexOfOF = place.indexOf("of");
            String firstPart = place.substring(0,indexOfOF+2);
            String secondPart = place.substring(indexOfOF + 3);

            kmNearTextView.setText(firstPart);
            placeTextView.setText(secondPart);



        }else{
            // case 2
            kmNearTextView.setText(R.string.near_the);
            placeTextView.setText(place);

        }



        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(earthquake.getTimeInMilliseconds());

        // Find the TextView with view ID date
        TextView dateView = (TextView) list_item_view.findViewById(R.id.date);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);
        // Display the date of the current earthquake in that TextView
        dateView.setText(formattedDate);

        // Find the TextView with view ID time
        TextView timeView = (TextView) list_item_view.findViewById(R.id.time);
        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView
        timeView.setText(formattedTime);


        return list_item_view;
    }


    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    /**
     * Return the formatted magnitude string as 0.0 from a double value
     */
    private String formatMagnitude(double mag){
        // create a DecimalFormat object and specify the pattern to be 0.0
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        // use the decimalFormat to format the double value of the magnitude
        return decimalFormat.format(mag);
    }

    /**
     * Return int representing the right color for the right magnitude value,
     * @param mag is the magnitude value of the earthquke
     */
    private int getMagnitudeColor(double mag){
        // first convert the double value to an int,
        // since all our cases are 0-2, 2-3 , so we might as well truncate the value
        int color = 0;
        int magnitude = (int) Math.floor(mag);
        switch (magnitude){

            case 0:
            case 1:
                color = ContextCompat.getColor(getContext(),R.color.magnitude1);
                break;
            case 2:
                color = ContextCompat.getColor(getContext(),R.color.magnitude2);
                break;
            case 3:
                color = ContextCompat.getColor(getContext(),R.color.magnitude3);
                break;
            case 4:
                color = ContextCompat.getColor(getContext(),R.color.magnitude4);
                break;
            case 5:
                color = ContextCompat.getColor(getContext(),R.color.magnitude5);
                break;
            case 6:
                color = ContextCompat.getColor(getContext(),R.color.magnitude6);
                break;
            case 7:
                color = ContextCompat.getColor(getContext(),R.color.magnitude7);
                break;
            case 8:
                color = ContextCompat.getColor(getContext(),R.color.magnitude8);
                break;
            case 9:
                color = ContextCompat.getColor(getContext(),R.color.magnitude9);
                break;
            default:
                color = ContextCompat.getColor(getContext(),R.color.magnitude10plus);
                break;
        }

        return color;
    }
}

package com.tempwidget.dam44.temperaturewidget;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Created by Dan on 19/04/2016.
 */
public class Time {
    int hour;
    int mins;

    public int getHours() {
        return hour;
    }

    public int getMinutes() {
        return mins;
    }

    public String toString() {
        return new DecimalFormat("00", DecimalFormatSymbols.getInstance( Locale.ENGLISH )).format(hour) + ":" +  new DecimalFormat("00", DecimalFormatSymbols.getInstance( Locale.ENGLISH )).format(mins);
    }
}

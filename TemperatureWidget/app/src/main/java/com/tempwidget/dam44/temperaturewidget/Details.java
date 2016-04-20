package com.tempwidget.dam44.temperaturewidget;

import android.provider.CalendarContract;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Dan on 19/04/2016.
 */
public class Details {

    private static Details details;

    protected Details() {}

    public static Details getInstance() {
        if (details == null) {
            details = new Details();
        }
        return details;
    }

    public boolean ready() {
        if (readings != null) {
            return true;
        }
        return false;
    }

    public void refresh(Temperature[][] readings) {
        this.readings = readings;
    }

    public Temperature[][] readings;
    //public ArrayList<Temperature> readings;
    public Time curTime;
    private int curTempPos;

    public Temperature getCurrentTemperature() {
        return readings[curTime.getHours()][readings[curTime.getHours()].length - 1];
    }

    public Temperature averageTempLastHour() {
        int hours = curTime.getHours();
        Temperature ret = new Temperature();
        ret.hour = hours;
        try {
            int noReadingsCurHour = readings[hours].length;

            double addTemp = 0;
            for (int i = 0; i < 12; i++) {
                if (i < noReadingsCurHour) {
                    addTemp += readings[hours][i].temp;
                } else {
                    addTemp += readings[hours - 1][i].temp;
                }
            }
            ret.temp = addTemp/12;
        } catch (Exception e) {
            e.printStackTrace();
            ret.temp = 0;
        }

        return ret;

    }

    public String doubleToStringWithPrecision(double d) {
        return new DecimalFormat("00.00", DecimalFormatSymbols.getInstance( Locale.ENGLISH )).format(d);
    }

}

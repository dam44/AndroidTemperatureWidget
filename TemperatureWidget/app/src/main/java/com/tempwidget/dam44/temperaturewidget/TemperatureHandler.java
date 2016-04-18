package com.tempwidget.dam44.temperaturewidget;

import android.util.Xml;

import org.w3c.dom.NodeList;

import java.util.ArrayList;

/**
 * Created by Dan on 18/04/2016.
 */
public class TemperatureHandler {

    private ArrayList<Temperature> temperatures;

    public TemperatureHandler() {
        temperatures = new ArrayList<Temperature>();
    }

    public void refresh() {
       new TempRefreshTask(this).execute();
    }

    public void finishRefresh(ArrayList<Temperature> temps) {
        temperatures = temps;
    }


}

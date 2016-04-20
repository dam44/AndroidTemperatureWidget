package com.tempwidget.dam44.temperaturewidget;

import android.os.AsyncTask;

import java.util.ArrayList;

/**
 * Created by Dan on 18/04/2016.
 */
public class TempRefreshTask extends AsyncTask<Void, Void, Void> {

    TemperatureHandler callback;
    XmlTemperatureHandler xmlTemp;
    Temperature[][] temps;

    public TempRefreshTask(TemperatureHandler callback) {
        super();
        this.callback = callback;
        xmlTemp = new XmlTemperatureHandler();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            temps = xmlTemp.importTemperatures();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        callback.finishRefresh(temps);
    }
}

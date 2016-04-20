package com.tempwidget.dam44.temperaturewidget;

import android.content.Context;
import android.content.Intent;
import android.util.Xml;

import org.w3c.dom.NodeList;

import java.util.ArrayList;

/**
 * Created by Dan on 18/04/2016.
 */
public class TemperatureHandler {

    TemperatureWidget widget;
    Context context;

    public TemperatureHandler() {

    }

    public void refresh(TemperatureWidget widget, Context context) {
        this.widget = widget;
        this.context = context;
       new TempRefreshTask(this).execute();
    }

    public void finishRefresh(Temperature[][] temps) {
        if (this.widget == null) return;
        Details.getInstance().refresh(temps);
        this.widget.triggerUpdate(context);
        //Temperature temp = Details.getInstance().averageTempLastHour();
    }


}

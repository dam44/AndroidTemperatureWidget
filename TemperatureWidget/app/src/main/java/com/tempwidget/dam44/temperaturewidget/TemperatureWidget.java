package com.tempwidget.dam44.temperaturewidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link TemperatureWidgetConfigureActivity TemperatureWidgetConfigureActivity}
 */
public class TemperatureWidget extends AppWidgetProvider {

    public static String ACTION_WIDGET_CONFIGURE = "ConfigureWidget";
    public static String ACTION_WIDGET_REFRESH = "android.appwidget.action.WIDGET_BUTTON";
    public static final String WIDGET_IDS_KEY ="mywidgetproviderwidgetids";
    public static final String WIDGET_DATA_KEY ="mywidgetproviderwidgetdata";
    public TemperatureHandler tempHandler;

    public TemperatureWidget() {
        tempHandler = new TemperatureHandler();

    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = TemperatureWidgetConfigureActivity.loadTitlePref(context, appWidgetId);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.temperature_widget);
        //views.setTextViewText(R.id.appwidget_text, widgetText);
        if (Details.getInstance().ready()) {
            views.setTextViewText(R.id.lb_avgTemp, "Avg Temperature: " + Details.getInstance().doubleToStringWithPrecision(Details.getInstance().averageTempLastHour().temp));
            views.setTextViewText(R.id.lb_curTemp, "Temperature: " + Details.getInstance().doubleToStringWithPrecision(Details.getInstance().getCurrentTemperature().temp));
            views.setTextViewText(R.id.lb_curTime, "Time: " + Details.getInstance().curTime.toString());
        }

        // Sets up the settings button to open the configuration activity
        Intent configIntent = new Intent(context, TemperatureWidgetConfigureActivity.class);
        configIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        PendingIntent configPendingIntent = PendingIntent.getActivity(context, appWidgetId, configIntent, 0);
        views.setOnClickPendingIntent(R.id.btnSettings, configPendingIntent);
        configIntent.setAction(ACTION_WIDGET_CONFIGURE + Integer.toString(appWidgetId));

        Intent intent = new Intent(ACTION_WIDGET_REFRESH);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.btnRefresh, pendingIntent);


        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
        tempHandler.refresh(this, context);
        //remoteViews.setOnClickPendingIntent(R.id.btnRefresh, getPendingSelfIntent(context, ACTION_WIDGET_REFRESH));
        //appWidgetManager.updateAppWidget(watchWidget, remoteViews);
    }

    public static void updateNoRrefresh(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.hasExtra(WIDGET_IDS_KEY)) {
            int[] ids = intent.getExtras().getIntArray(WIDGET_IDS_KEY);
            if (intent.hasExtra(WIDGET_DATA_KEY)) {
                //Object data = intent.getExtras().getParcelable(WIDGET_DATA_KEY);
                //this.update(context, AppWidgetManager.getInstance(context), ids, data);
            } else {
                this.updateNoRrefresh(context, AppWidgetManager.getInstance(context), ids);
            }
        }
        else {
            super.onReceive(context, intent);
            if (ACTION_WIDGET_REFRESH.equals(intent.getAction())) {
                tempHandler.refresh(this, context);
            }
        }
    }

    public void triggerUpdate(Context context) {

        AppWidgetManager man = AppWidgetManager.getInstance(context);
        int[] ids = man.getAppWidgetIds(
                new ComponentName(context, TemperatureWidget.class));
        Intent updateIntent = new Intent();
        updateIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        updateIntent.putExtra(TemperatureWidget.WIDGET_IDS_KEY, ids);
        //updateIntent.putExtra(TemperatureWidget.WIDGET_DATA_KEY, (Parcelable[]) null);
        //updateIntent.putExtra(TemperatureWidget.WIDGET_DATA_KEY, (Object)Details.getInstance());

        context.sendBroadcast(updateIntent);

        //int[] ids = intent.getExtras().getIntArray(WIDGET_IDS_KEY);
        //this.onUpdate(context, AppWidgetManager.getInstance(context), ids);
    }



    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        // When the user deletes the widget, delete the preference associated with it.
        for (int appWidgetId : appWidgetIds) {
            TemperatureWidgetConfigureActivity.deleteTitlePref(context, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

//    static protected PendingIntent getPendingSelfIntent(Context context, String action) {
//        Intent intent = new Intent(context, getClass());
//        intent.setAction(action);
//        return PendingIntent.getBroadcast(context, 0, intent, 0);
//    }
}


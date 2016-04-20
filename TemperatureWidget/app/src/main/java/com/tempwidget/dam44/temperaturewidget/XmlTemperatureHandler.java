package com.tempwidget.dam44.temperaturewidget;

import android.util.Log;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Dan on 18/04/2016.
 */
public class XmlTemperatureHandler extends XmlHandler {

    public Temperature[][] importTemperatures() {

        NodeList nodelist = xmlReader(GlobalVars.IMPORTS[GlobalVars.IMPORTNO], "temps");
        Element root = (Element) nodelist.item(0);
        String curtimeString = root.getElementsByTagName("currentTime").item(0).getFirstChild().getNodeValue();
        Time curDate = new Time();
        String[] curTime = curtimeString.trim().split(":");
        curDate.hour = Integer.parseInt(curTime[0]);
        curDate.mins = Integer.parseInt(curTime[1]);
        Details.getInstance().curTime = curDate;
        NodeList readings = root.getElementsByTagName("reading");

        int readingCount = readings.getLength();

        //ArrayList<Temperature> temps = new ArrayList<Temperature>();
        int curHour = Details.getInstance().curTime.getHours();
        //Calendar not 0 based, starts at hour 1.
        Temperature[][] temps = new Temperature[curHour + 1][];
        int count = 0;
        for (int i = 0; i < readingCount; i++) {
            try {

                Node reading = readings.item(i);

                Temperature temp = new Temperature();
                String hour = reading.getAttributes().item(0).getNodeValue();
                temp.hour = Integer.parseInt(hour);

                //Bug in the XML API, sometimes gives values beyond the current time.
                //Don't bother with these values as they are not helpful
                //and are unrealistic as in a real life situation we could not
                //see values in the future.
                if (temps == null || temps.length < temp.hour) {
                    break;
                }

                String min = reading.getAttributes().item(1).getNodeValue();
                temp.min = Integer.parseInt(min);
                String tempString = reading.getAttributes().item(2).getNodeValue();
                temp.temp = Double.valueOf(tempString);

                if (temps[temp.hour] == null) {
                    count = 0;
                    temps[temp.hour] = generateSecondDimension(readings, i, temp, curHour);
                }
                temps[temp.hour][count] = temp;
                count++;
                //temps.add(temp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return temps;
    }

    private Temperature[] generateSecondDimension(NodeList readings, int i, Temperature temp, int curHour) {
            int size = 0;
            if (temp.hour < (curHour)) {
                size = 12;
            } else {
                int j = i;
                int readingCount = readings.getLength();
                while (j < readingCount) {
                    //There is a bug in the xml api.
                    //Often will receive values past the current time.
                    //Originally was using readingsCount%12 to get the remaining number of temperatures.
                    //This bug breaks this as this assumes that we never receive values past the current time.
                    //Therefore, this less efficient workaround has to be used instead.
                    int hour = Integer.parseInt(readings.item(j).getAttributes().item(0).getNodeValue());
                    if (hour == temp.hour) {
                        size++;
                    }
                    j++;
                }
            }
            return new Temperature[size];
    }
}

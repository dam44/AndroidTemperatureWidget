package com.tempwidget.dam44.temperaturewidget;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Dan on 18/04/2016.
 */
public class XmlTemperatureHandler extends XmlHandler {

    public ArrayList<Temperature> importTemperatures() {

        NodeList nodelist = xmlReader(GlobalVars.IMPORTS[GlobalVars.IMPORTNO], "temps");
        Element root = (Element) nodelist.item(0);
        String curtimeString = root.getElementsByTagName("currentTime").item(0).getFirstChild().getNodeValue();
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
        Date curDate = null;
        try {
            curDate = dateFormat.parse(curtimeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        GlobalVars.CURRENTTIME = curDate;
        NodeList readings = root.getElementsByTagName("reading");

        int readingCount = readings.getLength();

        ArrayList<Temperature> temps = new ArrayList<Temperature>();
        for (int i = 0; i < readingCount; i++) {
            try {
                Node reading = readings.item(i);

                Temperature temp = new Temperature();
                String hour = reading.getAttributes().item(0).getNodeValue();
                temp.hour = Integer.parseInt(hour);
                String min = reading.getAttributes().item(1).getNodeValue();
                temp.min = Integer.parseInt(min);
                String tempString = reading.getAttributes().item(2).getNodeValue();
                temp.temp = Double.valueOf(tempString);

                temps.add(temp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return temps;
    }
}

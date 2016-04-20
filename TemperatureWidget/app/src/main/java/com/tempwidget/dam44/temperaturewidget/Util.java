package com.tempwidget.dam44.temperaturewidget;

/**
 * Created by Dan on 20/04/2016.
 */
public class Util {
    public static double C2F(double c) {
        return ((9/5) * c) + 32;
    }

    public static double F2C(double f) {
        return (f - 32)*(5/9);
    }
}

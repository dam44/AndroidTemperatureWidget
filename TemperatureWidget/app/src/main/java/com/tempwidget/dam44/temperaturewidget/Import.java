package com.tempwidget.dam44.temperaturewidget;

/**
 * Created by Dan on 20/04/2016.
 */
public class Import {
    String url;
    String name;

    public Import(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public String toString() {
        return name;
    }
}

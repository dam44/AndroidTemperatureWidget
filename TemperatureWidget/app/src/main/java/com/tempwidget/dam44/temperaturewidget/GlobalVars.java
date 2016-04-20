package com.tempwidget.dam44.temperaturewidget;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Dan on 18/04/2016.
 */
public class GlobalVars {
    private static GlobalVars instance;
    public List<Import> IMPORTS;
    public Import IMPORT;
    public TempMode tempMode;

    protected GlobalVars() {
        IMPORTS = new ArrayList<Import>();
        IMPORTS.add(new Import("http://users.aber.ac.uk/aos/CSM22/temp1data.php", "Import 1"));
        IMPORTS.add(new Import("http://users.aber.ac.uk/aos/CSM22/temp2data.php", "Import 2"));
        IMPORT = IMPORTS.get(0);
        tempMode = TempMode.CELSIUS;
    }
    public static GlobalVars getInstance(){
        if (instance == null) {
            instance = new GlobalVars();
        }
        return instance;
    }

}

package com.thws.eventmanager.adapter;

public class DBAdapter {
    private static DBAdapter Instance ;

    public static  DBAdapter getInstance() {
        if (Instance == null) {
            Instance = new DBAdapter();
        }
        return Instance;
    }
    private DBAdapter() {
    }

    public boolean checkLocationAvailability(long locationId) {


        // TODO Implement the right way to check if a location is available
        return true;
    }
}

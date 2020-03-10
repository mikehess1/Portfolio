package com.packages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class StatusMessage {

    //define attributes for StatusMessage class
    static SimpleDateFormat formatIn = new SimpleDateFormat("yyyyMMdd HH:mm:ss.SSS");
    static final float FIVE_MINS = 300_000;  //5 minutes max interval in milliseconds between messages
    Date timestamp = new Date();
    int satelliteID;
    float redHigh;
    float yellowHigh;
    float yellowLow;
    float redLow;
    float rawValue;
    String component;
    String severity;


    public StatusMessage (String message) throws ParseException {

        //construct StatusMessage from string parameter
        String[] tokens = message.split("\\|");
        timestamp = formatIn.parse(tokens[0]);
        satelliteID = Integer.parseInt(tokens[1]);
        redHigh = Float.parseFloat(tokens[2]);
        yellowHigh = Float.parseFloat(tokens[3]);
        yellowLow = Float.parseFloat(tokens[4]);
        redLow = Float.parseFloat(tokens[5]);
        rawValue = Float.parseFloat(tokens[6]);
        component = tokens[7];
    }

    public String printJSONAlert () {

        //define format for date output
        SimpleDateFormat formatOut = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        //specify severity based on component
        if (component.equals("TSTAT")) severity = "RED HIGH";
        if (component.equals("BATT")) severity = "RED LOW";

        //return formatted JSON alert string
        return (
                "{\n\t\"satelliteId\": " + satelliteID +
                ",\n\t\"severity\": \"" + severity +
                "\",\n\t\"component\": \"" + component +
                "\",\n\t\"timestamp\": \"" + formatOut.format(timestamp) +
                "\"\n}");
    }

    //determine if this status message is same sat and component as parameter status message
    public boolean isSimilar (StatusMessage next) {

        if (satelliteID == next.satelliteID && component.equals(next.component)) return true;

        return false;
    }

    //determine if this status message is within 5 mins of parameter status message
    public boolean isWithinFiveMins (StatusMessage next) {

        float difference = Math.abs(timestamp.getTime() - next.timestamp.getTime());
        if (difference <= FIVE_MINS) return true;

        return false;
    }

    //determine if raw value is beyond the specified threshold
    public boolean isOutOfBounds () {

        if (component.equals("TSTAT") && rawValue > redHigh) return true;
        if (component.equals("BATT") && rawValue < redLow) return true;

        return false;
    }


}

//<timestamp>|<satellite-id>|<red-high-limit>|<yellow-high-limit>|<yellow-low-limit>|<red-low-limit>|<raw-value>|<component>
//20180101 23:01:05.001|1001|101|98|25|20|99.9|TSTAT

/*
    {
        "satelliteId": 1000,
        "severity": "RED HIGH",
        "component": "TSTAT",
        "timestamp": "2018-01-01T23:01:38.001Z"
    }
 */
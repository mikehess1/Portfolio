package com.packages;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        //declare variables
        ArrayList<String> inputList = new ArrayList<String>();
        ArrayList<String> alertList = new ArrayList<String>();
        final int THRESHOLD = 3;  //min number of messages to trigger an alert

/*
        //manually add messages to message list (simulates input file)
        inputList.add("20180101 23:01:05.001|1001|101|98|25|20|99.9|TSTAT");
        inputList.add("20180101 23:01:09.521|1000|17|15|9|8|7.8|BATT");
        inputList.add("20180101 23:01:26.011|1001|101|98|25|20|99.8|TSTAT");
        inputList.add("20180101 23:01:38.001|1000|101|98|25|20|102.9|TSTAT");  //trigger 1
        inputList.add("20180101 23:01:49.021|1000|101|98|25|20|87.9|TSTAT");
        inputList.add("20180101 23:02:09.014|1001|101|98|25|20|89.3|TSTAT");
        inputList.add("20180101 23:02:10.021|1001|101|98|25|20|89.4|TSTAT");
        inputList.add("20180101 23:02:11.302|1000|17|15|9|8|7.7|BATT");
        inputList.add("20180101 23:03:03.008|1000|101|98|25|20|102.7|TSTAT");  //trigger 2
        inputList.add("20180101 23:03:05.009|1000|101|98|25|20|101.2|TSTAT");  //trigger 3
        inputList.add("20180101 23:04:06.017|1001|101|98|25|20|89.9|TSTAT");
        inputList.add("20180101 23:04:11.531|1000|17|15|9|8|7.9|BATT");
        inputList.add("20180101 23:05:05.021|1001|101|98|25|20|89.9|TSTAT");
        inputList.add("20180101 23:05:07.421|1001|17|15|9|8|7.9|BATT");
*/

        //create scanner
        Scanner scanner = new Scanner(System.in);

        //read lines and add to input list
        while(scanner.hasNextLine())
        {
            inputList.add(scanner.nextLine());
        }

        //initialize messageArray to # of messages in input list
        StatusMessage[] messageArray = new StatusMessage[inputList.size()];

        //populate message array with new messages from input list
        for (int i = 0; i < messageArray.length; i++) {
            messageArray[i] = new StatusMessage(inputList.get(i));
        }

        //parse message array for alerts violating threshold
        for (int i = 0; i < messageArray.length; i++) {

            int messageCount = 0;

            // if alert is out of bounds, check for more alerts out of bounds
            if (messageArray[i].isOutOfBounds()) {

                messageCount++;

                for (int j = i + 1; j < messageArray.length; j++) {

                    if (messageArray[j].isOutOfBounds() &&
                        messageArray[i].isSimilar(messageArray[j]) &&
                        messageArray[i].isWithinFiveMins(messageArray[j])) {

                        messageCount++;
                    }
                }

                //if three or more messages are found, add alert to list for printing
                if (messageCount >= THRESHOLD) alertList.add(messageArray[i].printJSONAlert());
            }
        }

        //if only one alert, format this way
        if (alertList.size() == 1) System.out.println(alertList.get(0));

        //if more than one alert, format this way
        else if (alertList.size() > 1) {

            System.out.println("[");

            //print alerts list
            for (int i = 0; i < alertList.size(); i++) {
                System.out.print(alertList.get(i));

                if (i == alertList.size() - 1) System.out.println();
                else System.out.println(",");
            }

            System.out.println("]");
        }

        //if no alerts, print no alerts
        else
            System.out.println("There are no alerts to report");
    }
}
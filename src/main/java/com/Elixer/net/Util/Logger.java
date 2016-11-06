package com.Elixer.net.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by aweso on 10/16/2016.
 */
public class Logger {

    public static void message(String text) {
        System.out.println(Logger.getCurrentTimeStamp() + "| " + text);
    }

    public static void message(float text) {
        System.out.println(Logger.getCurrentTimeStamp() + "| " + text);
    }

    public static void data(Object... strings) {
        System.out.print(Logger.getCurrentTimeStamp() + "| ");

        for(int i = 2; i < strings.length + 2; i++) {
            if(i % 2 == 0) {
                System.out.print(strings[i-2].toString() + ": ");
            } else {
                System.out.print(strings[i-2].toString() + " | ");
            }
        }

        System.out.println();
    }

    public static void error(String text) {
        System.err.println(Logger.getCurrentTimeStamp() + "| " + text);
    }

    public static void errorEnd(String text) {
        System.err.println(Logger.getCurrentTimeStamp() + "| " + text);
        System.exit(1);
    }

    public static void title(String text) {
        System.out.println();
        System.out.println("|----------------------------------------- " + text.toUpperCase() + " -----------------------------------------|");
    }

    private static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
}

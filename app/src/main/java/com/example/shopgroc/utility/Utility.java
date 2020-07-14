package com.example.shopgroc.utility;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import static com.example.shopgroc.utility.Constant.Date.DATE;
import static com.example.shopgroc.utility.Constant.DateFormats.DATE_AT_TIME;

public class Utility {

    public static String getDateAtTime(long millis){
        DateTime dateTime=new DateTime();
        dateTime.withMillis(millis);

        DateTimeFormatter fmt= DateTimeFormat.forPattern(DATE_AT_TIME);
        return fmt.print(dateTime);
    }
    public static String getDate(long mills){
        DateTime date = new DateTime();
        date.withMillis(mills);
        DateTimeFormatter fmt= DateTimeFormat.forPattern(DATE);
        return fmt.print(date);
    }

}

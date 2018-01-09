package basic.datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTypes {
    public static void main(String[] args) {
        // 1. SimpleDateFormat is not a thread safe class
        // * If multiple threads access a format concurrently, it must be synchronized
        // * externally
        System.out.println("==========> Part1 <==========");
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            java.util.Date date = f.parse("2018-01-10 02:02:16");
            System.out.println(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //2. java.sql.Date don't have time part
        //   java.util.Date is a common class, it is super class of java.sql.Date
        System.out.println("==========> Part2 <==========");
        long timestamp = System.currentTimeMillis();
        java.sql.Date d = new java.sql.Date(timestamp);
        System.out.println(d);
        java.util.Date date = new java.util.Date(timestamp);
        System.out.println(date);

        //3. calculate the delta between two different date
        System.out.println("==========> Part3 <==========");
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d1=sf.parse("2017-09-08 10:10:10");
            Date d2=sf.parse("2017-09-15 00:00:00");
            System.out.println("the time difference between: " + d1 + " & " + d2 + " is");
            System.out.println(calDaysDelta(d1, d2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static String calDaysDelta(Date startDate, Date endDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long diff = endDate.getTime() - startDate.getTime();
        long day = diff / nd;
        long hour = diff % nd / nh;
        long min = diff % nd % nh / nm;
        return day + " days - " + hour + " hours - " + min + " minutes";
    }
}

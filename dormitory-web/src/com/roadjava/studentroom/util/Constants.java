package com.roadjava.studentroom.util;


import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

/**
 *
  * 
 * 用到的常量
 */
public class Constants {
    public static final Integer PAGE_SIZE = 10;
    public static final String MIDDLE_LINE  = "-";

    public static void main(String[] args) {

        Date date = new Date();
//        date.setTime(38783735288877325L);
        System.out.println(date);
        System.out.println(date.getTime());
        System.out.println(System.currentTimeMillis());
    }
}

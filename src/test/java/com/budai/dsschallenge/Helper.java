package com.budai.dsschallenge;

import java.util.Calendar;
import java.util.Date;

public class Helper {

    public static Date getDate(int year, int month, int day, int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hour, minute, 0);
        return calendar.getTime();
    }
}

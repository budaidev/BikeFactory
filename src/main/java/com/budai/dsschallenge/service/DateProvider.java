package com.budai.dsschallenge.service;

import java.util.Calendar;
import java.util.Date;

public class DateProvider {

    public Date getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 7, 20, 6, 0, 0);
        return calendar.getTime();
    }
}

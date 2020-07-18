package com.budai.dsschallenge.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateUtilTest {

    private DateUtil dateUtil;
    private final int CURRENT_YEAR = 2020;

    @BeforeEach
    public void setup() {
        dateUtil = new DateUtil();
    }

    private Date getDate(int year, int month, int day, int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hour, minute, 0);
        return calendar.getTime();
    }

    @Test
    public void workingHoursOnSameHour() {
        Date start = getDate(CURRENT_YEAR, 7, 31, 8, 0);
        Date end = getDate(CURRENT_YEAR, 7, 31, 8, 30);
        int minutes = dateUtil.getWorkingMinutes(start, end);
        assertEquals(30, minutes);
    }

    @Test
    public void workingHoursDifferentHours() {
        Date start = getDate(CURRENT_YEAR, 7, 31, 8, 0);
        Date end = getDate(CURRENT_YEAR, 7, 31, 10, 30);
        int minutes = dateUtil.getWorkingMinutes(start, end);
        assertEquals(150, minutes);
    }

    @Test
    public void beforeWorkingHours() {
        Date start = getDate(CURRENT_YEAR, 7, 31, 3, 0);
        Date end = getDate(CURRENT_YEAR, 7, 31, 8, 0);
        int minutes = dateUtil.getWorkingMinutes(start, end);
        assertEquals(120, minutes);
    }

    @Test
    public void afterWorkingHours() {
        Date start = getDate(CURRENT_YEAR, 7, 31, 21, 0);
        Date end = getDate(CURRENT_YEAR, 7, 31, 23, 0);
        int minutes = dateUtil.getWorkingMinutes(start, end);
        assertEquals(60, minutes);
    }

    @Test
    public void beforeAndAfterWorkingHours() {
        Date start = getDate(CURRENT_YEAR, 7, 31, 2, 0);
        Date end = getDate(CURRENT_YEAR, 7, 31, 23, 0);
        int minutes = dateUtil.getWorkingMinutes(start, end);
        assertEquals(60 * 16, minutes);
    }

    @Test
    public void oneDayDifferenceInWorkingHours() {
        Date start = getDate(CURRENT_YEAR, 7, 30, 8, 0);
        Date end = getDate(CURRENT_YEAR, 7, 31, 8, 0);
        int minutes = dateUtil.getWorkingMinutes(start, end);
        assertEquals(60 * 16, minutes);
    }

    @Test
    public void oneDayDifferenceStartBeforeWorkingHours() {
        Date start = getDate(CURRENT_YEAR, 7, 30, 2, 0);
        Date end = getDate(CURRENT_YEAR, 7, 31, 8, 0);
        int minutes = dateUtil.getWorkingMinutes(start, end);
        assertEquals(60 * 18, minutes);
    }

    @Test
    public void oneDayDifferenceEndBeforeWorkingHours() {
        Date start = getDate(CURRENT_YEAR, 7, 30, 8, 0);
        Date end = getDate(CURRENT_YEAR, 7, 31, 3, 0);
        int minutes = dateUtil.getWorkingMinutes(start, end);
        assertEquals(60 * 14, minutes);
    }

    @Test
    public void oneDayDifferenceStartAfterWorkingHours() {
        Date start = getDate(CURRENT_YEAR, 7, 30, 23, 0);
        Date end = getDate(CURRENT_YEAR, 7, 31, 8, 0);
        int minutes = dateUtil.getWorkingMinutes(start, end);
        assertEquals(60 * 2, minutes);
    }

    @Test
    public void oneDayDifferenceEndAfterWorkingHours() {
        Date start = getDate(CURRENT_YEAR, 7, 30, 8, 0);
        Date end = getDate(CURRENT_YEAR, 7, 31, 23, 0);
        int minutes = dateUtil.getWorkingMinutes(start, end);
        assertEquals(60 * 30, minutes);
    }
}

package com.budai.dsschallenge.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateUtilTest {

    private DateUtil dateUtil;
    private final int CURRENT_YEAR = 2020;

    @BeforeEach
    public void setup() {
        dateUtil = new DateUtil();
    }

    @Test
    public void workingHoursOnSameHour() {
        LocalDateTime start = LocalDateTime.of(CURRENT_YEAR, 7, 31, 8, 0);
        LocalDateTime end = LocalDateTime.of(CURRENT_YEAR, 7, 31, 8, 30);
        int minutes = dateUtil.getWorkingMinutes(start, end);
        assertEquals(30, minutes);
    }

    @Test
    public void workingHoursDifferentHours() {
        LocalDateTime start = LocalDateTime.of(CURRENT_YEAR, 7, 31, 8, 0);
        LocalDateTime end = LocalDateTime.of(CURRENT_YEAR, 7, 31, 10, 30);
        int minutes = dateUtil.getWorkingMinutes(start, end);
        assertEquals(150, minutes);
    }

    @Test
    public void beforeWorkingHours() {
        LocalDateTime start = LocalDateTime.of(CURRENT_YEAR, 7, 31, 3, 0);
        LocalDateTime end = LocalDateTime.of(CURRENT_YEAR, 7, 31, 8, 0);
        int minutes = dateUtil.getWorkingMinutes(start, end);
        assertEquals(120, minutes);
    }

    @Test
    public void afterWorkingHours() {
        LocalDateTime start = LocalDateTime.of(CURRENT_YEAR, 7, 31, 21, 0);
        LocalDateTime end = LocalDateTime.of(CURRENT_YEAR, 7, 31, 23, 0);
        int minutes = dateUtil.getWorkingMinutes(start, end);
        assertEquals(60, minutes);
    }

    @Test
    public void beforeAndAfterWorkingHours() {
        LocalDateTime start = LocalDateTime.of(CURRENT_YEAR, 7, 31, 2, 0);
        LocalDateTime end = LocalDateTime.of(CURRENT_YEAR, 7, 31, 23, 0);
        int minutes = dateUtil.getWorkingMinutes(start, end);
        assertEquals(60 * 16, minutes);
    }

    @Test
    public void oneDayDifferenceInWorkingHours() {
        LocalDateTime start = LocalDateTime.of(CURRENT_YEAR, 7, 30, 8, 0);
        LocalDateTime end = LocalDateTime.of(CURRENT_YEAR, 7, 31, 8, 0);
        int minutes = dateUtil.getWorkingMinutes(start, end);
        assertEquals(60 * 16, minutes);
    }

    @Test
    public void oneDayDifferenceStartBeforeWorkingHours() {
        LocalDateTime start = LocalDateTime.of(CURRENT_YEAR, 7, 30, 2, 0);
        LocalDateTime end = LocalDateTime.of(CURRENT_YEAR, 7, 31, 8, 0);
        int minutes = dateUtil.getWorkingMinutes(start, end);
        assertEquals(60 * 18, minutes);
    }

    @Test
    public void oneDayDifferenceEndBeforeWorkingHours() {
        LocalDateTime start = LocalDateTime.of(CURRENT_YEAR, 7, 30, 8, 0);
        LocalDateTime end = LocalDateTime.of(CURRENT_YEAR, 7, 31, 3, 0);
        int minutes = dateUtil.getWorkingMinutes(start, end);
        assertEquals(60 * 14, minutes);
    }

    @Test
    public void oneDayDifferenceStartAfterWorkingHours() {
        LocalDateTime start = LocalDateTime.of(CURRENT_YEAR, 7, 30, 23, 0);
        LocalDateTime end = LocalDateTime.of(CURRENT_YEAR, 7, 31, 8, 0);
        int minutes = dateUtil.getWorkingMinutes(start, end);
        assertEquals(60 * 2, minutes);
    }

    @Test
    public void oneDayDifferenceEndAfterWorkingHours() {
        LocalDateTime start = LocalDateTime.of(CURRENT_YEAR, 7, 30, 8, 0);
        LocalDateTime end = LocalDateTime.of(CURRENT_YEAR, 7, 31, 23, 0);
        int minutes = dateUtil.getWorkingMinutes(start, end);
        assertEquals(60 * 30, minutes);
    }

    @Test
    public void testDateFromMinutes() {
        LocalDateTime start = LocalDateTime.of(CURRENT_YEAR, 7, 30, 8, 0);
        LocalDateTime end = dateUtil.getDateFromMinutes(60, start);
        assertEquals(end, LocalDateTime.of(CURRENT_YEAR, 7, 30, 9, 0));
    }

    @Test
    public void testDateFromMinutesNextDay() {
        LocalDateTime start = LocalDateTime.of(CURRENT_YEAR, 7, 30, 8, 0);
        LocalDateTime end = dateUtil.getDateFromMinutes(960, start);
        assertEquals(end, LocalDateTime.of(CURRENT_YEAR, 7, 31, 8, 0));
    }

    @Test
    public void testDateFromMinutesSameDayEnd() {
        LocalDateTime start = LocalDateTime.of(CURRENT_YEAR, 7, 30, 8, 0);
        LocalDateTime end = dateUtil.getDateFromMinutes(839, start);
        assertEquals(end, LocalDateTime.of(CURRENT_YEAR, 7, 30, 21, 59));
    }
}

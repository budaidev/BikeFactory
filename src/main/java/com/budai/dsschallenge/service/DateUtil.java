package com.budai.dsschallenge.service;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.stream.IntStream;

public class DateUtil {

    private static final int WORK_HOUR_START = 6;
    private static final int WORK_HOUR_END = 22;
    private static final long MINUTES = 60;

    private static final long WORKING_HOURS_PER_DAY = WORK_HOUR_END - WORK_HOUR_START;
    private static final long WORKING_MINUTES_PER_DAY = WORKING_HOURS_PER_DAY * MINUTES;


    public int getWorkingMinutes(final LocalDateTime startTime, LocalDateTime endTime) {
        if (null == startTime || null == endTime) {
            throw new IllegalStateException();
        }
        if (endTime.isBefore(startTime)) {
            return 0;
        }

        LocalDateTime from = startTime;
        LocalDateTime to = endTime;

        LocalDate fromDay = from.toLocalDate();
        LocalDate toDay = to.toLocalDate();

        int allDaysBetween = (int) (ChronoUnit.DAYS.between(fromDay, toDay) + 1);
        long allWorkingMinutes = IntStream.range(0, allDaysBetween)
                .filter(i -> isWorkingDay(from.plusDays(i)))
                .count() * WORKING_MINUTES_PER_DAY;

        long tailRedundantMinutes = 0;
        if (isWorkingDay(from)) {
            if (isWorkingHours(from)) {
                tailRedundantMinutes = Duration.between(fromDay.atTime(WORK_HOUR_START, 0), from).toMinutes();
            } else if (from.getHour() > WORK_HOUR_START) {
                tailRedundantMinutes = WORKING_MINUTES_PER_DAY;
            }
        }
        long headRedundanMinutes = 0;
        if (isWorkingDay(to)) {
            if (isWorkingHours(to)) {
                headRedundanMinutes = Duration.between(to, toDay.atTime(WORK_HOUR_END, 0)).toMinutes();
            } else if (to.getHour() < WORK_HOUR_START) {
                headRedundanMinutes = WORKING_MINUTES_PER_DAY;
            }
        }
        return (int) (allWorkingMinutes - tailRedundantMinutes - headRedundanMinutes);
    }

    public LocalDateTime getDateFromMinutes(int minutes, LocalDateTime startDate) {
        long days = minutes / WORKING_MINUTES_PER_DAY;
        long remainder = minutes % WORKING_MINUTES_PER_DAY;

        LocalDateTime dateTime = startDate;
        dateTime = dateTime.plusDays(days);

        int minutesThisDay = (WORK_HOUR_END - dateTime.getHour()) * 60 - dateTime.getMinute();
        if (remainder < minutesThisDay) {
            return dateTime.plusMinutes(remainder);
        } else {
            remainder -= minutesThisDay;
            dateTime = dateTime.plusDays(1);
            dateTime.minusMinutes(remainder);
            return dateTime;
        }
    }


    private boolean isWorkingDay(final LocalDateTime time) {
        // return time.getDayOfWeek().getValue() < DayOfWeek.SATURDAY.getValue();
        return true;
    }

    private boolean isWorkingHours(final LocalDateTime time) {
        int hour = time.getHour();
        return WORK_HOUR_START <= hour && hour <= WORK_HOUR_END;
    }

    public LocalDateTime toLocalDateTime(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime() / 1000 * 1000)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public Date toDate(LocalDateTime dateToConvert) {
        return java.util.Date.from(dateToConvert
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    public int getDayDifferenceBetweenDates(LocalDateTime firstDate, LocalDateTime secondDate) {
        long diff = secondDate.until(firstDate, ChronoUnit.MINUTES);
        return (int) (diff / WORKING_MINUTES_PER_DAY);
    }

    public int getOverdue(LocalDateTime firstDate, LocalDateTime secondDate) {
        int diffDay = getDayDifferenceBetweenDates(firstDate, secondDate);
        if (diffDay <= 0) {
            return 0;
        } else {
            return diffDay;
        }
    }

}

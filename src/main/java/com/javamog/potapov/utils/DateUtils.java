package com.javamog.potapov.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtils {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private DateUtils() {
    }

    public static Date parseDate(String date) {
        Date parse;
        try {
            parse = dateFormat.parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Can't parse date from " + date);
        }
        return parse;
    }
}

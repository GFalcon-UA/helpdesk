package com.javamog.potapov.dto.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {

    private static final Logger log = LogManager.getLogger(DateConverter.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static String convert(Date date){
        return dateFormat.format(date);
    }

    public static Date convert(String dateAsString){
        try {
            return dateFormat.parse(dateAsString);
        } catch (ParseException e) {
            log.error(e.getMessage());
            return null;
        }
    }

}

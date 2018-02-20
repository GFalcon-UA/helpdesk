package com.javamog.potapov.dto.util;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class DateConverterTest {

    @Test
    public void convert() {
        Date convertedDate = DateConverter.convert("04/02/2018");
        if(convertedDate == null){
            fail();
        }

        assertTrue(convertedDate.getYear() == 2018 - 1900);
        assertTrue(convertedDate.getMonth() == 1);
        assertTrue(convertedDate.getDate() == 4);

        Date newDate = new Date(1991 - 1900, 7,24);

        assertTrue("24/08/1991".equals(DateConverter.convert(newDate)));
    }

}
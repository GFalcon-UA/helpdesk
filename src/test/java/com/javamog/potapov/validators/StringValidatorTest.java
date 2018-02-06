package com.javamog.potapov.validators;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StringValidatorTest {

    private ArrayList<String> words;

    @Before
    public void generateData(){
        words = new ArrayList<>();
        words.add("TEST");
        words.add("Test+-");
        words.add("+?*");
    }

    @Test
    public void validateTest() {
        assertTrue(new StringValidator(words.get(0)).onlyUpper().validate());
        assertTrue(new StringValidator(words.get(0)).onlyUpper().onlyDigit().validate());
        assertFalse(new StringValidator(words.get(0)).onlyLower().validate());
        assertTrue(new StringValidator(words.get(1)).onlyUpper().onlyLower().onlySpecial().validate());
        assertTrue(new StringValidator(words.get(2)).onlySpecial().validate());
        assertTrue(new StringValidator(words.get(2)).minSize(2).maxSize(10).validate());
        assertFalse(new StringValidator(words.get(0)).minSize(5).validate());
        assertTrue(new StringValidator(words.get(0)).includs("T", 2).validate());
    }



}
/*
 *  MIT License
 * -----------
 *
 * Copyright (c) 2016-2019 Oleksii V. KHALIKOV, PE (gfalcon.com.ua)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package ua.com.gfalcon.helpdesk.validators;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;



public class StringValidatorTest {

    private ArrayList<String> words;


    @Before
    public void generateData() {
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
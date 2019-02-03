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

import java.util.Arrays;



public class StringValidator {

    public static final String UPPER_CASE = "[A-Z]";

    public static final String LOWER_CASE = "[a-z]";

    public static final String DIDITS = "[0-9]";

    public static final Character[] CHARACTER = {'~', '.', '"', '(', ')', ',', ':', ';', '<', '>', '@', '[', ']', '!',
            '#', '$', '%', '&', '\'', '*', '+', '-', '/', '=', '?', '^', '_', '`', '{', '|', '}', '-'
    };

    private String enteredString;

    private String str;

    private boolean minSize;

    private boolean maxSize;

    private boolean characterIncluded;


    public StringValidator() {

    }


    public StringValidator(String str) {
        this.enteredString = str;
        minSize = true;
        maxSize = true;
        characterIncluded = true;
    }


    public static String clearUpper(String string) {
        return string.replaceAll(UPPER_CASE, "").trim();
    }


    public static String clearLower(String string) {
        return string.replaceAll(LOWER_CASE, "").trim();
    }


    public static String clearDigit(String string) {
        return string.replaceAll(DIDITS, "").trim();
    }


    public static String clearSpecial(String string) {
        return string.replaceAll(Arrays.toString(CHARACTER), "").trim();
    }


    public static StringValidator getValidator() {
        return new StringValidator();
    }


    public StringValidator init(String str) {
        this.enteredString = str;
        minSize = true;
        maxSize = true;
        characterIncluded = true;
        return this;
    }


    public StringValidator onlyUpper() {
        initTemporaryString();
        str = clearUpper(str);
        return this;
    }


    public StringValidator onlyLower() {
        initTemporaryString();
        str = clearLower(str);
        return this;
    }


    public StringValidator onlyDigit() {
        initTemporaryString();
        str = clearDigit(str);
        return this;
    }


    public StringValidator onlySpecial() {
        initTemporaryString();
        str = clearSpecial(str);
        return this;
    }


    public StringValidator includs(String subStr) {
        characterIncluded = enteredString.contains(subStr) && characterIncluded;
        return this;
    }


    public StringValidator includs(String subStr, int expected) {
        if (subStr == null || subStr.isEmpty()) {
            return this;
        }
        if (expected < 1) {
            expected = 1;
        }
        int found = 0;
        String str = enteredString;
        while (true) {
            if (str.contains(subStr)) {
                str = str.replaceFirst(subStr, "");
                found++;
            } else {
                break;
            }
        }
        characterIncluded = characterIncluded && found == expected;
        return this;
    }


    public StringValidator minSize(int min) {
        minSize = enteredString.length() >= min && minSize;
        return this;
    }


    public StringValidator maxSize(int max) {
        maxSize = enteredString.length() <= max && maxSize;
        return this;
    }


    private void initTemporaryString() {
        if (str == null) {
            str = enteredString;
        }
    }


    public boolean validate() {
        if (str == null) {
            return minSize && maxSize && characterIncluded;
        }
        return str.length() == 0 && minSize && maxSize && characterIncluded;
    }

}

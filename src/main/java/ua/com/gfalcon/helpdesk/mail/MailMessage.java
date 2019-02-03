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

package ua.com.gfalcon.helpdesk.mail;

import ua.com.gfalcon.helpdesk.domain.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;



@Data
public class MailMessage {

    private String address;

    private List<String> addresses = new ArrayList<>();

    private String subject;

    private String text;


    public MailMessage(String address, String subject, String text) {
        setAddress(address);
        setSubject(subject);
        setText(text);
    }


    public MailMessage(User addressee, String subject, String text) {
        this(addressee.getEmail(), subject, text);
    }


    public MailMessage(List<User> addressees, String subject, String text) {
        for (User user : addressees) {
            addresses.add(user.getEmail());
        }
        setSubject(subject);
        setText(text);
    }

}

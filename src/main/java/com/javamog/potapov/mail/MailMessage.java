package com.javamog.potapov.mail;

import com.javamog.potapov.domain.User;
import lombok.Data;

@Data
public class MailMessage {

    private String address;
    private String subject;
    private String text;

    public MailMessage(String address, String subject, String text){
        setAddress(address);
        setSubject(subject);
        setText(text);
    }

    public MailMessage(User addressee, String subject, String text){
        this(addressee.getEmail(), subject, text);
    }

}

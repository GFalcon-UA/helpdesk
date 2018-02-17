package com.javamog.potapov.mail;

import com.javamog.potapov.domain.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MailMessage {

    private String address;
    private List<String> addresses = new ArrayList<>();
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

    public MailMessage(List<User> addressees, String subject, String text){
        for(User user : addressees){
            addresses.add(user.getEmail());
        }
        setSubject(subject);
        setText(text);
    }

}

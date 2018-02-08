package com.javamog.potapov.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javamog.potapov.parent.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "FEEDBACK")
public class Feedback extends AbstractEntity {

    @JsonProperty("nRate")
    private int rate;

    @JsonProperty("dDate")
    private Date date;

    @JsonProperty("sText")
    private String text;

    @ManyToOne
    @JsonBackReference
    @JsonProperty("oUser")
    private User user;

    @ManyToOne
    @JsonBackReference
    @JsonProperty("oTicket")
    private Ticket ticket;

    public Feedback() {
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    @Deprecated
    public void setUser(User user) {
        this.user = user;
    }

    public Ticket getTicket() {
        return ticket;
    }

    @Deprecated
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}

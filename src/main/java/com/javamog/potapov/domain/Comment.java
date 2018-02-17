package com.javamog.potapov.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javamog.potapov.parent.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "COMMENTS")
public class Comment extends AbstractEntity {

    @JsonProperty("sText")
    private String text;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @JsonProperty("dDate")
    private Date date;

    @ManyToOne
    @JsonManagedReference
    @JsonProperty("oUser")
    private User user;

    @ManyToOne
    @JsonBackReference
    @JsonProperty("oTicket")
    private Ticket ticket;

    public Comment(){

    }

    public Comment(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    protected void setUser(User user) {
        this.user = user;
    }

    public Ticket getTicket() {
        return ticket;
    }

    protected void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}


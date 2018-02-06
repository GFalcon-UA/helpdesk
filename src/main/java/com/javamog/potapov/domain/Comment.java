package com.javamog.potapov.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javamog.potapov.parent.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "COMMENT")
public class Comment extends AbstractEntity {


//    @Column(name = "text")
    private String text;

//    @Column(name = "commentDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Date date;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
    @ManyToOne
//    @JsonIgnore
    private User user;

//    @ManyToOne
//    @JoinColumn(name = "ticket_id")
    @ManyToOne
//    @JsonIgnore
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


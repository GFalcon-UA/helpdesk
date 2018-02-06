package com.javamog.potapov.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javamog.potapov.parent.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "HISTORY")
public class History extends AbstractEntity {

//    @Column(name = "date")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Date date;

//    @Column(name = "action")
    private String action;

//    @Column(name = "description")
    private String description;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

//    @ManyToOne
//    @JoinColumn(name = "ticket_id")
    @ManyToOne
    private Ticket ticket;

    public History() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

package com.javamog.potapov.model.history;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javamog.potapov.model.user.User;

import javax.persistence.*;
import java.util.Date;


@Entity
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HISTORY_ID", unique = true)
    private Long id;

    @Column(name = "date")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Date date;

    @Column(name = "action")
    @Enumerated(EnumType.STRING)
    private HistoryAction action;

    @Column(name = "description")
    private String details;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public HistoryAction getAction() {
        return action;
    }

    public void setAction(HistoryAction action) {
        this.action = action;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

package com.javamog.potapov.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.javamog.potapov.model.ticket.Ticket;
import com.javamog.potapov.model.user.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "FEEDBACK")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FEEDBACK_ID")
    private Long id;


    @Column(name = "rate")
    private int rate;


    private Date feedbackDate;

    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User feedbackUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Date getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getFeedbackUser() {
        return feedbackUser;
    }

    public void setFeedbackUser(User feedbackUser) {
        this.feedbackUser = feedbackUser;
    }
}

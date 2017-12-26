package com.javamog.potapov.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "COMMENT")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID", unique = true)
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "commentDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Date commentDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User commentUser;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket commentTicket;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public User getCommentUser() {
        return commentUser;
    }

    public void setCommentUser(User commentUser) {
        this.commentUser = commentUser;
    }

    public Ticket getCommentTicket() {
        return commentTicket;
    }

    public void setCommentTicket(Ticket commentTicket) {
        this.commentTicket = commentTicket;
    }

    @Override
    public int hashCode() {
        if (id != null) {
            return id.hashCode();
        } else {
            return super.hashCode();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Comment))
            return false;
        Comment other = (Comment) obj;
        if (id != other.id)
            return false;
        return true;
    }

    /*public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Person))
            return false;

        Person other = (Person)o;

        if (id == other.getId()) return true;
        if (id == null) return false;

// equivalence by id
        return id.equals(other.getId());
    }

    public int hashCode() {
        if (id != null) {
            return id.hashCode();
        } else {
            return super.hashCode();
        }
    }*/


}


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

package ua.com.gfalcon.helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import ua.com.gfalcon.utils.repository.entity.AbstractEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;



@Entity
@Table(name = "FEEDBACKS")
public class Feedback extends AbstractEntity {

    @JsonProperty("nRate")
    private int rate;

    @JsonProperty("dDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date date;

    @JsonProperty("sText")
    private String text;

    @ManyToOne
    @JsonBackReference
    @JsonProperty("oUser")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id")
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


    protected void setUser(User user) {
        this.user = user;
    }


    public Ticket getTicket() {
        return ticket;
    }


    protected void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }


    @Override
    public String toString() {
        return "Feedback{" +
                "rate=" + rate +
                ", date=" + date +
                ", text='" + text + '\'' +
                ", user=" + user.getId() +
                ", ticket=" + ticket.getId() +
                "} " + super.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Feedback))
            return false;
        Feedback feedback = (Feedback) o;
        return getRate() == feedback.getRate() &&
                Objects.equals(getDate(), feedback.getDate()) &&
                Objects.equals(getText(), feedback.getText()) &&
                Objects.equals(getUser(), feedback.getUser()) &&
                Objects.equals(getTicket(), feedback.getTicket());
    }


    @Override
    public int hashCode() {

        return Objects.hash(getRate(), getDate(), getText(), getUser(), getTicket());
    }

}

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
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import ua.com.gfalcon.utils.repository.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;



@Entity
@Table(name = "HISTORIES")
public class History extends AbstractEntity {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm")
    @JsonProperty("dDate")
    private Date date = new Date();

    @JsonProperty("sAction")
    private String action;

    @JsonProperty("sDescription")
    private String description;

    @JsonManagedReference
    @ManyToOne
    @JsonProperty("oUser")
    private User user;

    @JsonBackReference
    @ManyToOne
    @JsonProperty("oTicket")
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
        return "History{" +
                "date=" + date +
                ", action='" + action + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user.getId() +
                ", ticket=" + ticket.getId() +
                "} " + super.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof History))
            return false;
        History history = (History) o;
        return Objects.equals(getDate(), history.getDate()) &&
                Objects.equals(getAction(), history.getAction()) &&
                Objects.equals(getDescription(), history.getDescription()) &&
                Objects.equals(getUser(), history.getUser()) &&
                Objects.equals(getTicket(), history.getTicket());
    }


    @Override
    public int hashCode() {

        return Objects.hash(getDate(), getAction(), getDescription(), getUser(), getTicket());
    }

}

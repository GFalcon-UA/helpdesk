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

package ua.com.gfalcon.helpdesk.dto.models;

import ua.com.gfalcon.helpdesk.domain.Category;
import ua.com.gfalcon.helpdesk.domain.Feedback;
import ua.com.gfalcon.helpdesk.domain.User;
import ua.com.gfalcon.helpdesk.domain.enums.State;
import ua.com.gfalcon.helpdesk.domain.enums.Urgency;

import java.util.Date;



public class TicketDTO {

    private Long id;

    private String name;

    private String description;

    private Date createdOn;

    private Date desiredDate;

    private State state;

    private Urgency urgency;

    private User owner;

    private User assignee;

    private User approver;

    private Category category;

    private Feedback feedback;


    public TicketDTO() {
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public Date getCreatedOn() {
        return createdOn;
    }


    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }


    public Date getDesiredDate() {
        return desiredDate;
    }


    public void setDesiredDate(Date desiredDate) {
        this.desiredDate = desiredDate;
    }


    public State getState() {
        return state;
    }


    public void setState(State state) {
        this.state = state;
    }


    public Urgency getUrgency() {
        return urgency;
    }


    public void setUrgency(Urgency urgency) {
        this.urgency = urgency;
    }


    public User getOwner() {
        return owner;
    }


    public void setOwner(User owner) {
        this.owner = owner;
    }


    public User getAssignee() {
        return assignee;
    }


    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }


    public User getApprover() {
        return approver;
    }


    public void setApprover(User approver) {
        this.approver = approver;
    }


    public Category getCategory() {
        return category;
    }


    public void setCategory(Category category) {
        this.category = category;
    }


    public Feedback getFeedback() {
        return feedback;
    }


    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

}

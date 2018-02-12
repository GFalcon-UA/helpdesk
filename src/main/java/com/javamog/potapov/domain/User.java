package com.javamog.potapov.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javamog.potapov.domain.enums.Role;
import com.javamog.potapov.parent.entity.AbstractEntity;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERS")
public class User extends AbstractEntity implements UserDetails {

    @Column(name = "first_name", nullable = false)
    @JsonProperty("sFirstName")
    private String firstName;

    @Column(name = "last_name")
    @JsonProperty("sLastName")
    private String lastName;

    @Column(name = "phone")
    @JsonProperty("sPhone")
    private String phone;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    @JsonProperty("sRole")
    private Role role;

    @Column(name = "email", nullable = false)
    @JsonProperty("sEmail")
    private String email;

    @Column(name = "address")
    @JsonProperty("sAddress")
    private String address;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    @JsonProperty("aoOwnTickets")
    private Set<Ticket> ownTickets = new HashSet<>();

    @OneToMany(mappedBy = "assignee", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    @JsonProperty("aoAssignTickets")
    private Set<Ticket> assignTickets = new HashSet<>();

    @OneToMany(mappedBy = "approver", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    @JsonProperty("aoApproveTickets")
    private Set<Ticket> approveTickets = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @JsonProperty("aoHistories")
    private Set<History> histories = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @JsonProperty("aoComments")
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @JsonProperty("aoFeedbacks")
    private Set<Feedback> feedbacks = new HashSet<>();

    @Column(name = "password")
    @JsonIgnore
    private String password;

    public User() {
    }

    @Override
    public Set<Role> getAuthorities() {
        Set<Role> roles = new HashSet<>();
        roles.add(this.role);
        return roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Ticket> getOwnTickets() {
        return ownTickets;
    }

    public void setOwnTickets(Set<Ticket> ownTickets) {
        this.ownTickets = ownTickets;
    }

    public void addOwnTicket(Ticket ownTicket){
        ownTickets.add(ownTicket);
        ownTicket.setOwner(this);
    }

    public void removeOwnTicket(Ticket ownTicket){
        ownTickets.remove(ownTicket);
        ownTicket.setOwner(null);
    }

    public Set<Ticket> getAssignTickets() {
        return assignTickets;
    }

    public void setAssignTickets(Set<Ticket> assignTickets) {
        this.assignTickets = assignTickets;
    }

    public void setAssignTicket(Ticket assignTicket){
        assignTickets.add(assignTicket);
        assignTicket.setAssignee(this);
    }

    public void removeAssignTicket(Ticket assignTicket){
        assignTickets.remove(assignTicket);
        assignTicket.setAssignee(null);
    }

    public Set<Ticket> getApproveTickets() {
        return approveTickets;
    }

    public void setApproveTickets(Set<Ticket> approveTickets) {
        this.approveTickets = approveTickets;
    }

    public void addApproveTicket(Ticket approveTicket){
        approveTickets.add(approveTicket);
        approveTicket.setApprover(this);
    }

    public void removeApproveTicket(Ticket approveTicket){
        approveTickets.remove(approveTicket);
        approveTicket.setApprover(null);
    }

    public Set<History> getHistories() {
        return histories;
    }

    public void setHistories(Set<History> histories) {
        this.histories = histories;
    }

    public void addHistories(History historie) {
        histories.add(historie);
        historie.setUser(this);
    }

    public void removeHistories(History historie) {
        histories.remove(historie);
        historie.setUser(null);
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setUser(this);
    }

    public void removeComments(Comment comment) {
        comments.remove(comment);
        comment.setUser(null);
    }

    public Set<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Set<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public void addFeedback(Feedback feedback) {
        feedbacks.add(feedback);
        feedback.setUser(this);
    }

    public void removeFeedback(Feedback feedback) {
        feedbacks.remove(feedback);
        feedback.setUser(null);
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

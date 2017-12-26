package com.javamog.potapov.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.javamog.potapov.utils.Role;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", unique = true)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "role_id", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role roleId;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "addres", nullable = false)
    private String addres;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Ticket> ownTickets;

    @OneToMany(mappedBy = "assignee", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Ticket> assignTickets;

    @OneToMany(mappedBy = "approver", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Ticket> approveTickets;

    @OneToMany(mappedBy = "historyUser", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<History> userHistory;

    @OneToMany(mappedBy = "commentUser", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comment> userComments;

    @OneToMany(mappedBy = "feedbackUser", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Feedback> userFeedback;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Role getRoleId() {
        return roleId;
    }

    public void setRoleId(Role roleId) {
        this.roleId = roleId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public List<Ticket> getOwnTickets() {
        return ownTickets;
    }

    public void setOwnTickets(List<Ticket> tickets) {
        this.ownTickets = tickets;
    }

    public List<Ticket> getAssignTickets() {
        return assignTickets;
    }

    public void setAssignTickets(List<Ticket> myAssignTickets) {
        this.assignTickets = myAssignTickets;
    }

    public List<Ticket> getApproveTickets() {
        return approveTickets;
    }

    public void setApproveTickets(List<Ticket> myApproveTickets) {
        this.approveTickets = myApproveTickets;
    }

    public List<History> getUserHistory() {
        return userHistory;
    }

    public void setUserHistory(List<History> userHistory) {
        this.userHistory = userHistory;
    }

    public List<Comment> getUserComments() {
        return userComments;
    }

    public void setUserComments(List<Comment> userComments) {
        this.userComments = userComments;
    }

    public List<Feedback> getUserFeedback() {
        return userFeedback;
    }

    public void setUserFeedback(List<Feedback> userFeedback) {
        this.userFeedback = userFeedback;
    }
}

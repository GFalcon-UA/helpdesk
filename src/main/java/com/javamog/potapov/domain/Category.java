package com.javamog.potapov.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javamog.potapov.parent.entity.AbstractEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CATEGORIES")
public class Category extends AbstractEntity {

    @Column(name = "name", nullable = false)
    @JsonProperty("sName")
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    @JsonProperty("aoTickets")
    private Set<Ticket> tickets = new HashSet<>();

    public Category() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
        ticket.setCategory(this);
    }

    public void removeTicket(Ticket ticket) {
        this.tickets.remove(ticket);
        ticket.setCategory(null);
    }
}
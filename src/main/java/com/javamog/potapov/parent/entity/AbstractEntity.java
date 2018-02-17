package com.javamog.potapov.parent.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sf.brunneng.jom.annotations.Identifier;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
public class AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonProperty(value = "nId")
    private Long id;

    @Identifier
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                '}';
    }
}

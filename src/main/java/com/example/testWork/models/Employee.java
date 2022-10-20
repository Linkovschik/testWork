package com.example.testWork.models;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private Set<OrderEvent> orderEvents = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<OrderEvent> getOrderEvents() {
        return orderEvents;
    }

    public void setOrderEvents(Set<OrderEvent> orderEvents) {
        this.orderEvents = orderEvents;
    }

}
package com.example.testWork.models;

import com.example.testWork.support.ObjectConverter;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "order_event")
public class OrderEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "event_date", nullable = true)
    private Instant eventDate;

    @Column(name = "event_type", nullable = false)
    private String eventType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private ProductOrder order;

    @Column(name = "data", columnDefinition = "json")
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property =
            "eventType")
    @Convert(converter = ObjectConverter.class)
    private EventData data;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getEventDate() {
        return eventDate;
    }

    public void setEventDate(Instant eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public ProductOrder getOrder() {
        return order;
    }

    public void setOrder(ProductOrder order) {
        this.order = order;
    }

    public EventData getData() {
        return data;
    }

    public void setData(EventData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Event { "
                + "id=" + this.id + "; "
                + "eventDate=" + this.eventDate + "; "
                + "eventType=" + this.eventType + "; "
                + "employeeId=" + this.employee.getId() + "; "
                + "data=" + data + "; "
                + "}";
    }
}
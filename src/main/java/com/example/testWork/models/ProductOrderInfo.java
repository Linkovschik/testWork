package com.example.testWork.models;

import java.util.List;

// предоставляемая на экране информациоя о заказе
public class ProductOrderInfo {

    private Integer orderId;

    // статус заказа
    private String status;
    // список событий
    private List<OrderEvent> eventList;

    public ProductOrderInfo() {
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderEvent> getEventList() {
        return eventList;
    }

    public void setEventList(List<OrderEvent> eventList) {
        this.eventList = eventList;
    }

    @Override
    public String toString() {
        return "OrderId#" + this.orderId + " {Status: " + this.status + "}";
    }
}

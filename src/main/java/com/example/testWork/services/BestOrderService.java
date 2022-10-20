package com.example.testWork.services;

import com.example.testWork.dtos.CancelEventDTO;
import com.example.testWork.dtos.DefaultEventDTO;
import com.example.testWork.dtos.RegistrationEventDTO;
import com.example.testWork.exceptions.NotFoundOrderException;
import com.example.testWork.exceptions.OrderEventSequenceException;
import com.example.testWork.models.*;
import com.example.testWork.repository.*;
import com.example.testWork.support.OrderDateFormatter;
import com.example.testWork.support.enumerations.OrderEventType;
import com.example.testWork.support.enumerations.OrderEventTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BestOrderService implements OrderService {

    @Autowired
    OrderEventRepository orderEventRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductOrderRepository productOrderRepository;

    @Autowired
    OrderEventTypeMapper eventTypeMapper;

    @Autowired
    OrderDateFormatter orderDateFormatter;

    public List<ProductOrder> getOrders() {
        return productOrderRepository.findAll();
    }

    private void mapRegistrationEvent(RegistrationEventDTO eventData, OrderEvent orderEvent) {
        RegistrationData data;
        Client client;
        var foundClient = clientRepository.findById(eventData.getClientId());
        if (foundClient.isPresent()) {
            client = foundClient.get();
        } else {
            client = new Client();
            client.setId(eventData.getClientId());
            client = clientRepository.saveAndFlush(client);
        }

        Product product;
        var foundProduct = productRepository.findById(eventData.getProductId());
        if (foundProduct.isPresent()) {
            product = foundProduct.get();
        } else {
            product = new Product();
            product.setId(eventData.getProductId());
            product.setCost(eventData.getProductCost());
            product = productRepository.saveAndFlush(product);
        }

        var order = new ProductOrder();
        // не записываем Id из eventData, так как заказ только создаётся
        order.setClient(client);
        order.setProduct(product);
        order = productOrderRepository.saveAndFlush(order);

        orderEvent.setOrder(order);
        data = new RegistrationData();
        data.setExpectedPickUPTime(orderDateFormatter.toDate(eventData.getExpectedPickUPTime()));
        orderEvent.setData(data);
    }

    private void mapCancellingEvent(CancelEventDTO eventData, OrderEvent orderEvent) {
        CancellingData data;
        var order = productOrderRepository.findById(eventData.getOrderId()).orElseThrow(NotFoundOrderException::new);
        checkOrderStatus(order.getId());
        orderEvent.setOrder(order);
        data = new CancellingData();
        data.setReason(eventData.getReason());
        orderEvent.setData(data);
    }

    private void mapDefaultEvent(DefaultEventDTO eventData, OrderEvent orderEvent) {
        EventData data;
        var order = productOrderRepository.findById(eventData.getOrderId()).orElseThrow(NotFoundOrderException::new);
        checkOrderStatus(order.getId());
        orderEvent.setOrder(order);
        data = new EventData();
        orderEvent.setData(data);
    }

    public OrderEvent addEvent(DefaultEventDTO eventData) {
        var eventType = eventTypeMapper.mapToEnum(eventData.getEventType());

        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setEventType(eventData.getEventType());

        if (eventType == OrderEventType.REGISTERED && eventData instanceof RegistrationEventDTO) {
            mapRegistrationEvent((RegistrationEventDTO) eventData, orderEvent);
        } else if (eventType == OrderEventType.CANCELED && eventData instanceof CancelEventDTO) {
            mapCancellingEvent((CancelEventDTO) eventData, orderEvent);
        } else {
            mapDefaultEvent(eventData, orderEvent);
        }

        Employee employee;
        var foundEmployee = employeeRepository.findById(eventData.getEmployeeId());
        if (foundEmployee.isPresent()) {
            employee = foundEmployee.get();
        } else {
            employee = new Employee();
            employee.setId(eventData.getEmployeeId());
            employee = employeeRepository.saveAndFlush(employee);
        }

        orderEvent.setEmployee(employee);
        orderEvent.setEventDate(orderDateFormatter.toDate(eventData.getEventDate()));

        orderEvent = orderEventRepository.saveAndFlush(orderEvent);

        return orderEvent;
    }

    public ProductOrderInfo prepareOrderInfo(int orderId) {
        var productOrderInfo = new ProductOrderInfo();


        var productOrder = productOrderRepository.findById(orderId).orElseThrow(NotFoundOrderException::new);

        var productOrderLastEvent =
                productOrder.getOrderEvents().get(productOrder.getOrderEvents().size() - 1);

        productOrderInfo.setOrderId(orderId);
        productOrderInfo.setStatus(productOrderLastEvent.getEventType());

        var orderEvents = orderEventRepository.findByOrderId(orderId);
        productOrderInfo.setEventList(orderEvents);

        return productOrderInfo;
    }

    public String getEventTypeName(OrderEventType type) {
        return eventTypeMapper.getEnumTypeName(type);
    }

    public Boolean checkOrderStatus(int orderId) throws RuntimeException {
        var orderInfo = prepareOrderInfo(orderId);
        var orderStatus = eventTypeMapper.mapToEnum(orderInfo.getStatus());
        if (orderStatus == OrderEventType.GIVEN || orderStatus == OrderEventType.CANCELED) {
            throw new OrderEventSequenceException(orderId);
        }
        return true;
    }

}


package com.example.testWork.controllers;

import com.example.testWork.dtos.CancelEventDTO;
import com.example.testWork.dtos.DefaultEventDTO;
import com.example.testWork.dtos.RegistrationEventDTO;
import com.example.testWork.services.BestOrderService;
import com.example.testWork.support.enumerations.OrderEventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@Validated
@RequestMapping("/order/event")
public class OrderEventController {

    @Autowired
    private BestOrderService orderService;

    @GetMapping("/register")
    public String registerOrder(@ModelAttribute("eventData") RegistrationEventDTO eventData) {
        return "order/event/register";
    }

    @PostMapping("/register")
    public String saveRegisterEvent(@ModelAttribute("eventData") @Valid RegistrationEventDTO eventData) {
        eventData.setEventType(orderService.getEventTypeName(OrderEventType.REGISTERED));
        var registeredOrder = orderService.addEvent(eventData);
        return "redirect:/order/" + registeredOrder.getOrder().getId();
    }

    @GetMapping("/cancel/{orderId}")
    public String cancelOrder(@PathVariable("orderId") int orderId,
                              @ModelAttribute("eventData") CancelEventDTO eventData) {
        orderService.checkOrderStatus(orderId);
        eventData.setOrderId(orderId);
        return "order/event/cancel";
    }

    @PostMapping("/cancel")
    public String saveCancelEvent(@ModelAttribute("eventData") @Valid CancelEventDTO eventData) {
        eventData.setEventType(orderService.getEventTypeName(OrderEventType.CANCELED));
        var processedOrder = orderService.addEvent(eventData);
        return "redirect:/order/" + processedOrder.getOrder().getId();
    }

    @GetMapping("/take/{orderId}")
    public String takeOrder(@PathVariable("orderId") int orderId,
                            @ModelAttribute("eventData") DefaultEventDTO eventData) {
        orderService.checkOrderStatus(orderId);
        eventData.setOrderId(orderId);
        return "order/event/take";
    }

    @PostMapping("/take")
    public String saveTakeEvent(@ModelAttribute("eventData") @Valid DefaultEventDTO eventData) {
        eventData.setEventType(orderService.getEventTypeName(OrderEventType.TAKEN));
        var processedOrder = orderService.addEvent(eventData);
        return "redirect:/order/" + processedOrder.getOrder().getId();
    }

    @GetMapping("/prepare/{orderId}")
    public String prepareOrder(@PathVariable("orderId") int orderId,
                               @ModelAttribute("eventData") DefaultEventDTO eventData) {
        orderService.checkOrderStatus(orderId);
        eventData.setOrderId(orderId);
        return "order/event/prepare";
    }

    @PostMapping("/prepare")
    public String savePrepareEvent(@ModelAttribute("eventData") @Valid DefaultEventDTO eventData) {
        eventData.setEventType(orderService.getEventTypeName(OrderEventType.PREPARED));
        var processedOrder = orderService.addEvent(eventData);
        return "redirect:/order/" + processedOrder.getOrder().getId();
    }

    @GetMapping("/give/{orderId}")
    public String giveOrder(@PathVariable("orderId") int orderId,
                            @ModelAttribute("eventData") DefaultEventDTO eventData) {
        orderService.checkOrderStatus(orderId);
        eventData.setOrderId(orderId);
        return "order/event/give";
    }

    @PostMapping("/give")
    public String saveGiveEvent(@ModelAttribute("eventData") @Valid DefaultEventDTO eventData) {
        eventData.setEventType(orderService.getEventTypeName(OrderEventType.GIVEN));
        var processedOrder = orderService.addEvent(eventData);
        return "redirect:/order/" + processedOrder.getOrder().getId();
    }
}

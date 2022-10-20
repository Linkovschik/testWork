package com.example.testWork.controllers;

import com.example.testWork.services.BestOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private BestOrderService orderService;

    @GetMapping()
    public String index(Model model) {
        var orders = orderService.getOrders();
        model.addAttribute("orders", orders);
        return "order/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        try {
            var orderInfo = orderService.prepareOrderInfo(id);
            model.addAttribute("orderInfo", orderInfo);
            return "order/show";
        } catch (RuntimeException ex) {
            return "order/index";
        }
    }
}

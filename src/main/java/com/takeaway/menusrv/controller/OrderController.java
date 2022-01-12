package com.takeaway.menusrv.controller;

import com.takeaway.menusrv.model.OrderInfo;
import com.takeaway.menusrv.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menusrv/api/")
public class OrderController {
    private OrderService orderService;

    @Autowired
    ConfigurableApplicationContext context;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping("/restaurants/{rid}/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderInfo upload(@RequestBody OrderInfo orderInfo){
        return this.orderService.saveOrderInfo(orderInfo);
    }

    @PostMapping("/restaurants/{rid}/{id}/update")
    public void orderComplete(@RequestBody OrderInfo order) {
        if (order.getUserInfo().getId() == null) {
            order.getUserInfo().setId("");
        }
        if (order.getPaymentId() == null) {
            order.setPaymentId("");
        }
        if (order.getSpecialNote() == null) {
            order.setSpecialNote("");
        }
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
        jmsTemplate.convertAndSend("orders", order);
    }

    @DeleteMapping("/restaurants/purge/{id}")
    public void deleteById(@PathVariable String id){
        orderService.deleteById(id);
    }

    @GetMapping("/restaurants/{id}")
    public OrderInfo getOrderInfoById(@PathVariable String id){
        return orderService.findOrderInfoById(id);
    }
}

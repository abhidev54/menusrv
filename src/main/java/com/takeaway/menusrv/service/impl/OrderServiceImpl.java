package com.takeaway.menusrv.service.impl;

import com.takeaway.menusrv.model.*;
import com.takeaway.menusrv.repository.OrderRepository;
import com.takeaway.menusrv.service.MenuItemService;
import com.takeaway.menusrv.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private MenuItemService menuItemService;

    @Autowired
    ConfigurableApplicationContext context;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, MenuItemService menuItemService){
        this.orderRepository = orderRepository;
        this.menuItemService = menuItemService;
    }

    @Override
    public OrderInfo saveOrderInfo(OrderInfo orderInfo) {
        orderInfo.setOrderTime(System.currentTimeMillis());
        orderInfo.setTotalPrice(orderInfo.getFoodItems().stream().mapToInt(e -> e.getPrice() * e.getQuantity()).sum());
        return orderRepository.save(orderInfo);
    }

    @Override
    public OrderInfo updateOrderStatus(OrderInfo orderInfo) {
        if(orderInfo.getOrderStatus().equals(OrderStatus.COMPLETED)) {
            JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
            jmsTemplate.convertAndSend("orders", orderInfo);
        }
        return orderRepository.save(orderInfo);
    }

    @JmsListener(destination = "orders")
    public void onOrderComplete(OrderInfo orderInfo) {
        log.info("Order completed : {} ", orderInfo.getId());
        for(FoodItem foodItem : orderInfo.getFoodItems()) {
            MenuItem menuItem = menuItemService.getMenuItemByName(foodItem.getName());

            // Update stock
            if(menuItem.getStock()-foodItem.getQuantity() > 0) {
                menuItem.setStock(menuItem.getStock() - foodItem.getQuantity());
            } else {
                menuItem.setStock(0);
                menuItem.setStockAvailability(StockAvailability.UNAVAILABLE);
            }
            menuItemService.saveMenuItem(menuItem);
        }
    }

    @Override
    public void deleteById(String id) {
        orderRepository.deleteById(id);
    }

    @Override
    public OrderInfo findOrderInfoById(String id) {
        return orderRepository.findFirstById(id);
    }
}

package com.takeaway.menusrv.service;

import com.takeaway.menusrv.model.OrderInfo;
import com.takeaway.menusrv.model.OrderStatus;

public interface OrderService {

    OrderInfo saveOrderInfo(OrderInfo orderInfo);

    OrderInfo updateOrderStatus(OrderInfo orderInfo);

    void deleteById(String id);

    void onOrderComplete(OrderInfo orderInfo);

    OrderInfo findOrderInfoById(String id);
}

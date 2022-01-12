package com.takeaway.menusrv.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderInfo implements Serializable {
    private static final long serialVersionUID = 3134592436804106224L;

    @Id
    private String id;

    private String restaurantId;

    private List<FoodItem> foodItems;

    private int totalPrice;

    private long orderTime;
    private long deliveryTime;

    private String specialNote;

    private String paymentId;

    private UserInfo userInfo;

    private OrderStatus orderStatus;

    public UserInfo getUserInfo(){
        return this.userInfo;
    }

}

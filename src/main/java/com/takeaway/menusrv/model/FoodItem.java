package com.takeaway.menusrv.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FoodItem implements Serializable {
    private static final long serialVersionUID = 3134592476804106224L;
    private String name;
    private int quantity;
    private int price;
}

package com.example.springbootexample.model;

import com.example.springbootexample.common.OrderStatus;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "order")
public class Order extends BaseModel {

    private String orderNumber;
    @DBRef
    private Item item;
    private int quantity;
    private OrderStatus orderStatus;



}

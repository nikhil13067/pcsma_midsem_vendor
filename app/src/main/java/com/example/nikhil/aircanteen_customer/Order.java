package com.example.nikhil.aircanteen_customer;

import java.util.ArrayList;
import java.util.*;

/**
 * Created by nikhil on 29/3/16.
 */
public class Order {
    HashMap<Item,Integer> items;
    Date orderTime;
    String type;

    public Order(HashMap<Item,Integer> items, Date orderTime, String type) {

        this.items = items;
        this.orderTime = orderTime;
        this.type = type;
    }
}

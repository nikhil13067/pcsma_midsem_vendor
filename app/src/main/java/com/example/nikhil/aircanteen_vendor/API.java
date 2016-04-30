package com.example.nikhil.aircanteen_vendor;


import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;


public interface API {

    /*Retrofit get annotation with our URL
       And our method that will return us the list ob Employee
    */
    @GET("/items/")
    public void getAllUsers(Callback<List<Item>> response);


    @POST("/items/")
    public void postUser(@Body Item body, Callback<Item> callback);


    @GET("/orders/")
    public void getAllOrders(Callback<List<Order>> response);

    @POST("/createorder/")
    public void postOrder(@Body Order body, Callback<Order> callback);
}
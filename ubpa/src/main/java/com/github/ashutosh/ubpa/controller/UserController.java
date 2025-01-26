package com.github.ashutosh.ubpa.controller;

import com.github.ashutosh.ubpa.dto.request.OrderRequestBody;
import com.github.ashutosh.ubpa.dto.request.UserRequestBody;
import com.github.ashutosh.ubpa.dto.response.UserResponseBody;
import com.github.ashutosh.ubpa.entity.Order;
import com.github.ashutosh.ubpa.entity.User;
import com.github.ashutosh.ubpa.service.OrderService;
import com.github.ashutosh.ubpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

     @Autowired
     private UserService userService;
     @Autowired
     private OrderService orderService;
    /*
     Sign up or create user
     URI:-  POST request /users
     return success message with user id
     */

     @PostMapping("/users")
     public ResponseEntity<UserResponseBody> signUpUser(@RequestBody UserRequestBody userRequestBody){

          User user=userService.registerUser(userRequestBody);
          UserResponseBody userResponseBody=UserResponseBody.generateResponseBodyFromUser(user);
          return new ResponseEntity(userResponseBody, HttpStatus.OK);
     }


     /*
     * Order products
     * URI :- POST request /users/{userId}/orders
     * request body :- list of products
     * return success message with order id
     * */

     @PostMapping("/users/{userId}/orders")
     public ResponseEntity<Order> placeOrder(@PathVariable long userId, @RequestBody OrderRequestBody orderRequestBody){

          Order order = orderService.placeOrder(orderRequestBody,userId);
          return new ResponseEntity<Order>(order,HttpStatus.OK);
     }


     /*
     * Get a user order
     * URI:- GET /users/{userId}/orders/{orderId}
     *  */

}

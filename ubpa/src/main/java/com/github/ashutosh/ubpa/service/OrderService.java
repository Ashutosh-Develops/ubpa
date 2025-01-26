package com.github.ashutosh.ubpa.service;

import com.github.ashutosh.ubpa.dto.request.OrderRequestBody;
import com.github.ashutosh.ubpa.entity.Order;
import com.github.ashutosh.ubpa.entity.Product;
import com.github.ashutosh.ubpa.entity.User;
import com.github.ashutosh.ubpa.repository.IOrderRepository;
import com.github.ashutosh.ubpa.repository.IProductRepository;
import com.github.ashutosh.ubpa.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private IUserRepository userRepository;

    public Order placeOrder(OrderRequestBody orderRequestBody,long userId){

        Order order = getOrderFromOrderRequestBody(orderRequestBody,userId);

        Order savedOrder = orderRepository.save(order);

        return savedOrder;
     }

    private Order getOrderFromOrderRequestBody(OrderRequestBody orderRequestBody,long id){

        long userId=id;

        List<Product> products=new ArrayList<>();
        double totalPrice=0;

        for(long productId:orderRequestBody.getProductIdList()){
            Product product=productRepository.findById(productId).orElseThrow(IllegalArgumentException::new);
            products.add(product);
            totalPrice+=product.getProductPrice();
        }

       // Get the user
        User user = userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);

        Order order = new Order();
        order.setUser(user);
        order.setProducts(products);
        order.setTotalPrice(totalPrice);
        order.setOrderCreationDateAndTime(LocalDateTime.now());

        return order;
    }
}

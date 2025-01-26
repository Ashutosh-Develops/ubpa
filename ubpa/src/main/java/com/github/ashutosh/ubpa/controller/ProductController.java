package com.github.ashutosh.ubpa.controller;

import com.github.ashutosh.ubpa.dto.request.ProductRequestBody;
import com.github.ashutosh.ubpa.dto.response.ProductResponseBody;
import com.github.ashutosh.ubpa.entity.Product;
import com.github.ashutosh.ubpa.service.KafkaService;
import com.github.ashutosh.ubpa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private KafkaService kafkaService;


    @PostMapping("/products")
    public ResponseEntity<ProductResponseBody> addProduct(@RequestBody ProductRequestBody productRequestBody){
        Product product=productService.addProduct(productRequestBody);
        ProductResponseBody productResponseBody=ProductResponseBody.generateResponseBodyFromProduct(product);
        return new ResponseEntity<ProductResponseBody>(productResponseBody, HttpStatus.OK);
    }

    /*
      Get a Product
    * URI:- GET request /product/{productId}
      returns product
    * */

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable long productId){
       Product product=productService.getProduct(productId);
       kafkaService.sendMessageWhenAProductIsRequested(product);
       return new ResponseEntity<Product>(product,HttpStatus.OK);
    }

}

package com.github.ashutosh.ubpa.service;

import com.github.ashutosh.ubpa.dto.request.ProductRequestBody;
import com.github.ashutosh.ubpa.dto.response.ProductResponseBody;
import com.github.ashutosh.ubpa.entity.Product;
import com.github.ashutosh.ubpa.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private IProductRepository productRepository;


    public Product addProduct(ProductRequestBody productRequestBody){

         // get product from request body

        Product product=getProductFromRequestBody(productRequestBody);

        // Save product instance in database
        Product savedProduct=productRepository.save(product);

        return savedProduct;

    }

    public Product getProduct(long productId){

        Product product=productRepository.findById(productId).orElseThrow(IllegalArgumentException::new);

        return product;
    }

    private Product getProductFromRequestBody(ProductRequestBody productRequestBody){


        long id = productRequestBody.getId();
        String productName= productRequestBody.getProductName();
        String productDescription=productRequestBody.getProductDescription();
        String productUrl=productRequestBody.getProductUrl();
        double productPrice=productRequestBody.getProductPrice();
        int productQuantity=productRequestBody.getProductQuantity();

        Product product=new Product();
        product.setProductName(productName);
        product.setProductDescription(productDescription);
        product.setProductUrl(productUrl);
        product.setProductPrice(productPrice);
        product.setProductQuantity(productQuantity);

        return product;
    }
}

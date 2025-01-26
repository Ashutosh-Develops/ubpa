package com.github.ashutosh.ubpa.dto.response;

import com.github.ashutosh.ubpa.entity.Product;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
public class ProductResponseBody {

    private long productId;

    public ProductResponseBody() {
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public static ProductResponseBody generateResponseBodyFromProduct(Product product){

        ProductResponseBody productResponseBody=new ProductResponseBody();
        productResponseBody.setProductId(product.getId());

        return productResponseBody;
    }
}

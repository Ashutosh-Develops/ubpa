package com.github.ashutosh.ubpa.dto.request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class OrderRequestBody {



    private List<Long> productIdList;


    public OrderRequestBody() {
    }


    public List<Long> getProductIdList() {
        return productIdList;
    }

    public void setProductIdList(List<Long> productIdList) {
        this.productIdList = productIdList;
    }
}

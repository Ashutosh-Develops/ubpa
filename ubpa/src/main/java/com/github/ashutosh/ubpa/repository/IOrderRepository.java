package com.github.ashutosh.ubpa.repository;

import com.github.ashutosh.ubpa.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends CrudRepository<Order,Long> {
}

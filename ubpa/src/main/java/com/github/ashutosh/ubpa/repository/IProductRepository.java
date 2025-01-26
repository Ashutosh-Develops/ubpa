package com.github.ashutosh.ubpa.repository;

import com.github.ashutosh.ubpa.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends CrudRepository<Product,Long> {
}

package com.demo.catalogservice.repo;

import com.demo.catalogservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatalogRepository extends JpaRepository<Order,Integer> {

    List<Order> findByCategory(String category);

}

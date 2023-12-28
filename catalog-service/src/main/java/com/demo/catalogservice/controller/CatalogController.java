package com.demo.catalogservice.controller;

import com.demo.catalogservice.entity.Order;
import com.demo.catalogservice.repo.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/orders")
public class CatalogController {

    @Autowired
    CatalogRepository catalogRepo;

    @PostConstruct
    public void initializeOrders(){
        catalogRepo.saveAll(
                Stream.of(
                        new Order("Puma","sports", "white", 3000),
                        new Order("Mobile phone","electronics", "white", 30000),
                        new Order("Basketball","sports", "blue", 300),
                        new Order("Vacuum cleaner","electronics", "black", 4000)

                ).collect(Collectors.toList()));
    }

    @GetMapping("/{category}")
    public List<Order> getOrdersByCategory(@PathVariable String category){
        return catalogRepo.findByCategory(category);
    }
}

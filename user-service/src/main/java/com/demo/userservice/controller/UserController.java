package com.demo.userservice.controller;

import com.demo.userservice.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/user-service")
public class UserController {

    @Autowired
    public RestTemplate restTemplate;

    private static final String USER_SERVICE = "userService";

    @GetMapping("/orders")
    @CircuitBreaker(name =USER_SERVICE,fallbackMethod = "getAllAvailableProducts")
    public List<OrderDTO> getOrdersByCategory(@RequestParam String category){
        String url = "http://localhost:9191/orders/"+category; //catalog URL
        return restTemplate.getForObject(url, ArrayList.class);
    }

    public List<OrderDTO> getAllAvailableProducts(Exception e){
        return Stream.of(
                new OrderDTO(119, "LED TV", "electronics", "white", 45000),
                new OrderDTO(118, "Mobile phone", "electronics", "grey", 12000),
                new OrderDTO(117, "Washing machine", "electronics", "pink", 12000),
                new OrderDTO(116, "Car", "automobile", "grey", 200000),
                new OrderDTO(115, "Truck", "automobile", "yellow", 91000)
        ).collect(Collectors.toList());
    }
}

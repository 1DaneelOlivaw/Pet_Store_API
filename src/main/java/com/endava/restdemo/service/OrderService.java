package com.endava.restdemo.service;

import com.endava.restdemo.model.Order;
import com.endava.restdemo.model.Person;
import com.endava.restdemo.model.SearchCriteria;
import com.endava.restdemo.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){this.orderRepository = orderRepository;}

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    public void save(Order order) {
        orderRepository.save(order);

    }

    public void delete(Long id) {
        orderRepository.delete(id);
    }
}

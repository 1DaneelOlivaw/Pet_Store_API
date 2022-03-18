package com.endava.restdemo.repository;

import com.endava.restdemo.model.Order;

import com.endava.restdemo.model.StatusOrder;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
@Repository
public class OrderRepository {

    private Map<Long, Order> orders = new HashMap<>();

    @PostConstruct
    private void init() {createOrders();}

    private void createOrders(){
        Order order = new Order();
        order.setId(1L);
        order.setPetId(1L);
        order.setQuantity(5);
        order.setShipDate(LocalDateTime.of(2019, Month.AUGUST, 15, 13, 23));
        order.setStatus(StatusOrder.delivered.name());
        order.setComplete(true);
        orders.put(order.getId(), order);
    }

    public void delete(Long id) {
        orders.remove(id);
    }

    public void save(Order order) {
        orders.compute(order.getId(), (key, value) -> order);
    }

    public Optional<Order> findById(Long id) {
        return Optional.ofNullable(orders.getOrDefault(id, null));
    }

    public List<Order> findAll() {
        return new ArrayList<>(orders.values());
    }


}

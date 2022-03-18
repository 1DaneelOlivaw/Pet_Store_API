package com.endava.restdemo.controller;

import com.endava.restdemo.exception.DataNotFoundException;
import com.endava.restdemo.exception.InvalidDataException;
import com.endava.restdemo.model.Order;
import com.endava.restdemo.model.Person;
import com.endava.restdemo.model.Pet;
import com.endava.restdemo.service.OrderService;
import com.endava.restdemo.service.PetService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Api(value = "Access to Petstore orders", tags = "/store")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/store")
public class StoreController {
    private final OrderService orderService;

    @ApiOperation(value = "Find purchase order by ID", response = Order.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation"),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Order not found!") })
    @GetMapping(path = "/order/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Order> getOrderById(@PathVariable Long id){
        if(id < 0)
        {
            throw new DataNotFoundException("Order not found");
        }
        return orderService.findById(id);
    }

    @ApiOperation(value = "Delete purchase order by ID", response = Order.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Order not found")})
    @DeleteMapping(path = "/order/{orderID}")
    public void deleteOrder(@PathVariable() Long id)
    {
        if(id < 0)
        {
            throw new DataNotFoundException("Order not found");
        }
        orderService.delete(id);
    }

    @Validated
    @ApiOperation(value = "Place an order for a pet", response = Order.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation"),
            @ApiResponse(code = 400, message = "Invalid Order")})
    @PostMapping(path = "/order",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void saveOrder(@RequestBody @ApiParam @Valid Order order, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
        {
            throw new InvalidDataException("invalid order");
        }

        orderService.save(order);
    }

    @ApiOperation(value = "Returns pet inventories by status", response = Order.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation")})
    @PostMapping(path = "/inventory", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getInventory() {
        return orderService.findAll();
    }

}

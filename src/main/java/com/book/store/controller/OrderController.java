package com.book.store.controller;

import com.book.store.controller.model.OrderRequest;
import com.book.store.dto.OrderDto;
import com.book.store.model.Order;
import com.book.store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

import static com.book.store.dto.Converter.orderToOrderDto;

@RestController
@RequestMapping(value = "/api/user")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order")
    public ResponseEntity<?> saveOrder(Principal principal, @RequestBody @Valid OrderRequest orderRequest) {
        this.orderService.saveOrder(principal.getName(), orderRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/order")
    public ResponseEntity<List<OrderDto>> getOrders(Principal principal) {
        List<Order> order = this.orderService.getOrders(principal.getName());
        return new ResponseEntity<List<OrderDto>>(orderToOrderDto(order), HttpStatus.OK);
    }

}

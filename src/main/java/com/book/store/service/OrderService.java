package com.book.store.service;

import com.book.store.controller.model.OrderRequest;
import com.book.store.controller.model.OrderRowRequest;
import com.book.store.exception.CartException;
import com.book.store.exception.UserException;
import com.book.store.model.Order;
import com.book.store.model.OrderItem;
import com.book.store.model.Product;
import com.book.store.model.User;
import com.book.store.model.singleton.DhlSingleton;
import com.book.store.model.singleton.InpostSingleton;
import com.book.store.repository.OrderRepository;
import com.book.store.repository.OrderRowRepository;
import com.book.store.repository.ProductRepository;
import com.book.store.repository.UserRepository;
import com.book.store.service.shipping.DhlStrategy;
import com.book.store.service.shipping.InpostStrategy;
import com.book.store.service.shipping.ShippingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private UserRepository userRepository;

    private OrderRowRepository orderRowRepository;

    private OrderRepository orderRepository;

    private ProductRepository productRepository;

    private static ShippingStrategy strategy;

    @Autowired
    public OrderService(UserRepository userRepository,
                        OrderRowRepository orderRowRepository,
                        OrderRepository orderRepository,
                        ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.orderRowRepository = orderRowRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public List<Order> getOrders(final String userName) {
        Optional<User> user = this.userRepository.findByEmail(userName);

        if (!user.isPresent()) {
            throw new UserException(String.format("Cant find user with name %s", userName));
        }

        Optional<List<Order>> orders = this.orderRepository.findAllByUserId(user.get().getId());

        if (orders.isPresent()) {
            return orders.get();
        }

        return new ArrayList<>();
    }

    public Order saveOrder(final String userName, final OrderRequest orderRowRequests) {

        if (orderRowRequests == null || orderRowRequests.getOrderRowRequest().isEmpty()) {
            throw new CartException("Can't save order");
        }

        Optional<User> userGet = this.userRepository.findByEmail(userName);

        if (!userGet.isPresent()) {
            throw new UserException("Can't find user");
        }

        Order order = new Order();
        order.setUser(userGet.get());

        Double totalPrice = calculateTotalPrice(orderRowRequests.getOrderRowRequest());

        order.setPrice(totalPrice);

        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderRowRequest orderRow : orderRowRequests.getOrderRowRequest()) {
            Optional<Product> product = this.productRepository.findById(orderRow.getProductId());

            if (product.isPresent()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setPrice(product.get().getPrice());
                orderItem.setName(product.get().getName());
                orderItem.setOrder(order);
                orderItems.add(orderItem);

                this.orderRowRepository.deleteById(orderRow.getOrderRowId());
            }
        }

        if (orderRowRequests.getShippingMethod().equals(InpostSingleton.getInstance().getName())) {
            order.setShippingMethod(InpostSingleton.getInstance().getName());
            order.setShippingPrice(InpostSingleton.getInstance().getPrice());

            strategy = new InpostStrategy();
            order.setTrackingNumber(strategy.generateTrackingNumber());
        } else {
            order.setShippingMethod(DhlSingleton.getInstance().getName());
            order.setShippingPrice(DhlSingleton.getInstance().getPrice());

            strategy = new DhlStrategy();
            order.setTrackingNumber(strategy.generateTrackingNumber());
        }

        order.setOrderItems(orderItems);

        return this.orderRepository.save(order);
    }

    private Double calculateTotalPrice(final List<OrderRowRequest> orderRowRequests) {
        double totalPrice = 0;

        for (OrderRowRequest orderRowRequest : orderRowRequests) {
            Optional<Product> dish = this.productRepository.findById(orderRowRequest.getProductId());

            if (dish.isPresent()) {
                totalPrice += dish.get().getPrice();
            }
        }

        return totalPrice;
    }

}

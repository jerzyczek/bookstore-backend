package com.book.store.service;

import com.book.store.controller.model.OrderRowRequest;
import com.book.store.exception.CartException;
import com.book.store.exception.ProductException;
import com.book.store.exception.UserException;
import com.book.store.model.Cart;
import com.book.store.model.OrderRow;
import com.book.store.model.Product;
import com.book.store.model.User;
import com.book.store.model.factory.cart.AbstractCartFactory;
import com.book.store.model.factory.cart.CartFactory;
import com.book.store.model.factory.orderrow.AbstractOrderRowFactory;
import com.book.store.model.factory.orderrow.OrderRowFactory;
import com.book.store.repository.CartRepository;
import com.book.store.repository.OrderRowRepository;
import com.book.store.repository.ProductRepository;
import com.book.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private CartRepository cartRepository;

    private UserRepository userRepository;

    private ProductRepository productRepository;

    private OrderRowRepository orderRowRepository;

    @Autowired
    public CartService(CartRepository cartRepository,
                       UserRepository userRepository,
                       ProductRepository productRepository,
                       OrderRowRepository orderRowRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderRowRepository = orderRowRepository;
    }

    public Cart getCart(final String userName) {
        Optional<User> userOptional = this.userRepository.findByEmail(userName);

        if (!userOptional.isPresent()) {
            throw new UserException("Can't get user");
        }

        User user = userOptional.get();

        final Cart cart = Optional.ofNullable(user.getCart()).orElse(new Cart());

        return cart;
    }

    public Cart saveCart(final String userName, final OrderRowRequest cartRequest) {

        if (cartRequest == null) {
            throw new CartException("Can't save cart");
        }

        Optional<User> user = this.userRepository.findByEmail(userName);

        if (!user.isPresent()) {
            throw new UserException("Can't find user");
        }

        Cart cartUser = Optional.ofNullable(user.get().getCart()).orElse(null);

        if (cartUser == null) {
            Cart cart = new Cart();
            List<OrderRow> orderRows = fillByOrderRowNew(cartRequest);
            AbstractCartFactory abstractCartFactory = new CartFactory();
            abstractCartFactory.createCart(cart, user.get(), orderRows);
            user.get().setCart(cart);

            return this.cartRepository.save(cart);
        }

        List<OrderRow> orderRows = fillListByOrderRow(cartRequest, cartUser);
        cartUser.setOrderRows(orderRows);
        user.get().setCart(cartUser);

        for (OrderRow orderRow : orderRows) {
            this.orderRowRepository.save(orderRow);
        }

        return this.cartRepository.save(cartUser);
    }

    public void deleteCart(final String userName, final Long orderRowId) {
        Optional<User> userGet = this.userRepository.findByEmail(userName);

        if (!userGet.isPresent()) {
            throw new UserException(String.format("Can't get user with name: %s", userName));
        }

        User user = userGet.get();

        Cart cartUser = Optional.ofNullable(user.getCart()).orElse(null);

        if (cartUser != null) {
            Optional<OrderRow> orderRow = this.orderRowRepository.findById(orderRowId);

            if (orderRow.isPresent()) {
                this.orderRowRepository.deleteById(orderRow.get().getId());
            }
        }
    }

    private List<OrderRow> fillByOrderRowNew(final OrderRowRequest cartRequest) {
        List<OrderRow> orderRows = new ArrayList<>();

        AbstractOrderRowFactory orderRowFactory = new OrderRowFactory();
        orderRows.add(orderRowFactory.createOrderRow(getProduct(cartRequest.getProductId())));
        return orderRows;
    }

    private List<OrderRow> fillListByOrderRow(final OrderRowRequest cartRequest, Cart cart) {
        List<OrderRow> orderRows = new ArrayList<>();

        AbstractOrderRowFactory orderRowFactory = new OrderRowFactory();
        OrderRow orderRow = orderRowFactory.createOrderRow(getProduct(cartRequest.getProductId()));
        orderRow.setCart(cart);
        orderRows.add(orderRow);

        return orderRows;
    }

    private Product getProduct(final Long id) {
        Optional<Product> product = this.productRepository.findById(id);

        if (product.isPresent()) {
            return product.get();
        }

        throw new ProductException("Cant find product");
    }

}

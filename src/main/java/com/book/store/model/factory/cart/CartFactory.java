package com.book.store.model.factory.cart;

import com.book.store.model.Cart;
import com.book.store.model.OrderRow;
import com.book.store.model.User;

import java.util.List;

public class CartFactory extends AbstractCartFactory {

    @Override
    public void createCart(Cart cart, User user, List<OrderRow> orderRows) {
        cart.setOrderRows(orderRows);
        cart.setUser(user);
    }
}

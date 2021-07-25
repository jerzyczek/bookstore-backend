package com.book.store.model.factory.cart;

import com.book.store.model.Cart;
import com.book.store.model.OrderRow;
import com.book.store.model.User;

import java.util.List;

public abstract class AbstractCartFactory {

    public abstract void createCart(Cart cart, User user, List<OrderRow> orderRows);

}

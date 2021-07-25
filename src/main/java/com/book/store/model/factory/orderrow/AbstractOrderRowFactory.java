package com.book.store.model.factory.orderrow;

import com.book.store.model.OrderRow;
import com.book.store.model.Product;

public abstract class AbstractOrderRowFactory {

    public abstract OrderRow createOrderRow(Product product);

}

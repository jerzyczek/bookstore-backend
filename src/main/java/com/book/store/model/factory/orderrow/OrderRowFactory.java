package com.book.store.model.factory.orderrow;

import com.book.store.model.OrderRow;
import com.book.store.model.Product;

public class OrderRowFactory extends AbstractOrderRowFactory {

    @Override
    public OrderRow createOrderRow(Product product) {
        OrderRow orderRow = new OrderRow();
        orderRow.setProduct(product);
        orderRow.setQuantity(1);

        return orderRow;
    }
}

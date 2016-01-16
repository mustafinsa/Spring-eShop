package com.mustafinsa.spring.eshop.model;

import java.util.List;

public interface ShoppingCartDao {
    void saveOrUpdate(ShoppingCart shoppingCart);

    boolean currentCartExist(String username, int id);

    ShoppingCart getCart(String username, int itemId);

    List<ShoppingCart> getCarts();
}

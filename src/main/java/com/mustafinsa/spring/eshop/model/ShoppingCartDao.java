package com.mustafinsa.spring.eshop.model;

import java.util.List;

public interface ShoppingCartDao {
    void saveOrUpdate(ShoppingCart shoppingCart);

    boolean currentCartExist(String username);

    List<ShoppingCart> getCarts();
}

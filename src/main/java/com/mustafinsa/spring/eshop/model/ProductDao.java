package com.mustafinsa.spring.eshop.model;

import java.util.List;

public interface ProductDao {

    void saveOrUpdate(Product product);

    Product getProduct(int id);

    List<Product> getProducts();

    boolean delete(int id);
}

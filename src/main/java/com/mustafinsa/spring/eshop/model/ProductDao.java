package com.mustafinsa.spring.eshop.model;

import java.util.List;

public interface ProductDao {

    void save(Product product);

    Product getById(int id);

    void update(Product product);

    void deleteById(int id);

    List<Product> getAll();
}

package com.mustafinsa.spring.eshop.model;

import java.util.List;

public interface ProductDao {

    //Create
    void save(Product product);

    //Read
    Product getById(int id);

    //Update
    void update(Product product);

    //Delete
    void deleteById(int id);

    //Get All
    List<Product> getAll();
}
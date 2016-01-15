package com.mustafinsa.spring.eshop.model;

import java.util.List;

public interface UsersDao {

    void create(User user);

    List<User> getAllUsers();

    boolean exists(String username);

    User getUser(String username);
}

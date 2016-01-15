package com.mustafinsa.spring.eshop.service;

import com.mustafinsa.spring.eshop.model.User;
import com.mustafinsa.spring.eshop.model.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {
    @Autowired
    private UsersDao usersDao;

    public void create(User user) {
        usersDao.create(user);
    }

    public boolean exists(String username) {
        return usersDao.exists(username);
    }

    public List<User> getAllUsers() {
        return usersDao.getAllUsers();
    }
}

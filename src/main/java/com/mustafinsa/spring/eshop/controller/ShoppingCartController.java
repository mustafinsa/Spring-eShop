package com.mustafinsa.spring.eshop.controller;

import com.mustafinsa.spring.eshop.model.ShoppingCart;
import com.mustafinsa.spring.eshop.model.ShoppingCartDao;
import com.mustafinsa.spring.eshop.model.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ShoppingCartController {

    @Autowired
    private ShoppingCartDao shoppingCartDao;
    @Autowired
    private UsersDao usersDao;

    @RequestMapping(value="postCart", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody Map<String, Object> getCartItem(Principal principal, @RequestBody Map<String, Object> data) {

        int id = Integer.parseInt((String) data.get("target"));
        shoppingCartDao.saveOrUpdate(new ShoppingCart(id, usersDao.getUser(principal.getName()), 1, false));
        if (!shoppingCartDao.currentCartExist(principal.getName())) {
            System.out.println(id + " not exists");
        } else {

            System.out.println(id + " exist");
        }

        Map<String, Object> rval = new HashMap<>();
        return rval;
    }
}

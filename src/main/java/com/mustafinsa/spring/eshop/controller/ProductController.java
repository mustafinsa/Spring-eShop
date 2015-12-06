package com.mustafinsa.spring.eshop.controller;

import com.mustafinsa.spring.eshop.model.ProductDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {

    @Autowired
    ProductDao productDao;
    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String productAll(Model model){
        model.addAttribute("productList", productDao.getAll());
        return "productAll";
    }

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String product(@RequestParam("id") int id, Model model) {
        model.addAttribute("product", productDao.getById(id));
        return "product";
    }
}

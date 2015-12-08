package com.mustafinsa.spring.eshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping
    public String logout() {
        return "logout";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "adminPage";
    }
}

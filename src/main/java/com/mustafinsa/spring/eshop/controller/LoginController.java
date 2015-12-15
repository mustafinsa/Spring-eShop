package com.mustafinsa.spring.eshop.controller;

import com.mustafinsa.spring.eshop.model.User;
import com.mustafinsa.spring.eshop.model.UsersDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class LoginController {
    private UsersDao usersDao;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "logout";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "adminPage";
    }

    @RequestMapping("/newAccount")
    public String newAccount(Model model, @Valid User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            if (usersDao.exists(user.getUsername())) {
                bindingResult.rejectValue("username", "DuplicateKey.user.username");
            }
            return "newAccount";
        }
        user.setEnabled(true);
        user.setAuthority("ROLE_USER");
        usersDao.create(user);
        model.addAttribute("user", user);
        return "createdAccount";
    }
}

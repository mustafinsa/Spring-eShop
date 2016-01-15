package com.mustafinsa.spring.eshop.controller;

import com.mustafinsa.spring.eshop.model.FormValidationGroup;
import com.mustafinsa.spring.eshop.model.User;
import com.mustafinsa.spring.eshop.model.UsersDao;
import com.mustafinsa.spring.eshop.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    UsersService usersService;

    @RequestMapping("/login")
    public String showLogin() {
        return "login";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "logout";
    }

    @RequestMapping("/admin")
    public String showAdminPage(Model model) {
        List<User> users = usersService.getAllUsers();
        model.addAttribute("users", users);
        return "admin";
    }

    @RequestMapping("/newAccount")
    public String showNewAccount(Model model) {
        model.addAttribute("user", new User());
        return "newAccount";
    }

    @RequestMapping(value = "/createAccount", method = RequestMethod.POST)
    public String createAccount(
            @Validated(FormValidationGroup.class) User user,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            if (usersService.exists(user.getUsername())) {
                bindingResult.rejectValue("username", "DuplicateKey.user.username");
            }
            return "newAccount";
        }
        user.setEnabled(true);
        user.setAuthority("ROLE_USER");
        usersService.create(user);
        return "createdAccount";
    }
}

package com.jacksoch.controller;

import com.jacksoch.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PageController {

    @GetMapping
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/register")
    public String register(Model model) {
        User user = new User();
        //Add an empty user object to this page's 'model'.
        model.addAttribute("user", user);

        return "register";
    }

    @PostMapping("/register")
    public String newUser(@ModelAttribute("user") User user) {
        System.out.println(user);
        return "index";
    }
}

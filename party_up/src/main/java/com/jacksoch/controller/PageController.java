package com.jacksoch.controller;

import com.jacksoch.dao.GroupDao;
import com.jacksoch.dao.JoinRequestDao;
import com.jacksoch.dao.MessageDao;
import com.jacksoch.dao.UserDao;
import com.jacksoch.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class PageController {

    private UserDao userDao;
    private MessageDao messageDao;
    private GroupDao groupDao;
    private JoinRequestDao requestDao;

    public PageController(UserDao userDao, MessageDao messageDao, GroupDao groupDao, JoinRequestDao requestDao) {
        this.userDao = userDao;
        this.messageDao = messageDao;
        this.groupDao = groupDao;
        this.requestDao = requestDao;
    }

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

        if (userDao.getUserByEmail(user.getEmail()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This email is already in use.");
        }

        if (userDao.getUserByUsername(user.getUsername()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This username is already in use.");
        }

        userDao.createUser(user);
        return "index";
    }

    @GetMapping("/games")
    public String games(Model model) {
        //Adds all groups to the page as an 'attribute' to be accessed by Thymeleaf when rendering HTML
        model.addAttribute("groups", groupDao.getAllGroups());

        return "game-postings";
    }
}

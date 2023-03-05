package com.jacksoch.controller;

import com.jacksoch.dao.UserDao;
import com.jacksoch.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    /*
    ########################################   Attributes   ##########################################
     */

    private UserDao dao;

    /*
   ########################################   Constructor   ##########################################
    */

    public UserController(UserDao dao) {this.dao = dao;}

    /*
   ########################################  API Endpoints  ##########################################
    */

    @GetMapping
    public List<User> list(@RequestParam(required = false, name = "name-like") String nameLike,
                           @RequestParam(required = false) Integer groupId) {
        List<User> users = null;

        if (groupId != null) {
            users = dao.getUsersByGroup(groupId);
        } else if (nameLike != null) {
            users = dao.getAllUsersWithNameContaining(nameLike);
        } else {
            users = dao.getAllUsers();
        }
        return users;
    }

    @GetMapping("/{id}")
    public User get(@PathVariable int id) {
        User user = dao.getUser(id);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unknown User");
        }
        return user;
    }

    //ToDo: Validate User
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public User create(@RequestBody User user) {
        return dao.createUser(user);
    }

    //ToDo: Validate User
    @PutMapping("/{id}")
    public User update(@RequestBody User user, @PathVariable int id) {
        if (dao.getUser(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unknown User");
        }
        return dao.updateUser(user, id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{id}")
    public void delete(int id) {
        dao.deleteUser(id);
    }


}

package com.jacksoch.controller.api;

import com.jacksoch.dao.UserDao;
import com.jacksoch.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
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

        //Handle being given multiple RequestParameters
        if (groupId != null && nameLike != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only one request parameter can be specified in "
                    + "the URL.");
        }

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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public User create(@Valid @RequestBody @ModelAttribute("user") User user) {
        if (dao.getUserByUsername(user.getUsername()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A user with this username already exists.");
        } if (dao.getUserByEmail(user.getEmail()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This email address is already in use.");
        }
        return dao.createUser(user);
    }

    //ToDo: Validate User
    @PutMapping("/{id}")
    public User update(@Valid @RequestBody User user, @PathVariable int id) {
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

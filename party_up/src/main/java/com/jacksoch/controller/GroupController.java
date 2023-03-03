package com.jacksoch.controller;

import com.jacksoch.dao.GroupDao;
import com.jacksoch.model.Group;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {


    /*
    ########################################   Attributes   ##########################################
     */

    private GroupDao dao;

    public GroupController(GroupDao dao) {
        this.dao = dao;
    }
    /*
   ########################################   Constructor   ##########################################
    */

    @GetMapping
    public List<Group> getAll(@RequestParam(required = false) Integer ownerId,
                              @RequestParam(required = false) Integer playerId) {

        List<Group> groups;

        //Check for user search parameters and use appropriate dao methods to get appropriate List
        if (ownerId != null && playerId != null) {
            groups = dao.getGroupsByOwnerAndPlayer(ownerId, playerId);
        } else if (ownerId != null) {
            groups = dao.getGroupsByOwner(ownerId);
        } else if (playerId != null) {
            groups = dao.getGroupsByPlayer(playerId);
        } else {
            groups = dao.getAllGroups();
        }
        return groups;
    }

    @GetMapping("/{id}")
    public Group getById(@PathVariable int id) {
        Group group = dao.getGroup(id);

        //Handle if not found
        if (group == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        //Return value
        return group;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Group create(@RequestBody Group group) {
        return dao.createGroup(group);
    }

    @PutMapping("/{id}")
    public Group update(@RequestBody Group group, @PathVariable int id) {
        //Handle Case if Does Not Exist
        if (dao.getGroup(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unknown Group");
        }
        return dao.updateGroup(group, id);
    }

    //ToDo: ADD VALIDATION
    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        dao.deleteGroup(id);
    }



}

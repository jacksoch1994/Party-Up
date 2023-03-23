package com.jacksoch.controller.api;

import com.jacksoch.dao.GroupDao;
import com.jacksoch.dao.JoinRequestDao;
import com.jacksoch.dao.UserDao;
import com.jacksoch.model.JoinRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/join-requests")
public class JoinRequestController {

    /*
    ########################################   Attributes   ##########################################
     */

    private JoinRequestDao joinRequestDao;
    private UserDao userDao;
    private GroupDao groupDao;


    /*
   ########################################   Constructor   ##########################################
    */

    public JoinRequestController(JoinRequestDao joinRequestDao, UserDao userDao, GroupDao groupDao) {
        this.joinRequestDao = joinRequestDao;
        this.userDao = userDao;
        this.groupDao = groupDao;
    }

    /*
   ########################################  API Endpoints  ##########################################
    */

    @GetMapping
    public List<JoinRequest> getAll(@RequestParam(required = false) Integer groupId,
                                    @RequestParam(required = false) Integer playerId) {

        List<JoinRequest> requests = new ArrayList<>();

        if (playerId != null && groupId != null) {
            requests.add(joinRequestDao.getJoinRequest(playerId, groupId));
        } else if (playerId != null) {
            requests = joinRequestDao.getAllJoinRequestsByPlayer(playerId);
        } else if (groupId != null) {
            requests = joinRequestDao.getAllJoinRequestsByGroup(groupId);
        } else {
            requests = joinRequestDao.getAllJoinRequests();
        }

        return requests;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(JoinRequest joinRequest) {
        if (userDao.getUser(joinRequest.getPlayer_id()) != null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unknown User.");
        } else if (groupDao.getGroup(joinRequest.getGroup_id()) != null) {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Unknown Group");
        }

        joinRequestDao.createJoinRequest(joinRequest);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping
    public void delete(JoinRequest joinRequest) {
        joinRequestDao.deleteJoinRequest(joinRequest);
    }

}

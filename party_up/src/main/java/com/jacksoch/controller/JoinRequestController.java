package com.jacksoch.controller;

import com.jacksoch.dao.JoinRequestDao;
import com.jacksoch.model.JoinRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/join-requests")
public class JoinRequestController {

    /*
    ########################################   Attributes   ##########################################
     */

    private JoinRequestDao dao;

    /*
   ########################################   Constructor   ##########################################
    */

    public JoinRequestController(JoinRequestDao dao) {this.dao = dao;}

    /*
   ########################################  API Endpoints  ##########################################
    */

    @GetMapping
    public List<JoinRequest> getAll(@RequestParam(required = false) Integer groupId,
                                    @RequestParam(required = false) Integer playerId) {

        List<JoinRequest> requests = new ArrayList<>();

        if (playerId != null && groupId != null) {
            requests.add(dao.getJoinRequest(playerId, groupId));
        } else if (playerId != null) {
            requests = dao.getAllJoinRequestsByPlayer(playerId);
        } else if (groupId != null) {
            requests = dao.getAllJoinRequestsByGroup(groupId);
        } else {
            requests = dao.getAllJoinRequests();
        }

        return requests;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(JoinRequest joinRequest) {
        dao.createJoinRequest(joinRequest);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping
    public void delete(JoinRequest joinRequest) {
        dao.deleteJoinRequest(joinRequest);
    }

}

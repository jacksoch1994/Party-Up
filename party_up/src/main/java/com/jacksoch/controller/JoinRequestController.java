package com.jacksoch.controller;

import com.jacksoch.model.JoinRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("join-requests")
public class JoinRequestController {

    public List<JoinRequest> getAll(@RequestParam(required = false) ) {
        List<JoinRequest> requests null;
    }
}

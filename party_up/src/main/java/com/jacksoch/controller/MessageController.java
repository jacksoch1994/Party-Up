package com.jacksoch.controller;

import com.jacksoch.dao.MessageDao;
import com.jacksoch.model.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/messages")
public class MessageController {

    private MessageDao dao;

    public MessageController(MessageDao dao) {
        this.dao = dao;
    }

    @GetMapping()
    public List<Message> get(@RequestParam(required = false) Integer senderId, @RequestParam(required = false) Integer receiverId) {
        List<Message> messages = new ArrayList<>();

        if (senderId != null && receiverId != null) {
            messages = dao.getMessagesBySenderReceiver(senderId, receiverId);
        } else if (senderId != null) {
            messages = dao.getMessagesBySender(senderId);
        } else if (receiverId != null) {
            messages = dao.getMessagesByReceiver(receiverId);
        }
        return messages;
    }
}

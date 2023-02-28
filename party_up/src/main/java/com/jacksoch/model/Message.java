package com.jacksoch.model;

import java.time.LocalDateTime;

public class Message {

    /*
    ########################################   Attributes   ##########################################
     */

    private int id;
    private int senderId;
    private int receiverId;
    LocalDateTime timestamp = LocalDateTime.now();
    private String content;

    /*
    ######################################## Getter Methods ##########################################
     */

    public int getId() {
        return id;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getContent() {
        return content;
    }

    /*
    ######################################## Setter Methods ##########################################
     */

    public void setId(int id) {
        this.id = id;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public void setReceiverId(int recieverId) {
        this.receiverId = recieverId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTimestamp(LocalDateTime timestamp) {this.timestamp = timestamp;};

    /*
    ########################################   Constructor   ##########################################
     */

    //Constructor for all required fields. LocalDateTime field is defaulted to the time of creation.
    public Message(int id, int senderId, int receiverId, String content) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
    }

    public Message(int id, int senderId, int receiverId, String content, LocalDateTime timestamp) {
        this(id, senderId, receiverId, content);
        this.timestamp = timestamp;
    }

}

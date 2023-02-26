package com.jacksoch.model;

import java.time.LocalDateTime;

public class Message {

    /*
    ########################################   Attributes   ##########################################
     */

    private int id;
    private int senderId;
    private int recieverId;
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

    public int getRecieverId() {
        return recieverId;
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

    public void setRecieverId(int recieverId) {
        this.recieverId = recieverId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /*
    ########################################   Constructor   ##########################################
     */

    //Constructor for all required fields. LocalDateTime field is defaulted to the time of creation and cannot be changed.
    public Message(int id, int senderId, int recieverId, String content) {
        this.id = id;
        this.senderId = senderId;
        this.recieverId = recieverId;
        this.content = content;
    }

}

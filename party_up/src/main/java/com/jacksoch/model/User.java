package com.jacksoch.model;

public class User {

    /*
    ########################################   Attributes   ##########################################
     */

    private int id;
    private String username;
    private String email;
    private String phone;
    private String pfpURL;

    /*
    ######################################## Getter Methods ##########################################
     */

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPfpURL() {
        return pfpURL;
    }

    /*
    ######################################## Setter Methods ##########################################
     */

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPfpURL(String pfpURL) {
        this.pfpURL = pfpURL;
    }

    /*
    ########################################   Constructor   ##########################################
     */

    //Constructor with only NOT NULL values
    public User(int user_id, String username, String email) {
        this.id = user_id;
        this.username = username;
        this.email = email;
    }

    //Constructor with all values
    public User(int user_id, String username, String email, String phone, String pfpURL) {
        this(user_id, username, email);
        this.phone = phone;
        this. pfpURL = pfpURL;
    }

}

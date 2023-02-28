package com.jacksoch.model;

public class Group {

    /*
    ########################################   Attributes   ##########################################
     */

    private int id;
    private int ownerId;
    private String title;
    private String game;
    private int maxPlayerCount;
    private boolean isAcceptingNewPlayers = true;
    private String description;
    private String location;



    /*
    ######################################## Getter Methods ##########################################
     */

    public int getId() {
        return id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public String getTitle() {
        return title;
    }

    public String getGame() {
        return game;
    }

    public int getMaxPlayerCount() {
        return maxPlayerCount;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public boolean isAcceptingNewPlayers() {
        return isAcceptingNewPlayers;
    }

    /*
    ######################################## Setter Methods ##########################################
     */

    public void setId(int id) {
        this.id = id;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public void setMaxPlayerCount(int maxPlayerCount) {
        this.maxPlayerCount = maxPlayerCount;
    }

    public void setAcceptingNewPlayers(boolean acceptingNewPlayers) {
        isAcceptingNewPlayers = acceptingNewPlayers;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    /*
    ########################################   Constructor   ##########################################
     */

    //Constructor for required fields
    public Group(int id, int ownerId, String title, String game, int maxPlayerCount) {
        this.id = id;
        this.ownerId = ownerId;
        this.title = title;
        this.game = game;
        this.maxPlayerCount = maxPlayerCount;
    }

    //Constructor for all fields
    public Group(int id, int ownerId, String title, String game, int maxPlayerCount, boolean isAcceptingNewPlayers,
                 String description, String location) {
        this(id, ownerId, title, game, maxPlayerCount);
        this.isAcceptingNewPlayers = isAcceptingNewPlayers;
        this.description = description;
        this.location = location;
    }
}

package com.jacksoch.model;

public class JoinRequest {

    /*
    ########################################   Attributes   ##########################################
     */

    private int player_id;
    private int group_id;

    /*
    ######################################## Getter Methods ##########################################
     */

    public int getPlayer_id() {
        return player_id;
    }

    public int getGroup_id() {
        return group_id;
    }

    /*
    ######################################## Setter Methods ##########################################
     */

    public void setPlayer_id(int player_id) {
        this.player_id = player_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    /*
    ########################################   Constructor   ##########################################
     */

    public JoinRequest(int player_id, int group_id) {
        this.player_id = player_id;
        this.group_id = group_id;
    }

    public JoinRequest() {}

}

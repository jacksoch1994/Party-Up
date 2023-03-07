package com.jacksoch.dao;

import com.jacksoch.model.Group;

import java.util.List;

public interface GroupDao {

    /**
     * Get a List of all Groups.
     *
     * @return a list containing all Groups.
     */
    List<Group> getAllGroups();

    /**
     * Get a Group with the specified groupId. Returns null if not found.
     *
     * @param groupId the id of the playgroup
     * @return the Group with the specified id
     */
    Group getGroup(int groupId);

    /**
     * Get a List of Groups owned by a specific user with the specified id.
     *
     * @param ownerId userId of the group owner
     * @return a List of Groups owned by the specified owner
     */
    List<Group> getGroupsByOwner(int ownerId);

    /**
     * Get a List of Groups that the specified User is a player in.
     *
     * @param playerId userId of the player
     * @return a List of Groups that the User is a player in
     */
    List<Group> getGroupsByPlayer(int playerId);

    /**
     * Get a List of Groups owned by a specific user with the specified id that the specified player is also in.
     *
     * @param ownerId userId of the group owner
     * @param playerId userId of the player
     * @return a List of Groups owned by the specified owner with the specified player
     */
    List<Group> getGroupsByOwnerAndPlayer(int ownerId, int playerId);

    /**
     * Creates a new Group in the database.
     *
     * @param group the Group to add.
     * @return the newly added Group.
     */
    Group createGroup(Group group);

    /**
     * Updates a Group in the database.
     *
     * @param group a Group object containing the updated values.
     * @param id the id of the object to update
     * @return the newly updated Group.
     */
    Group updateGroup(Group group , int id);

    /**
     * Returns the current number of players in the Group associated with the group id. Returns 0 if the group was not
     * found.
     *
     * @param groupId the id of the Group.
     * @return the number of players in the group as an int
     */
    int playerCount(int groupId);

    /**
     * Adds a User to the Group. Returns true if successful, and false if the max player count was already reached.
     *
     * @param groupId the id of the Group to add the player to.
     * @param playerId the id of the User to add to the Group.
     * @return boolean value indicating a successful operation
     */
    boolean addPlayer(int groupId, int playerId);

    /**
     * Removes a group from the database.
     *
     * @param id the id of the Group to remove from the database.
     */
    void deleteGroup(int id);
}

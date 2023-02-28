package com.jacksoch.dao;

import com.jacksoch.model.Group;

import java.util.List;

public interface GroupDao {

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
     * Creates a new Group in the database.
     *
     * @param group the Group to add.
     * @return the newly added Group.
     */
    Group createGroup(Group group);

    /**
     * Updates a Group in the database.
     *
     * @param group the Group to update.
     * @return the newly updated Group.
     */
    Group updateGroup(Group group);

    /**
     * Removes a group from the database.
     *
     * @param group the Group to remove from the database.
     */
    void deleteGroup(Group group);
}

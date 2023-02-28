package com.jacksoch.dao;

import com.jacksoch.model.JoinRequest;

public interface JoinRequestDao {

    /**
     * Get a JoinRequest with the specified playerId and JoinId. Returns null if not found.
     *
     * @param playerId id of the User that made the join request
     * @param groupId id of the Group the User made a request to join
     * @return a JoinRequest object
     */
    JoinRequest getJoinRequest(int playerId, int groupId);

    /**
     * Creates a new JoinRequest with the specified playerId and JoinId.
     *
     * @param newRequest the new JoinRequest object to add.
     */
    void createJoinRequest(JoinRequest newRequest);


    /**
     * Deletes an existing JoinRequest with the specified playerId and JoinId.
     *
     * @param newRequest JoinRequest object to remove.
     */
    void deleteJoinRequest(JoinRequest newRequest);
}

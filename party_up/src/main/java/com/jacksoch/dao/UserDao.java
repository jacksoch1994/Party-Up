package com.jacksoch.dao;

import com.jacksoch.model.User;

import java.util.List;

public interface UserDao {

    /**
     * Return the User corresponding to the userId provided. If user does not exist, return null.
     *
     * @param userId id of the User
     * @return the User assigned to the provided UserId
     */
    User getUser(int userId);

    /**
     * Return the User corresponding to the username provided. If user does not exist, return null.
     *
     * @param username username of the User
     * @return the User assigned to the provided username
     */
    User getUserByUsername(String username);


    /**
     * Return a list of Users that are players in the playgroup with the provided groupId.
     *
     * @param groupId groupId of the specified playgroup
     * @return a List of Users in the playgroup
     */
    List<User> getUsersByGroup(int groupId);

    /**
     * Creates a new User in the database.
     *
     * @param user User object to add to the database.
     * @return the newly added User
     */
    User createUser(User user);

    /**
     * Updates the values of a User in the database.
     *
     * @param user the User object to update the database with
     * @return the newly updated User
     */
    User updateUser(User user);

}

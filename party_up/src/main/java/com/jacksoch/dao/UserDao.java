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
     * Return a List of all Users.
     *
     * @return a List of all Users.
     */
    List<User> getAllUsers();

    /**
     * Return a List of all Users containing the specified string in their username.
     *
     * @param searchParam the String to search for in all Users' usernames.
     * @return a List of all Users with the specified substring in their username.
     */
    List<User> getAllUsersWithNameContaining(String searchParam);

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
    User updateUser(User user, int id);

    /**
     * Deletes a user from the database.
     *
     * @param userId the id of the User to remove.
     */
    void deleteUser(int userId);

}

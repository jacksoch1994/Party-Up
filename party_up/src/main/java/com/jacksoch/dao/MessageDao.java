package com.jacksoch.dao;

import com.jacksoch.model.Message;

import java.util.List;

public interface MessageDao {

    /**
     * Get a Message from the database with the given messageId. If a Message is not found, return null;
     *
     * @param messageId the id of the Message
     * @return the Message with the given id
     */
    Message getMessage(int messageId);

    /**
     * Get a list of all Messages sent by a User with a given id.
     *
     * @param senderId userId of the sender
     * @return list of Messages sent by the specified user
     */
    List<Message> getMessagesBySender(int senderId);

    /**
     * Get a list of all Messages received by a User with a given id.
     *
     * @param receiverId userId of the receiver
     * @return list of Messages received by the specified user
     */
    List<Message> getMessagesByReceiver(int receiverId);

    /**
     * Get a list of all Messages sent by one User with the given senderId and received by another User with a given
     * receiverId.
     *
     * @param senderId
     * @param receiverId
     * @return
     */
    List<Message> getMessagesBySenderReceiver(int senderId, int receiverId);

    /**
     * Updates a message to reflect the values in the provided Message. Does not update the timestamp,
     * senderId, or receiverId of the message.
     *
     * @param updatedMessage Message object with the id of the Message to be updated.
     * @return the updated Message object
     */
    Message updateMessage(Message updatedMessage, int id);

    /**
     * Adds a new message to the database.
     *
     * @param newMessage Message object to add to the database
     * @return the added Message with new id value
     */
    Message addMessage(Message newMessage);

    /**
     * Deletes a message from the database.
     *
     * @param id the id of the message to delete
     */
    void deleteMessage(int id);
}

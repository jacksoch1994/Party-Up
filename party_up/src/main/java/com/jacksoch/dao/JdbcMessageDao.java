package com.jacksoch.dao;

import com.jacksoch.model.Message;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcMessageDao implements MessageDao {

    /*
    ########################################   Attributes   ##########################################
     */

    private final JdbcTemplate jdbcTemplate;

    /*
    ########################################   Constructor   ##########################################
     */

    public JdbcMessageDao(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    /*
    #########################################  DAO Methods  ###########################################
     */

    @Override
    public Message getMessage(int messageId) {
        String sql = "SELECT * FROM user_message WHERE message_id = ?;";
        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, messageId);

        Message message = null;
        if (row.next()) {
            message = mapRowToMessage(row);
        }
        return message;
    }

    @Override
    public List<Message> getMessagesBySender(int senderId) {
        String sql = "SELECT * FROM user_message WHERE sender_id = ?;";
        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, senderId);

        List<Message> messages = new ArrayList<>();
        while (rows.next()) {
            messages.add(mapRowToMessage(rows));
        }
        return messages;
    }

    @Override
    public List<Message> getMessagesByReceiver(int receiverId) {
        String sql = "SELECT * FROM user_message WHERE receiver_id = ?;";
        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, receiverId);

        List<Message> messages = new ArrayList<>();
        while (rows.next()) {
            messages.add(mapRowToMessage(rows));
        }
        return messages;
    }

    @Override
    public List<Message> getMessagesBySenderReceiver(int senderId, int receiverId) {
        String sql = "SELECT * FROM user_message WHERE receiver_id = ? AND sender_id = ?;";
        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, receiverId, senderId);

        List<Message> messages = new ArrayList<>();
        while (rows.next()) {
            messages.add(mapRowToMessage(rows));
        }
        return messages;
    }

    @Override
    public Message updateMessage(Message updatedMessage, int id) {
        String sql = "UPDATE user_message SET message_content = ? WHERE message_id = ? RETURNING message_id;";
        Integer updatedId = jdbcTemplate.queryForObject(sql, Integer.class, updatedMessage.getContent(), id);

        //Handle null case
        if (updatedId == null) updatedId = -1;
        return getMessage(updatedId);
    }

    @Override
    public Message addMessage(Message newMessage) {
        String sql = "INSERT INTO user_message(sender_id, receiver_id, message_content) VALUES (?,?,?) RETURNING message_id;";
        Integer id = jdbcTemplate.queryForObject(sql, Integer.class, newMessage.getSenderId(),
                newMessage.getReceiverId(), newMessage.getContent());

        //Handle null case
        if (id == null) id = -1;
        return getMessage(id);
    }

    @Override
    public void deleteMessage(int id) {
        String sql = "DELETE FROM user_message WHERE message_id = ?;";
        jdbcTemplate.update(sql);
    }

     /*
    ########################################  Helper Methods  ##########################################
     */

    private Message mapRowToMessage(SqlRowSet rows) {
        int messageId = rows.getInt("message_id");
        int senderId = rows.getInt("sender_id");
        int receiverId = rows.getInt("receiver_id");
        String message = rows.getString("message_content");

        LocalDateTime sendDate = null;
        if (rows.getTimestamp("send_date") != null) {
            sendDate = rows.getTimestamp("send_date").toLocalDateTime();
        }

        return new Message(messageId, senderId, receiverId, message, sendDate);
    }

}

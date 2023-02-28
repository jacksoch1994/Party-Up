package com.jacksoch.dao;

import com.jacksoch.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserDao implements  UserDao{
    /*
    ########################################   Attributes   ##########################################
     */

    private final JdbcTemplate jdbcTemplate;

    /*
    ########################################   Constructor   ##########################################
     */

    public JdbcUserDao(DataSource dataSource) {this.jdbcTemplate = new JdbcTemplate(dataSource);};

    /*
    #########################################  DAO Methods  ###########################################
     */

    @Override
    public User getUser(int userId) {
        String sql = "SELECT * FROM user_account WHERE user_id = ?";
        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, userId);

        User user = null;
        if (rows.next()) {
            user = mapRowToUser(rows);
        }
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM user_account WHERE username = ?";
        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, username);

        User user = null;
        if (rows.next()) {
            user = mapRowToUser(rows);
        }
        return user;
    }

    @Override
    public List<User> getUsersByGroup(int groupId) {
        String sql = "SELECT * FROM user_account AS ua JOIN user_group AS " +
                "ug ON ua.user_id = ug.player_id WHERE group_id = ?;";

        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, groupId);
        List<User> users = new ArrayList<>();

        while (rows.next()) {
            users.add(mapRowToUser(rows));
        }
        return users;
    }

    @Override
    public User createUser(User user) {
        String sql = "INSERT INTO user_account(username, email, phone, pfp_url) VALUES (?, ?, ?, ?) RETURNING user_id;";
        Integer id = jdbcTemplate.queryForObject(sql, Integer.class, user.getUsername(), user.getEmail(),
                user.getPhone(), user.getPfpURL());

        //Handle potential NullPointerException
        if (id == null) id = -1;
        return getUser(id);
    }

    @Override
    public User updateUser(User user) {
        String sql = "UPDATE user_account SET username=?, email=?, phone=?, pfp_url=? WHERE user_id=?;";
        Integer id = jdbcTemplate.queryForObject(sql, Integer.class, user.getUsername(), user.getEmail(),
                user.getPhone(), user.getPfpURL());

        //Handle potential NullPointerException
        if (id == null) id = -1;
        return getUser(id);
    }

    /*
    ########################################  Helper Methods  ##########################################
     */

    private User mapRowToUser(SqlRowSet rows) {
        int userId = rows.getInt("user_id");
        String username = rows.getString("username");
        String email = rows.getString("email");
        String phone = rows.getString("phone");
        String pfpURL = rows.getString("pfp_url");

        return new User(userId, username, email, phone, pfpURL);
    }
}
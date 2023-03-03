package com.jacksoch.dao;

import com.jacksoch.model.JoinRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JdbcJoinRequestDao implements JoinRequestDao{

    /*
    ########################################   Attributes   ##########################################
     */

    private final JdbcTemplate jdbcTemplate;

    /*
    ########################################   Constructor   ##########################################
     */

    public JdbcJoinRequestDao(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    /*
    #########################################  DAO Methods  ###########################################
     */

    @Override
    public JoinRequest getJoinRequest(int playerId, int groupId) {
        String sql = "SELECT player_id, group_id FROM join_request WHERE player_id = ? AND group_id = ?";
        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, playerId, groupId);

        JoinRequest request = null;
        if (rows.next()) {
            request = mapToJoinRequest(rows);
        }
        return request;
    }

    @Override
    public void createJoinRequest(JoinRequest newRequest) {
        String sql = "INSERT INTO join_request(playerId, groupId) VALUES (?,?);";
        jdbcTemplate.update(sql, newRequest.getPlayer_id(), newRequest.getGroup_id());
    }

    @Override
    public void deleteJoinRequest(JoinRequest newRequest) {
        String sql = "DELETE FROM join_request WHERE player_id = ? AND group_id = ?;";
        jdbcTemplate.update(sql, newRequest.getPlayer_id(), newRequest.getGroup_id());
    }

    /*
    ########################################  Helper Methods  ##########################################
     */

    //Helper method for mapping the current SqlRowSet row to a JoinRequest object
    private JoinRequest mapToJoinRequest(SqlRowSet rows) {
        int playerId = rows.getInt("player_id");
        int groupId = rows.getInt("group_id");
        return new JoinRequest(playerId, groupId);
    }
}

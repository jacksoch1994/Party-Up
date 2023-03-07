package com.jacksoch.dao;

import com.jacksoch.model.Group;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcGroupDao implements GroupDao{
    /*
    ########################################   Attributes   ##########################################
     */

    private final JdbcTemplate jdbcTemplate;

    /*
    ########################################   Constructor   ##########################################
     */

    public JdbcGroupDao(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    /*
    #########################################  DAO Methods  ###########################################
     */

    @Override
    public List<Group> getAllGroups() {
        String sql = "SELECT * FROM play_group;";
        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);

        List<Group> groups = new ArrayList<>();
        while (rows.next()) {
            groups.add(mapRowToGroup(rows));
        }
        return groups;
    }

    @Override
    public Group getGroup(int groupId) {
        String sql = "SELECT * FROM play_group WHERE group_id = ?;";
        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, groupId);

        Group group = null;
        if (row.next()) {
            group = mapRowToGroup(row);
        }
        return group;
    }

    @Override
    public List<Group> getGroupsByOwner(int ownerId) {
        String sql = "SELECT * FROM play_group WHERE owner_id = ?;";
        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, ownerId);

        List<Group> groups = new ArrayList<>();
        while (rows.next()) {
            groups.add(mapRowToGroup(rows));
        }
        return groups;
    }

    @Override
    public List<Group> getGroupsByPlayer(int playerId) {
        String sql = "SELECT * FROM play_group WHERE group_id IN (SELECT group_id FROM user_group WHERE player_id = ?);";
        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, playerId);

        List<Group> groups = new ArrayList<>();
        while (rows.next()) {
            groups.add(mapRowToGroup(rows));
        }
        return groups;
    }

    @Override
    public List<Group> getGroupsByOwnerAndPlayer(int ownerId, int playerId) {
        String sql = "SELECT * FROM play_group WHERE owner_id = ? AND group_id IN (SELECT group_id FROM user_group "
                + "WHERE player_id = ?);";
        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, ownerId, playerId);

        List<Group> groups = new ArrayList<>();
        while (rows.next()) {
            groups.add(mapRowToGroup(rows));
        }
        return groups;
    }

    @Override
    public Group createGroup(Group group) {
        String sql = "INSERT INTO play_group(owner_id, title, game, max_player_count, accepting_new_players, "
                + "description, game_location) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING group_id;";
        Integer id = jdbcTemplate.queryForObject(sql, Integer.class, group.getOwnerId(), group.getTitle(),
                group.getGame(), group.getMaxPlayerCount(), group.isAcceptingNewPlayers(),
                group.getDescription(), group.getLocation());

        //Handle potential NullPointerException
        if (id == null) id = -1;
        return getGroup(id);
    }

    @Override
    public Group updateGroup(Group group, int id) {
        String sql = "UPDATE play_group SET owner_id=?, title=?, game=?, max_player_count=?, "
        + "accepting_new_players=?, description=?, game_location=? WHERE group_id=? RETURNING group_id;";
        Integer returnId = jdbcTemplate.queryForObject(sql, Integer.class, group.getOwnerId(), group.getTitle(),
                group.getGame(), group.getMaxPlayerCount(), group.isAcceptingNewPlayers(),
                group.getDescription(), group.getLocation(), id);

        //Handle potential NullPointerException
        if (returnId == null) returnId = -1;
        return getGroup(returnId);
    }

    @Override
    public int playerCount(int groupId) {
        String sql = "SELECT COUNT(*) FROM user_group WHERE group_id = ?;";
        Integer playerCount = jdbcTemplate.queryForObject(sql, Integer.class, groupId);

        if (playerCount == null) {
            return 0;
        } else {
            return playerCount;
        }
    }

    @Override
    public boolean addPlayer(int groupId, int playerId) {
        String sql = "INSERT INTO user_group(player_id, group_id) VALUES(?,?);";

        if (getGroup(groupId) == null || playerCount(groupId) >= getGroup(groupId).getMaxPlayerCount()) {
            return false;
        }

        jdbcTemplate.update(sql, playerId, groupId);
        return true;
    }

    @Override
    public void deleteGroup(int id) {
        String sql = "DELETE FROM play_group WHERE group_id = ?;";
        jdbcTemplate.update(sql, id);
    }

    /*
    ########################################  Helper Methods  ##########################################
     */

    private Group mapRowToGroup(SqlRowSet row) {
        int groupId = row.getInt("group_id");
        int ownerId = row.getInt("owner_id");
        String title = row.getString("title");
        String game = row.getString("game");
        int maxPlayerCount = row.getInt("max_player_count");
        boolean isAcceptingNewPlayers = row.getBoolean("accepting_new_players");
        String description = row.getString("description");
        String gameLocation = row.getString("game_location");

        return new Group(groupId, ownerId, title, game, maxPlayerCount, isAcceptingNewPlayers, description, gameLocation);
    }
}

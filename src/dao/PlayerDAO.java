package dao;

import entity.Player;
import until.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {
    public static List<Player> findAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Player> listPlayer = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_all_player()}");
            ResultSet rs = callSt.executeQuery();
            listPlayer = new ArrayList<Player>();
            while (rs.next()) {
                Player player = new Player();
                player.setPlayerId(rs.getInt("playerId"));
                player.setPlayerName(rs.getString("fullName"));
                player.setEmail(rs.getString("email"));
                player.setPhoneNumber(rs.getString("phoneNumber"));
                player.setRegisterDate(LocalDate.parse(rs.getString("registerDate"),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                player.setStatus(rs.getBoolean("status"));
                listPlayer.add(player);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }

        return listPlayer;
    }

    public static boolean createPlayer(Player player) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call create_player(?, ?, ?, ?)}");
            callSt.setString(1, player.getPlayerName());
            callSt.setString(2, player.getEmail());
            callSt.setString(3, player.getPhoneNumber());
            callSt.setDate(4, Date.valueOf(player.getRegisterDate()));
            callSt.executeUpdate();
            result = true;
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }
    public static boolean updatePlayer(Player player) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call update_player(?, ?, ?, ?,?,?)}");
            callSt.setInt(1, player.getPlayerId());
            callSt.setString(2, player.getPlayerName());
            callSt.setString(3, player.getEmail());
            callSt.setString(4, player.getPhoneNumber());
            callSt.setDate(5, Date.valueOf(player.getRegisterDate()));
            callSt.setDate(4, Date.valueOf(player.getRegisterDate()));
            callSt.executeUpdate();
            result = true;
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return true;
    }

    public static Player findPlayerById(int playerId) {
        Connection conn = null;
        CallableStatement callSt = null;
        Player player = null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_player_by_id(?)}");
            callSt.setInt(1, playerId);
            ResultSet rs = callSt.executeQuery();
            player = new Player();
            if (rs.next()) {
                player.setPlayerId(rs.getInt("playerId"));
                player.setPlayerName(rs.getString("fullName"));
                player.setEmail(rs.getString("email"));
                player.setPhoneNumber(rs.getString("phoneNumber"));
                player.setRegisterDate(LocalDate.parse(rs.getString("registerDate"),DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                player.setStatus(rs.getBoolean("status"));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return player;
    }

    public static boolean deletePlayerById(int playerId) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call delete_player(?)}");
        }catch (Exception e) {
            e.printStackTrace();

        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return true;
    }
    public static List<Player> searchPlayerByName(String playerName) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Player> listPlayer = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_player_by_name(?)}");
            callSt.setString(1, playerName);
            ResultSet rs = callSt.executeQuery();
            listPlayer = new ArrayList<>();
            while (rs.next()) {
                Player player = new Player();
                player.setPlayerId(rs.getInt("playerId"));
                player.setPlayerName(rs.getString("fullName"));
                player.setEmail(rs.getString("email"));
                player.setPhoneNumber(rs.getString("phoneNumber"));
                player.setRegisterDate(LocalDate.parse(rs.getString("registerDate"),DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                player.setStatus(rs.getBoolean("status"));
                listPlayer.add(player);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }return listPlayer;
    }
}

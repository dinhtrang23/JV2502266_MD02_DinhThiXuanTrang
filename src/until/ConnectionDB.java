package until;

import java.sql.*;


public class ConnectionDB {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/player_management_db";
    private static final String USER = "root";
    private static final String PASS = "trang!";

    public static Connection openConnection() {
        Connection conn = null;
        try {
           Class.forName(DRIVER);
           conn = DriverManager.getConnection(URL, USER, PASS);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection(Connection conn, CallableStatement CallSt) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (CallSt != null) {
            try {
                CallSt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

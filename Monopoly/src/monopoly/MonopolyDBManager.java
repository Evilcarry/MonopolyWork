package monopoly;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Project ID: 10 - Monopoly
 * @author Benjamin Andres Fuentes Cavieres - 20104709
 * @author Sean Simpkins - 20105546
 */
public class MonopolyDBManager {
    // maybe the users can have different games so if they played a 2 player game they can save it to their own save.
    //Have a look at the H01_DBanager.java file for more info.
    //Have a look at H_RetrieveAll.

    //private static String userName;
    //private static String password;
    private static final String URL = "jdbc:derby:Monopoly_GameSave; create=true";

    Connection conn;

    public MonopolyDBManager() {
        establishConnectToDB();
    }

    /**
     * Used to establish a connection to the database Monopoly_GameSave.
     */
    public void establishConnectToDB() {
        if (conn == null) {
            try {
                this.conn = DriverManager.getConnection(URL);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void closeConnectToDB() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public ResultSet queryDB(String sqlQuery) {
        Connection connection = this.conn;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultSet;
    }

    public void updateDB(String sqlQuery) {

        Connection connection = this.conn;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sqlQuery);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println(ex.getNextException());
        }
    }

    public Connection getConn() {
        return conn;
    }
}

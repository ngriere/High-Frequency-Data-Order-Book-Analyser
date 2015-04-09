/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Nicolas_2
 */
public class DBConnectionImpl implements DBConnection {
    
    // CLASS FIELDS
    private Statement stmt;
    private ResultSet res;
    private PreparedStatement PreparedStmt;
    private Connection conn;
    
    /**
     * Creates a new Connection object. This creates a new connection to
     * the specified database.
     *
     * @param url the url of the database to connect to
     * @param user the login name of the user
     * @param password his password
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public DBConnectionImpl(String url, String user, String password) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(url, user, password);
        stmt = conn.createStatement();
        System.out.println("Connexion à la base de données: OK");
    }

    @Override
    public void createDB() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

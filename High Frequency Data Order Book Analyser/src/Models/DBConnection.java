/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Models;

import java.sql.SQLException;

/**
 * The interface of a DBConnection.
 * @author Nicolas_2
 */
public interface DBConnection {
    
    /**
     * 
     * @throws java.sql.SQLException
     */
    void createDB() throws SQLException;
}

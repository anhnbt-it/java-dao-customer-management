/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anhnbt
 */
public class DBConnection {
    
    public static Connection getConnection() {
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=quanlysanpham;user=sa;password=KhoaiTay@2019";
        Connection conn = null;
        try {
            System.out.print("Connecting to SQL Server ... ");
            conn = DriverManager.getConnection(connectionUrl);
            System.out.println("Done.");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    
}

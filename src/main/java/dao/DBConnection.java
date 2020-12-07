/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anhnbt
 */
public class DBConnection {
    
//    private static final String JDBC_URL = "jdbc:sqlserver://localhost:1433/quanlysanpham";
//    private static final String USER = "sa";
//    private static final String PASSWORD = "KhoaiTay@2019";
        
    public static Connection getConnection() {
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=quanlysanpham;user=sa;password=KhoaiTay@2019";
        Connection conn = null;
        try {
            System.out.print("Connecting to SQL Server ... ");
//            conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            conn = DriverManager.getConnection(connectionUrl);
            System.out.println("Done.");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;

/**
 *
 * @author anhnbt
 */
public class CustomerDao implements IDao<Customer> {
    private Connection conn;

    public CustomerDao() {
    }

    public CustomerDao(Connection conn) {
        this.conn = conn;
    }
    
    

    @Override
    public boolean add(Customer obj) {
        String sql = "INSERT INTO customers (name, username, password, phone, address"
                + ", gender, status, roles, created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        int rowsAffected = 0;
        try (
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, obj.getName());
            pstmt.setString(2, obj.getUsername());
            pstmt.setString(3, obj.getPassword());
            pstmt.setString(4, obj.getPhone());
            pstmt.setString(5, obj.getAddress());
            pstmt.setByte(6, obj.getGender());
            pstmt.setByte(7, obj.getStatus());
            pstmt.setByte(8, obj.getRoles());
            
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            pstmt.setTimestamp(9, timestamp);
            
            rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted");
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowsAffected > 0;
    }

    @Override
    public boolean remove(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean edit(Customer obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Customer> getRecords(int currentPage, int recordsPerPage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Customer> findByName(String query, int currentPage, int recordsPerPage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Customer findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;

/**
 *
 * @author anhnbt
 */
public class CartDao {
    private final Hashtable<Integer, Product> hashtable;
    private Connection conn;

    public CartDao(Connection conn) {
        hashtable = new Hashtable<>();
        this.conn = conn;
    }
    
    public Hashtable<Integer, Product> listProducts() {
        return hashtable;
    }
    
    public void addItem(Product obj) {
        int key = obj.getId();
        if (hashtable.containsKey(key)) {
            // Neu ton tai san pham thi cap nhat lai so luong
            Product product = hashtable.get(key);
            product.setQty(product.getQty() + obj.getQty());
            hashtable.put(key, obj);
            System.out.println("Updated quantity success.");
        } else {
            // Them san pham vao gio hang
            hashtable.put(key, obj);
            System.out.println("Added to Cart.");
        }
    }
    
    public void removeItem(Product obj) {
        
    }
    
    public void removeItemById(int id) {
    }
    
    public void checkOut(Product product, int oldQty) {
        // Them hoa don > them hoa don chi tiet
        
        try {
            conn.setAutoCommit(false);

            String sql = "INSERT INTO tbl_orders (customer_id, name, address, phone) "
                + "VALUES (?, ?, ?, ?);";
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, 1);
            pstmt.setString(2, "TuanAnh");
            pstmt.setString(3, "Hanoi");
            pstmt.setString(4, "123");
            pstmt.executeUpdate();
            
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                // Them hoa don chi tiet
                int insertedId = rs.getInt(1);
                System.out.println("InsertedId: " + insertedId);
                sql = "INSERT INTO tbl_orderdetails (order_id, product_id, quantity) VALUES (?, ?, ?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, insertedId);
                pstmt.setInt(2, product.getId());
                pstmt.setInt(3, product.getQty());
                pstmt.executeUpdate();
                
                sql = "UPDATE tbl_products SET qty = ? WHERE id = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, oldQty - product.getQty());
                pstmt.setInt(2, product.getId());
                pstmt.executeUpdate();
                
                conn.commit();
                System.out.println("Thank you for your purchase!");
                System.out.println("Your order # is: " + insertedId);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

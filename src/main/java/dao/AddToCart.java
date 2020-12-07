/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;

/**
 *
 * @author anhnbt
 */
public class AddToCart {
    private Hashtable<Integer, Product> hashtable;
    Connection conn;

    public AddToCart(Connection conn) {
        hashtable = new Hashtable<>();
        this.conn = conn;
    }
    
    public void getListProducts() {
        Enumeration<Integer> enu = hashtable.keys();
        while (enu.hasMoreElements()) {
            int key = enu.nextElement();
            Product product = hashtable.get(key);
            System.out.println(product.toString());
        }
    }
    
    public void addProduct(Product obj) {
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
            System.out.println("Add to cart success.");
        }
    }
    
    public void checkOut() {
        getListProducts();
        // Them hoa don
        String sql = "INSERT INTO orders (customer_id, name, address, phone) "
                + "VALUES (?, ?, ?, ?);";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, 0);
        } catch (SQLException ex) {
            Logger.getLogger(AddToCart.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

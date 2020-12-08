/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    
    public void checkOut(int productId, int qty) {
        getListProducts();
        // Them hoa don > them hoa don chi tiet
        String sql = "INSERT INTO tbl_orders (customer_id, name, address, phone) "
                + "VALUES (?, ?, ?, ?);";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, 1);
            pstmt.setString(2, "TuanAnh");
            pstmt.setString(3, "Hanoi");
            pstmt.setString(4, "123");
            pstmt.executeUpdate();
            if (pstmt.executeUpdate() > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    // Them hoa don chi tiet
                    int insertedId = rs.getInt(1);
                    sql = "INSERT INTO tbl_orderdetails (order_id, product_id, quantity) VALUES (?, ?, ?)";
                    pstmt.setInt(1, insertedId);
                    pstmt.setInt(2, productId);
                    pstmt.setInt(3, qty);
                    if (pstmt.executeUpdate() > 0) {
                        System.out.println("Checkout thanh cong!");
                    } else {
                        System.out.println("Check out that bai!");
                    }
                }
//            } else {
//                System.out.println("Check out that bai!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddToCart.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

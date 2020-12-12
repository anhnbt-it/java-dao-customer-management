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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;

/**
 *
 * @author anhnbt
 */
public class ProductDao implements IDao<Product> {
    private final Connection conn;

    public ProductDao(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public void save(Product obj) {
        String sql = "INSERT INTO tbl_products (name, productline_id, qty, price"
                + ", description, status) VALUES (?, ?, ?, ?, ?, ?);";
        int rowsAffected = 0;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, obj.getName());
            pstmt.setInt(2, obj.getProductlineId());
            pstmt.setInt(3, obj.getQty());
            pstmt.setDouble(4, obj.getPrice());
            pstmt.setString(5, obj.getDesc());
            pstmt.setShort(6, obj.getStatus());
            
            rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted");
            
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Product obj) {
        String sql = "UPDATE tbl_products SET name = ?, productline_id = ?, qty = ?"
                + ", price = ?, description = ?, status = ? WHERE id = ?";
        int rowsAffected = 0;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, obj.getName());
            pstmt.setInt(2, obj.getProductlineId());
            pstmt.setInt(3, obj.getQty());
            pstmt.setDouble(4, obj.getPrice());
            pstmt.setString(5, obj.getDesc());
            pstmt.setShort(6, obj.getStatus());
            pstmt.setInt(7, obj.getId());
            
            rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) updated");
            
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Product obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM tbl_products WHERE id = ?";
        int rowsAffected = 0;
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) deleted");
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tbl_products";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setProductlineId(rs.getInt("productline_id"));
                product.setName(rs.getString("name"));
                product.setQty(rs.getInt("qty"));
                product.setPrice(rs.getDouble("price"));
                product.setDesc(rs.getString("description"));
                product.setStatus(rs.getShort("status"));
                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    @Override
    public List<Product> findByName(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product findById(int id) {
        String sql = "SELECT * FROM tbl_products WHERE id = ?";
        Product product = new Product();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                product.setId(rs.getInt("id"));
                product.setProductlineId(rs.getInt("productline_id"));
                product.setName(rs.getString("name"));
                product.setQty(rs.getInt("qty"));
                product.setPrice(rs.getDouble("price"));
                product.setDesc(rs.getString("description"));
                product.setStatus(rs.getShort("status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return product;
    }

    @Override
    public boolean existById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

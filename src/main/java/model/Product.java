/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author anhnbt
 */
public class Product {
    private int id;
    private int productlineId;
    private String name;
    private int qty;
    private double price;
    private String desc;
    private short status;

    public Product() {
    }
    
    public Product(int productlineId, String name, int qty, double price, String desc, short status) {
        this.productlineId = productlineId;
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.desc = desc;
        this.status = status;
    }

    public Product(int id, int productlineId, String name, int qty, double price, String desc, short status) {
        this.id = id;
        this.productlineId = productlineId;
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.desc = desc;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductlineId() {
        return productlineId;
    }

    public void setProductlineId(int productlineId) {
        this.productlineId = productlineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + getId() + ", productlineId=" + getProductlineId() + ", name=" + getName() + ", qty=" + getQty() + ", price=" + getPrice() + ", desc=" + getDesc() + ", status=" + getStatus() + '}';
    }
    
}

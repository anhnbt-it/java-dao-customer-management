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
public class Customer extends Person {
    private int id;
    private String username;
    private String password;
    private byte status;
    private byte roles;

    public Customer() {
    }

    public Customer(String username, String password, byte status, byte roles) {
        this.username = username;
        this.password = password;
        this.status = status;
        this.roles = roles;
    }

    public Customer(int id, String username, String password, byte status, byte roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = status;
        this.roles = roles;
    }

    public Customer(String username, String password, byte status, byte roles, String name, String phone, String address, byte gender) {
        super(name, phone, address, gender);
        this.username = username;
        this.password = password;
        this.status = status;
        this.roles = roles;
    }

    public Customer(int id, String username, String password, byte status, byte roles, String name, String phone, String address, byte gender) {
        super(name, phone, address, gender);
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = status;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public byte getRoles() {
        return roles;
    }

    public void setRoles(byte roles) {
        this.roles = roles;
    }
    
    
}

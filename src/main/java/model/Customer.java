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

    public Customer(int id, String username, String password, byte status, byte roles, String name, String phone, String address, byte gender) {
        super(name, phone, address, gender);
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = status;
        this.roles = roles;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the status
     */
    public byte getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(byte status) {
        this.status = status;
    }

    /**
     * @return the roles
     */
    public byte getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(byte roles) {
        this.roles = roles;
    }
    
}

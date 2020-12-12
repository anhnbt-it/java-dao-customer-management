/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author anhnbt
 * @param <T>
 */
public interface IDao<T>  {
    void save(T obj);
    
    void update(T obj);
    
    void delete(T obj);
    
    void deleteById(int id);
    
    List<T> findAll();
    
    List<T> findByName(String query);
    
    T findById(int id);
    
    boolean existById(int id);
}

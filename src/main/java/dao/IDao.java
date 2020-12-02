/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author anhnbt
 * @param <E>
 */
public interface IDao<E> {
    boolean add(E obj);
    boolean remove(int id);
    boolean edit(E obj);
    List<E> getRecords(int currentPage, int recordsPerPage);
    List<E> findByName(String query, int currentPage, int recordsPerPage);
    E findById(int id);
}

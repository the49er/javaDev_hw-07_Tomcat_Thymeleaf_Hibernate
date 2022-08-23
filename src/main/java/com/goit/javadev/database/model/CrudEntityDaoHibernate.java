package com.goit.javadev.database.model;

import java.util.List;

public interface CrudEntityDaoHibernate<T> {
    void insertNewEntity(T element);                     //create
    void updateEntityFieldsById(T element, long id);  //update
    T getEntityById(long id);                            //read
    List<T> getAllEntities();                            //read
    void deleteById(long id);                         //delete
    void clearTable();                                //delete
    long getMaxId();                                     //read
}

package com.goit.javadev.database.model;

import java.util.List;

public interface CrudEntityHibernateDAO<T> {
    void insertNewEntity(T element);                     //create
    void insertNewEntities(List<T> element);              //create
    void insertEntitiesFromJsonFile (String jsonFilePath);//create
    void updateEntityFieldsById(T element, long id);  //update
    T getEntityById(long id);                            //read
    List<T> getAllEntities();                            //read
    void deleteEntitiesFromListById(long[] ids);          //delete
    void deleteById(long id);                         //delete
    void clearTable();                                //delete
    long getMaxId();                                     //read
}

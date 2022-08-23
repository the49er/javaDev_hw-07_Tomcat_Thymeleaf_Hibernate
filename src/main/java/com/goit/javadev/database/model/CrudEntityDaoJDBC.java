package com.goit.javadev.database.model;

import java.util.List;

public interface CrudEntityDaoJDBC<T> {
    long insertNewEntity(T element);                     //create
    int insertNewEntities(List<T> element);              //create
    int insertEntitiesFromJsonFile (String jsonFilePath);//create
    boolean updateEntityFieldsById(T element, long id);  //update
    T getEntityById(long id);        //read
    List<T> getAllEntities();                            //read
    int deleteEntitiesFromListById(long[] ids);          //delete
    boolean deleteById(long id);                         //delete
    boolean clearTable();                                //delete
    long getMaxId();                                     //read
}

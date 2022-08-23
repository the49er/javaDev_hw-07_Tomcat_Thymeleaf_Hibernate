package com.goit.javadev.database.model.entity_relation;

import java.util.List;
import java.util.Optional;

public interface ManyToMany<T> {
    boolean insertKey(T element);
    boolean deleteKey(long key1, long key2);
    Optional<List<T>> getAll();
    int createKeysFromList(List<T> list);
    int insertKeysFromJsonFile(String jsonFilePath);
}

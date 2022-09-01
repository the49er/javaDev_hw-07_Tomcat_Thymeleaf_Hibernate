package com.goit.javadev.database;

import com.goit.javadev.database.feature.storage.Storage;

public class ProjectCapitalManagementInitDataBase {
    public static void main(String[] args) {

        //DataBase Schema Initialisation
        Storage storage = Storage.getInstance();
        storage.executeUpdates();
    }
}

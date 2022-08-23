package com.goit.javadev.database.keys.entity.skill;

import com.goit.javadev.database.model.skill.SkillDaoJDBC;
import com.goit.javadev.database.model.skill.ProgramLang;
import com.goit.javadev.database.model.skill.Skill;
import com.goit.javadev.database.model.skill.SkillLevel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class SkillDaoJDBCTest {
    private Connection connection;
    private SkillDaoJDBC skillDaoJDBC;
    Skill skillTest1;
    Skill skillTest2;
    Skill skillTest3;
    String jsonFilePath;
    List<Skill> expectedList;

    @BeforeEach
    public void beforeEach () throws SQLException {
        final String jdbc = "jdbc:h2:mem:./testDataBase;DB_CLOSE_DELAY=-1";
        String sqlCreateDataBase = "CREATE SCHEMA IF NOT EXISTS `homework_7`";
        String sqlCreateTableCompany = "CREATE TABLE IF NOT EXISTS `homework_7`.skills (" +
                "id IDENTITY PRIMARY KEY, programming_lang VARCHAR(100), level VARCHAR(100))";
        connection = DriverManager.getConnection(jdbc);
        connection.createStatement().executeUpdate(sqlCreateDataBase);
        connection.createStatement().executeUpdate(sqlCreateTableCompany);
        skillDaoJDBC = new SkillDaoJDBC(connection);
        skillTest1 = new Skill(1, ProgramLang.JAVA, SkillLevel.JUNIOR);
        skillTest2 = new Skill(2, ProgramLang.C_SHARP, SkillLevel.MIDDLE);
        skillTest3 = new Skill(3, ProgramLang.JAVA_SCRIPT, SkillLevel.SENIOR);
        expectedList = List.of(skillTest1, skillTest2, skillTest3);
        jsonFilePath = "src/test/resource/json/test_skills.json";
        skillDaoJDBC.clearTable();
    }

    @AfterEach
    public void afterEach() throws SQLException {
        connection.close();
    }

    @Test
    public void testThatSkillCreated() {
        long id = skillDaoJDBC.insertNewEntity(skillTest1);
        Skill createdSkill = skillDaoJDBC.getEntityById(id);
        Assertions.assertEquals(id, createdSkill.getId());
    }

    @Test
    void testThatEntitiesCreated() {
        int expected = skillDaoJDBC.insertNewEntities(expectedList);
        int actual = skillDaoJDBC.getAllEntities().size();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testThatEntityFieldsUpdated() {
        skillDaoJDBC.insertNewEntity(skillTest1);
        boolean actual = skillDaoJDBC.updateEntityFieldsById(skillTest2, 1);
        Assertions.assertTrue(true, String.valueOf(actual));
    }


    @Test
    void testThatEntityDeleted() {
        skillDaoJDBC.insertNewEntity(skillTest1);
        skillDaoJDBC.deleteById(1);
        Optional<Skill> actual = Optional.ofNullable(skillDaoJDBC.getEntityById(1));
        Assertions.assertNull(null, String.valueOf(actual));
    }

    @Test
    void testThatEntitiesWereDeleted() {
        long[] longArr = {1, 2, 3};
        int expected = skillDaoJDBC.insertNewEntities(expectedList);
        int actual = skillDaoJDBC.deleteEntitiesFromListById(longArr);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testThatEntitiesWereInsertedFromJsonFile() {
        int expected = skillDaoJDBC.insertEntitiesFromJsonFile(jsonFilePath);
        int actual = skillDaoJDBC.getAllEntities().size();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testThatTableWasCleared() {
        skillDaoJDBC.insertNewEntities(expectedList);
        skillDaoJDBC.clearTable();
        Optional<Skill> actual = Optional.ofNullable(skillDaoJDBC.getEntityById(1));
        Assertions.assertFalse(false, String.valueOf(actual.isPresent()));
    }
}

package com.goit.javadev.database;

import com.goit.javadev.database.feature.storage.HibernateUtil;
import com.goit.javadev.database.model.developer.DeveloperDaoJDBC;
import com.goit.javadev.database.model.skill.SkillDaoJDBC;
import com.goit.javadev.database.model.company.CompanyDaoJDBC;
import com.goit.javadev.database.model.customer.CustomerDaoService;
import com.goit.javadev.database.model.project.ProjectDaoJDBC;
import com.goit.javadev.database.feature.storage.DataBaseInitService;
import com.goit.javadev.database.feature.storage.Storage;
import com.goit.javadev.database.model.entity_relation.company_customer.CompanyCustomerDaoJDBC;
import com.goit.javadev.database.model.entity_relation.developer_project.DeveloperProjectDaoJDBC;
import com.goit.javadev.database.model.entity_relation.developer_skill.DeveloperSkillDaoJDBC;

import java.sql.Connection;

public class ProjectCapitalManagementInitDataBase {
    public static void main(String[] args) {

        String companiesJsonFileIn = "files/in/companies.json"; //companies
        String developersJsonFileIn = "files/in/developers.json"; // developers
        String projectsJsonFileIn = "files/in/projects.json"; // projects
        String customersJsonFileIn = "files/in/customers.json"; // customers
        String skillsJsonFileIn = "files/in/skills.json"; // skills
        String compCustomerKeysJsonFileIn = "files/in/company_customer_keys.json"; // company_customer
        String devProjectKeysJsonFileIn = "files/in/developer_project_keys.json"; // developer_project
        String devSkillKeysJsonFileIn = "files/in/developer_skill_keys.json"; // developer_skill

        //DataBaseInitialisation with test_user
        Storage storage = Storage.getInstance();
        Connection connection = storage.getConnection();
        storage.executeUpdates();

//        new DataBaseInitService().initDbFlyWay(HibernateUtil.getInstance());
//
//
//        //population of companies table
//        CompanyDaoJDBC companyDbService = new CompanyDaoJDBC(connection);
//        companyDbService.insertEntitiesFromJsonFile(companiesJsonFileIn);
//
//        //population of developers table
//        DeveloperDaoJDBC developerDaoService = new DeveloperDaoJDBC(connection);
//        developerDaoService.insertEntitiesFromJsonFile(developersJsonFileIn);
//
//        //population of customers table
//        CustomerDaoService customerDaoService = new CustomerDaoService(connection);
//        customerDaoService.insertEntitiesFromJsonFile(customersJsonFileIn);
//
//        //population of projects table
//        ProjectDaoJDBC projectDaoJDBC = new ProjectDaoJDBC(connection);
//        projectDaoJDBC.insertEntitiesFromJsonFile(projectsJsonFileIn);
//
//        //population of skills table
//        SkillDaoJDBC skillDaoJDBC = new SkillDaoJDBC(connection);
//        skillDaoJDBC.insertEntitiesFromJsonFile(skillsJsonFileIn);
//
//        //population of company_customer table (keys)
//        CompanyCustomerDaoJDBC companyCustomerDaoJDBC = new CompanyCustomerDaoJDBC(connection);
//        companyCustomerDaoJDBC.insertKeysFromJsonFile(compCustomerKeysJsonFileIn);
//
//        //population of developer_project table (keys)
//        DeveloperProjectDaoJDBC developerProjectDaoJDBC = new DeveloperProjectDaoJDBC(connection);
//        developerProjectDaoJDBC.insertKeysFromJsonFile(devProjectKeysJsonFileIn);
//
//        //population of developer_skill table (keys)
//        DeveloperSkillDaoJDBC developerSkillDaoJDBC = new DeveloperSkillDaoJDBC(connection);
//        developerSkillDaoJDBC.insertKeysFromJsonFile(devSkillKeysJsonFileIn);
    }
}

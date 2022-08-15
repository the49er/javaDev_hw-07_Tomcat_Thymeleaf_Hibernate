package com.goit.javadev.database.model.developer;

import com.goit.javadev.database.feature.storage.HibernateUtil;
import com.goit.javadev.database.model.CrudEntityHibernateDAO;
import com.google.gson.Gson;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DeveloperDaoHibernate implements CrudEntityHibernateDAO<Developer> {
    private final HibernateUtil util = HibernateUtil.getInstance();



    @Override
    public void insertNewEntity(Developer element) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(element);
        transaction.commit();
        session.close();
    }

    @Override
    public void insertNewEntities(List<Developer> element) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        for (Developer dev: element) {
            session.persist(dev);
        }
        transaction.commit();
        session.close();
    }

    @Override
    public void insertEntitiesFromJsonFile(String jsonFilePath) {
        Gson gson = new Gson();
        String inString = null;
        try {
            inString = String.join(
                    "\n",
                    Files.readAllLines(Paths.get(jsonFilePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        com.goit.javadev.database.model.developer.Developer[] developers = gson.fromJson(inString, com.goit.javadev.database.model.developer.Developer[].class);
        List<Developer> developerList =
                Arrays.stream(developers)
                        .collect(Collectors.toList());

        insertNewEntities(developerList);
    }

    @Override
    public void updateEntityFieldsById(Developer element, long id) {
        Session session = util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
                Developer existing = session.find(Developer.class, id);
                existing.setName(element.getName());
                existing.setAge(element.getAge());
                existing.setGender(element.getGender());
                existing.setSalary(element.getSalary());
                existing.setCompanyId(element.getCompanyId());
                session.merge(existing);
            transaction.commit();
        session.close();
    }

    @Override
    public Developer getEntityById(long id) {
        Session session = util.getSessionFactory().openSession();
        Developer developer = session.get(Developer.class, 10l);
        session.close();
        return developer;
    }

    @Override
    public List<Developer> getAllEntities() {
        Session session = util.getSessionFactory().openSession();
        List<Developer> result = session.createQuery("from Developer", Developer.class).list();
        session.close();
        return result;
    }

    @Override
    public void deleteEntitiesFromListById(long[] ids) {

    }

    @Override
    public void deleteById(long id) {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Developer developer = session.get(Developer.class, id);
        session.remove(developer);
        transaction.commit();
        session.close();

    }

    @Override
    public void clearTable() {

    }

    @Override
    public long getMaxId() {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String query = "SELECT max(id) FROM Developer";
        List list = session.createQuery(query).list();
        int maxId = ((Long) list.get(0)).intValue();
        transaction.commit();
        session.close();
        return maxId;
    }

    public static void main(String[] args) {
        DeveloperDaoHibernate developerDaoHibernate = new DeveloperDaoHibernate();
        Developer developer = new Developer();
        developer.setName("newName");
        developer.setAge(25);
        developer.setGender(Developer.Gender.MALE);
        developer.setSalary(5000);
        developer.setCompanyId(2);
        developerDaoHibernate.updateEntityFieldsById(developer, 1);
        System.out.println(developerDaoHibernate.getAllEntities());

        System.out.println(developerDaoHibernate.getMaxId());
    }
}

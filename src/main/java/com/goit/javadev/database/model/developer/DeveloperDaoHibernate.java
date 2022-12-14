package com.goit.javadev.database.model.developer;

import com.goit.javadev.database.feature.storage.HibernateUtil;
import com.goit.javadev.database.feature.storage.Storage;
import com.goit.javadev.database.model.CrudEntityDaoHibernate;
import com.goit.javadev.database.model.project.Project;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;

public class DeveloperDaoHibernate implements CrudEntityDaoHibernate<Developer> {
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
        Developer developer = session.get(Developer.class, id);
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
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE FROM Developer");
        query.executeUpdate();
        transaction.commit();
        session.close();


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
}

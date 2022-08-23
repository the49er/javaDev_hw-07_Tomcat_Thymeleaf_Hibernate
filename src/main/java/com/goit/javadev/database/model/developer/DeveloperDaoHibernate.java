package com.goit.javadev.database.model.developer;

import com.goit.javadev.database.feature.storage.HibernateUtil;
import com.goit.javadev.database.feature.storage.Storage;
import com.goit.javadev.database.model.CrudEntityDaoHibernate;
import com.goit.javadev.database.model.company.CompanyDaoJDBC;
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

    public static void main(String[] args) {
        DeveloperDaoHibernate developerDaoHibernate = new DeveloperDaoHibernate();
        CompanyDaoJDBC companyDaoJDBC = new CompanyDaoJDBC(Storage.getInstance().getConnection());

        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();

        Project project = new Project();
        project.setName("NewProject");
        project.setDescription("Test1");
        project.setDate(LocalDate.now());
        project.setCustomerId(1);
        project.setCompanyId(2);


        Developer developer = new Developer();
        developer.setName("newName");
        developer.setAge(99);
        developer.setGender(Developer.Gender.MALE);
        developer.setSalary(50000);
        developer.setCompanyId(5);

        Transaction tx = session.beginTransaction();
        session.persist(project);
        session.persist(developer);

        tx.commit();
        session.close();

        //developerDaoHibernate.insertNewEntity(developer);
        //developerDaoHibernate.updateEntityFieldsById(developer, 1);
        //System.out.println(developerDaoHibernate.getAllEntities());

        System.out.println(developerDaoHibernate.getMaxId());
        System.out.println(developerDaoHibernate.getEntityById(developerDaoHibernate.getMaxId()));
        //developerDaoHibernate.clearTable();
    }
}

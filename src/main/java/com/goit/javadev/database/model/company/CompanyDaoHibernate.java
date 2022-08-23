package com.goit.javadev.database.model.company;

import com.goit.javadev.database.feature.storage.HibernateUtil;
import com.goit.javadev.database.model.CrudEntityDaoHibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CompanyDaoHibernate implements CrudEntityDaoHibernate<Company> {
    private final HibernateUtil util = HibernateUtil.getInstance();


    @Override
    public void insertNewEntity(Company element) {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(element);
        transaction.commit();
        session.close();
    }


    @Override
    public void updateEntityFieldsById(Company element, long id) {
        Session session = util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
                Company existing = session.find(Company.class, id);
                existing.setName(element.getName());
                existing.setSpecialization(element.getSpecialization());
                session.merge(existing);
            transaction.commit();
        session.close();
    }

    @Override
    public Company getEntityById(long id) {
        Session session = util.getSessionFactory().openSession();
        Company company = session.get(Company.class, id);
        session.close();
        return company;
    }

    @Override
    public List<Company> getAllEntities() {
        Session session = util.getSessionFactory().openSession();
        List<Company> result = session.createQuery("from Company", Company.class).list();
        session.close();
        return result;
    }


    @Override
    public void deleteById(long id) {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Company company = session.get(Company.class, id);
        session.remove(company);
        transaction.commit();
        session.close();

    }

    @Override
    public void clearTable() {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE FROM Company");
        query.executeUpdate();
        transaction.commit();
        session.close();


    }

    @Override
    public long getMaxId() {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String query = "SELECT max(id) FROM Company";
        List list = session.createQuery(query).list();
        int maxId = ((Long) list.get(0)).intValue();
        transaction.commit();
        session.close();
        return maxId;
    }

    public static void main(String[] args) {
        CompanyDaoHibernate companyDaoHibernate = new CompanyDaoHibernate();
        Company company = new Company();
        company.setName("First");
        company.setSpecialization("Networking");
        companyDaoHibernate.insertNewEntity(company);
//        //companyDaoHibernate.updateEntityFieldsById(company, 1);
//        System.out.println(companyDaoHibernate.getAllEntities());
//
//        System.out.println(companyDaoHibernate.getMaxId());
//        System.out.println(companyDaoHibernate.getMaxId());
        System.out.println(companyDaoHibernate.getEntityById(companyDaoHibernate.getMaxId()));
        //companyDaoHibernate.clearTable();
    }
}

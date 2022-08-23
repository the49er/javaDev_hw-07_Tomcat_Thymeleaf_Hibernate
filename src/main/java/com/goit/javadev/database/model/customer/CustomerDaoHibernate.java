package com.goit.javadev.database.model.customer;

import com.goit.javadev.database.feature.storage.HibernateUtil;
import com.goit.javadev.database.model.CrudEntityDaoHibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerDaoHibernate implements CrudEntityDaoHibernate<Customer> {
    private final HibernateUtil util = HibernateUtil.getInstance();


    @Override
    public void insertNewEntity(Customer element) {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(element);
        transaction.commit();
        session.close();
    }


    @Override
    public void updateEntityFieldsById(Customer element, long id) {
        Session session = util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
                Customer existing = session.find(Customer.class, id);
                existing.setName(element.getName());
                existing.setBusinessSphere(element.getBusinessSphere());
                session.merge(existing);
            transaction.commit();
        session.close();
    }

    @Override
    public Customer getEntityById(long id) {
        Session session = util.getSessionFactory().openSession();
        Customer customer = session.get(Customer.class, id);
        session.close();
        return customer;
    }

    @Override
    public List<Customer> getAllEntities() {
        Session session = util.getSessionFactory().openSession();
        List<Customer> result = session.createQuery("from Customer", Customer.class).list();
        session.close();
        return result;
    }


    @Override
    public void deleteById(long id) {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = session.get(Customer.class, id);
        session.remove(customer);
        transaction.commit();
        session.close();

    }

    @Override
    public void clearTable() {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE FROM Customer");
        query.executeUpdate();
        transaction.commit();
        session.close();


    }

    @Override
    public long getMaxId() {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String query = "SELECT max(id) FROM Customer";
        List list = session.createQuery(query).list();
        int maxId = ((Long) list.get(0)).intValue();
        transaction.commit();
        session.close();
        return maxId;
    }

    public static void main(String[] args) {
        CustomerDaoHibernate customerDaoHibernate = new CustomerDaoHibernate();
        Customer customer = new Customer();
        customer.setName("First");
        customer.setBusinessSphere("Networking");
        customerDaoHibernate.insertNewEntity(customer);
//        //customerDaoHibernate.updateEntityFieldsById(customer, 1);
//        System.out.println(customerDaoHibernate.getAllEntities());
//
//        System.out.println(customerDaoHibernate.getMaxId());
//        System.out.println(customerDaoHibernate.getMaxId());
        System.out.println(customerDaoHibernate.getEntityById(customerDaoHibernate.getMaxId()));
        //customerDaoHibernate.clearTable();
    }
}

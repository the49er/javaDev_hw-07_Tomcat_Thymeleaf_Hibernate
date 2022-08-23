package com.goit.javadev.database.model.project;

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

public class ProjectDaoHibernate implements CrudEntityDaoHibernate<Project> {
    private final HibernateUtil util = HibernateUtil.getInstance();


    @Override
    public void insertNewEntity(Project element) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(element);
        transaction.commit();
        session.close();
    }


    @Override
    public void updateEntityFieldsById(Project element, long id) {
        Session session = util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
                Project existing = session.find(Project.class, id);
                existing.setName(element.getName());
                existing.setDescription(element.getDescription());
                existing.setDate(element.getDate());
                existing.setCompanyId(element.getCompanyId());
                existing.setCustomerId(element.getCustomerId());
                session.merge(existing);
            transaction.commit();
        session.close();
    }

    @Override
    public Project getEntityById(long id) {
        Session session = util.getSessionFactory().openSession();
        Project project = session.get(Project.class, id);
        session.close();
        return project;
    }

    @Override
    public List<Project> getAllEntities() {
        Session session = util.getSessionFactory().openSession();
        List<Project> result = session.createQuery("from Project", Project.class).list();
        session.close();
        return result;
    }


    @Override
    public void deleteById(long id) {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Project project = session.get(Project.class, id);
        session.remove(project);
        transaction.commit();
        session.close();

    }

    @Override
    public void clearTable() {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE FROM Project");
        query.executeUpdate();
        transaction.commit();
        session.close();


    }

    @Override
    public long getMaxId() {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String query = "SELECT max(id) FROM Project";
        List list = session.createQuery(query).list();
        int maxId = ((Long) list.get(0)).intValue();
        transaction.commit();
        session.close();
        return maxId;
    }

    public static void main(String[] args) {
        ProjectDaoHibernate projectDaoHibernate = new ProjectDaoHibernate();
        CompanyDaoJDBC companyDaoJDBC = new CompanyDaoJDBC(Storage.getInstance().getConnection());

        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();

        Project project = new Project();
        project.setName("NewProject");
        project.setDescription("Test1");
        project.setDate(LocalDate.now());
        project.setCustomerId(1);
        project.setCompanyId(2);

        Transaction tx = session.beginTransaction();
        session.persist(project);

        tx.commit();
        session.close();

        //projectDaoHibernate.insertNewEntity(project);
        //projectDaoHibernate.updateEntityFieldsById(project, 1);
        //System.out.println(projectDaoHibernate.getAllEntities());

        System.out.println(projectDaoHibernate.getMaxId());
        System.out.println(projectDaoHibernate.getEntityById(projectDaoHibernate.getMaxId()));
        //projectDaoHibernate.clearTable();
    }
}

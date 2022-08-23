package com.goit.javadev.database.model.skill;

import com.goit.javadev.database.feature.storage.HibernateUtil;
import com.goit.javadev.database.feature.storage.Storage;
import com.goit.javadev.database.model.CrudEntityDaoHibernate;
import com.goit.javadev.database.model.company.CompanyDaoJDBC;
import com.goit.javadev.database.model.skill.Skill;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;

public class SkillDaoHibernate implements CrudEntityDaoHibernate<Skill> {
    private final HibernateUtil util = HibernateUtil.getInstance();


    @Override
    public void insertNewEntity(Skill element) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(element);
        transaction.commit();
        session.close();
    }


    @Override
    public void updateEntityFieldsById(Skill element, long id) {
        Session session = util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
                Skill existing = session.find(Skill.class, id);
                existing.setProgramLang(element.getProgramLang());
                existing.setSkillLevel(element.getSkillLevel());
                session.merge(existing);
            transaction.commit();
        session.close();
    }

    @Override
    public Skill getEntityById(long id) {
        Session session = util.getSessionFactory().openSession();
        Skill skill = session.get(Skill.class, id);
        session.close();
        return skill;
    }

    @Override
    public List<Skill> getAllEntities() {
        Session session = util.getSessionFactory().openSession();
        List<Skill> result = session.createQuery("from Skill", Skill.class).list();
        session.close();
        return result;
    }


    @Override
    public void deleteById(long id) {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Skill skill = session.get(Skill.class, id);
        session.remove(skill);
        transaction.commit();
        session.close();

    }

    @Override
    public void clearTable() {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE FROM Skill");
        query.executeUpdate();
        transaction.commit();
        session.close();


    }

    @Override
    public long getMaxId() {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String query = "SELECT max(id) FROM Skill";
        List list = session.createQuery(query).list();
        int maxId = ((Long) list.get(0)).intValue();
        transaction.commit();
        session.close();
        return maxId;
    }

    public static void main(String[] args) {
        SkillDaoHibernate skillDaoHibernate = new SkillDaoHibernate();
        CompanyDaoJDBC companyDaoJDBC = new CompanyDaoJDBC(Storage.getInstance().getConnection());

        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();

        Skill skill = new Skill();
        skill.setProgramLang(ProgramLang.JAVA);
        skill.setSkillLevel(SkillLevel.JUNIOR);

        Transaction tx = session.beginTransaction();
        session.persist(skill);

        tx.commit();
        session.close();

        //skillDaoHibernate.insertNewEntity(skill);
        //skillDaoHibernate.updateEntityFieldsById(skill, 1);
        //System.out.println(skillDaoHibernate.getAllEntities());

        System.out.println(skillDaoHibernate.getMaxId());
        System.out.println(skillDaoHibernate.getEntityById(skillDaoHibernate.getMaxId()));
        //skillDaoHibernate.clearTable();
    }
}

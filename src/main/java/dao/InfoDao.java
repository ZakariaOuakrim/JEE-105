package dao;

import entity.Info;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * DAO pour l'entité Info utilisant l'API native Hibernate.
 */
public class InfoDao implements IDao<Info> {

    private static final SessionFactory sessionFactory =
            new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    @Override
    public void insert(Info info) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(info);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public void update(Info info) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.update(info);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public void delete(Info info) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Info managed = session.get(Info.class, info.getId());
            if (managed != null) session.delete(managed);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public void deleteById(Long id) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Info managed = session.get(Info.class, id);
            if (managed != null) session.delete(managed);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public Info findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Info.class, id);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Info> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Info").list();
        }
    }

    @Override
    public void deleteAll() {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.createQuery("DELETE FROM Info").executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public void close() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }
}

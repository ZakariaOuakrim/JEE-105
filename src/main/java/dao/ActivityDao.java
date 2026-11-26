package dao;

import entity.Activity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * DAO pour l'entité Activity utilisant l'API native Hibernate.
 */
public class ActivityDao implements IDao<Activity> {

    private static final SessionFactory sessionFactory =
            new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    @Override
    public void insert(Activity activity) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(activity);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public void update(Activity activity) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.update(activity);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public void delete(Activity activity) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Activity managed = session.get(Activity.class, activity.getId());
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
            Activity managed = session.get(Activity.class, id);
            if (managed != null) session.delete(managed);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public Activity findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Activity.class, id);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Activity> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Activity").list();
        }
    }

    @Override
    public void deleteAll() {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.createQuery("DELETE FROM Activity").executeUpdate();
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

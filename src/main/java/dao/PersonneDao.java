package dao;

import entity.Personne;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * DAO pour l'entité Personne utilisant l'API native Hibernate (SessionFactory / Session).
 *
 * Correspondance avec l'ancienne version JPA :
 *   EntityManagerFactory  →  SessionFactory
 *   EntityManager         →  Session
 *   em.persist()          →  session.save()
 *   em.merge()            →  session.update()
 *   em.remove()           →  session.delete()
 *   em.find()             →  session.get()
 *   JPQL                  →  HQL (syntaxe quasi-identique)
 */
public class PersonneDao implements IDao<Personne> {

    // SessionFactory : objet lourd, une seule instance pour toute l'application.
    // Elle lit hibernate.cfg.xml et les fichiers .hbm.xml référencés dedans.
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // "configure()" lit hibernate.cfg.xml depuis le classpath
            return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Exception ex) {
            System.err.println("Erreur lors de la création de la SessionFactory : " + ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }
    }

    // ── CRUD de base ────────────────────────────────────────────────────────

    @Override
    public void insert(Personne personne) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(personne);          // équivalent de em.persist()
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public void update(Personne personne) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.update(personne);        // équivalent de em.merge()
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public void delete(Personne personne) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            // On re-attache l'objet à la session courante avant de supprimer
            Personne managed = session.get(Personne.class, personne.getId());
            if (managed != null) {
                session.delete(managed);     // équivalent de em.remove()
            }
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
            Personne managed = session.get(Personne.class, id);
            if (managed != null) {
                session.delete(managed);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public Personne findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Personne.class, id);  // équivalent de em.find()
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Personne> findAll() {
        try (Session session = sessionFactory.openSession()) {
            // HQL — syntaxe quasi-identique au JPQL
            return session.createQuery("FROM Personne p").list();
        }
    }

    @Override
    public void deleteAll() {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.createQuery("DELETE FROM Personne").executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    // ── Méthodes métier ──────────────────────────────────────────────────────

    /** Alias de findAll() pour compatibilité avec Main.java */
    public List<Personne> trouveTout() {
        return findAll();
    }

    @SuppressWarnings("unchecked")
    public List<Personne> trouveParNom(String nom) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Personne p WHERE p.nom = :nom")
                          .setParameter("nom", nom)
                          .list();
        }
    }

    public List<Personne> findByNom(String nom) {
        return trouveParNom(nom);
    }

    public void sauve(Personne personne) {
        insert(personne);
    }

    /** Ferme la SessionFactory (à appeler à la fin du programme) */
    public void close() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }
}

package test;

import entity.CashPayment;
import entity.CreditCardPayment;
import entity.Payment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Programme de test pour les deux approches d'héritage Payment :
 *   1) 1 table avec <discriminator> et <subclass>
 *   2) 3 tables avec <joined-subclass>
 */
public class MainPayment {

    public static void main(String[] args) {
        System.out.println("APPROCHE 1 : 1 table (Payment) avec <discriminator>");
        testApproach("hibernate-payment-1table.cfg.xml");

        System.out.println("APPROCHE 2 : 3 tables (Payment, CashPayment, CreditCardPayment) avec <joined-subclass>");
        testApproach("hibernate-payment-3tables.cfg.xml");
    }

    @SuppressWarnings("unchecked")
    private static void testApproach(String configFile) {
        SessionFactory sessionFactory = new Configuration().configure(configFile).buildSessionFactory();

        try {
            // 1) Insertion des paiements
            try (Session session = sessionFactory.openSession()) {
                Transaction tx = session.beginTransaction();

                CashPayment cash = new CashPayment("Achat livre Java", "DZD");
                CreditCardPayment card = new CreditCardPayment("Abonnement Internet", "Visa");

                session.save(cash);
                session.save(card);

                tx.commit();
            }

            // 2) Lecture polymorphique (FROM Payment)
            try (Session session = sessionFactory.openSession()) {
                List<Payment> payments = session.createQuery("FROM Payment").list();
                System.out.println("--- Paiements récupérés ---");
                for (Payment p : payments) {
                    System.out.println(p);
                }
            }
        } finally {
            sessionFactory.close();
        }
    }
}

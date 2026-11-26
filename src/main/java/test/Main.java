package test;

import dao.PersonneDao;
import entity.Activity;
import entity.Adresse;
import entity.Info;
import entity.Personne;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        PersonneDao dao = new PersonneDao();

        try {
            // 1) Création des adresses (maintenant via objet Adresse → champs rue/ville dans Personne)
            Adresse adr1 = new Adresse("10 Boulevard Mohamed V", "Alger");
            Adresse adr2 = new Adresse("25 Rue Didouche Mourad", "Oran");

            // 2) Création des infos (relation One-To-One mappée dans Personne.hbm.xml)
            Info info1 = new Info("Enseignant-Chercheur", 'M', 35);
            Info info2 = new Info("Développeur Java", 'M', 28);

            Personne p1 = new Personne("Belharbi", "Salaheddine", adr1, info1);
            Personne p2 = new Personne("Cheb", "Djalil", adr2, info2);

            p1.addActivity(new Activity("Football", "Sport collectif le weekend"));
            p1.addActivity(new Activity("Natation", "Séance hebdomadaire"));
            p1.addActivity(new Activity("Lecture", "Livres de science et d'histoire"));

            p2.addActivity(new Activity("Randonnée", "Sorties en montagne"));
            p2.addActivity(new Activity("Musique", "Guitare acoustique"));

            System.out.println("\n--- Insertion des personnes avec leurs Activités ---");
            dao.insert(p1);
            dao.insert(p2);
            System.out.println("-> Personnes et Activités insérées avec succès !");

            System.out.println("\n--- Liste de toutes les personnes (HQL : FROM Personne) ---");
            List<Personne> laListe = dao.trouveTout();
            for (Personne p : laListe) {
                System.out.println(p);
            }

            System.out.println("\n--- Recherche par nom ('Belharbi') (HQL : FROM Personne WHERE nom = :nom) ---");
            List<Personne> parNom = dao.trouveParNom("Belharbi");
            for (Personne p : parNom) {
                System.out.println("Trouvé : " + p.getNom() + " " + p.getPrenom()
                        + " -> " + p.getActivities().size() + " activités : " + p.getActivities());
            }

        } finally {
            dao.close();
        }
    }
}

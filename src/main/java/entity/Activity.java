package entity;

/**
 * Entité Activity — POJO pur, aucune annotation JPA.
 * Le mapping vers la table "Activities" est défini dans Activity.hbm.xml.
 */
public class Activity {

    private Long id;
    private String nom;
    private String description;

    private Personne personne;

    public Activity() {
    }

    public Activity(String nom) {
        this.nom = nom;
    }

    public Activity(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    // ── Getters & Setters ──

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Personne getPersonne() { return personne; }
    public void setPersonne(Personne personne) { this.personne = personne; }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

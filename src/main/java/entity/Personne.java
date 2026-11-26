package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Entité Personne — POJO pur, aucune annotation JPA.
 * Le mapping vers la table "Personnes" est défini dans Personne.hbm.xml.
 */
public class Personne {

    private Long id;
    private String nom;
    private String prenom;

    // Champs de l'adresse intégrés directement (correspond au <component> dans le hbm.xml)
    private String rue;
    private String ville;

    // Relation One-To-One vers Info
    private Info info;

    // Relation One-To-Many vers Activity
    private List<Activity> activities = new ArrayList<>();

    public Personne() {
    }

    public Personne(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public Personne(String nom, String prenom, Adresse adresse, Info info) {
        this.nom = nom;
        this.prenom = prenom;
        if (adresse != null) {
            this.rue = adresse.getRue();
            this.ville = adresse.getVille();
        }
        this.info = info;
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
        activity.setPersonne(this);
    }

    public void removeActivity(Activity activity) {
        activities.remove(activity);
        activity.setPersonne(null);
    }

    // ── Getters & Setters ──

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getRue() { return rue; }
    public void setRue(String rue) { this.rue = rue; }

    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }

    public Info getInfo() { return info; }
    public void setInfo(Info info) { this.info = info; }

    public List<Activity> getActivities() { return activities; }
    public void setActivities(List<Activity> activities) { this.activities = activities; }

    @Override
    public String toString() {
        return "Personne{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", rue='" + rue + '\'' +
                ", ville='" + ville + '\'' +
                ", info=" + info +
                ", activities=" + activities +
                '}';
    }
}

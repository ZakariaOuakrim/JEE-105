package entity;

/**
 * Entité Info — POJO pur, aucune annotation JPA.
 * Le mapping vers la table "Info" est défini dans Info.hbm.xml.
 */
public class Info {

    private Long id;
    private String observation;
    private char sexe;
    private int age;

    public Info() {
    }

    public Info(String observation, char sexe, int age) {
        this.observation = observation;
        this.sexe = sexe;
        this.age = age;
    }

    // ── Getters & Setters ──

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getObservation() { return observation; }
    public void setObservation(String observation) { this.observation = observation; }

    public char getSexe() { return sexe; }
    public void setSexe(char sexe) { this.sexe = sexe; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    @Override
    public String toString() {
        return "Info{" +
                "id=" + id +
                ", observation='" + observation + '\'' +
                ", sexe=" + sexe +
                ", age=" + age +
                '}';
    }
}

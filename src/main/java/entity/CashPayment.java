package entity;

/**
 * Classe fille CashPayment héritant de Payment.
 */
public class CashPayment extends Payment {

    private String monnaie;

    public CashPayment() {
    }

    public CashPayment(String description, String monnaie) {
        super(description);
        this.monnaie = monnaie;
    }

    public String getMonnaie() {
        return monnaie;
    }

    public void setMonnaie(String monnaie) {
        this.monnaie = monnaie;
    }

    @Override
    public String toString() {
        return "CashPayment{" +
                "paymentId=" + getPaymentId() +
                ", description='" + getDescription() + '\'' +
                ", monnaie='" + monnaie + '\'' +
                '}';
    }
}

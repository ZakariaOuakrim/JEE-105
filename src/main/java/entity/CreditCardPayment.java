package entity;

/**
 * Classe fille CreditCardPayment héritant de Payment.
 */
public class CreditCardPayment extends Payment {

    private String type;

    public CreditCardPayment() {
    }

    public CreditCardPayment(String description, String type) {
        super(description);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CreditCardPayment{" +
                "paymentId=" + getPaymentId() +
                ", description='" + getDescription() + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

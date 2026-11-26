package entity;

/**
 * Classe mère Payment — POJO pur sans annotation.
 */
public class Payment {

    private int paymentId;
    private String description;

    public Payment() {
    }

    public Payment(String description) {
        this.description = description;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", description='" + description + '\'' +
                '}';
    }
}

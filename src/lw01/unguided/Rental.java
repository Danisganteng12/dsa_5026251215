package lw01.unguided;

public abstract class Rental implements Chargeable {

    private final String id;
    private final int days;

    protected Rental(String id, int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("Jumlah hari harus lebih dari nol.");
        }

        this.id = id;
        this.days = days;
    }

    public String getId() {
        return id;
    }

    public int getDays() {
        return days;
    }

    @Override
    public abstract int calculateCharge();

    public int calculateCharge(int units) {
        if (units <= 0) {
            throw new IllegalArgumentException("Jumlah unit harus lebih dari nol.");
        }

        return units * calculateCharge();
    }

    public String label() {
        return "Rental";
    }

    public String summary() {
        return id + " | " + label() + " | " + calculateCharge();
    }
}

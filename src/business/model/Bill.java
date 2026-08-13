package business.model;

public abstract class Bill {

    private int id;
    private Patient bimar;

    public Bill(int id, Patient bimar) {
        this.id = id;
        this.bimar = bimar;
    }

    public int getId() {
        return id;
    }

    public Patient getBimar() {
        return bimar;
    }

    public abstract double mohasebeHazine();

    public abstract String getNoeBil();
}
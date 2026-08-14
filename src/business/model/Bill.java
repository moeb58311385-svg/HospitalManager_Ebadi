package business.model;

public abstract class Bill {

    private int id;
    private Patient bimar;
    //برای بخش تعریف کردم 
    private String naamBakhsh;

    public Bill(int id, Patient bimar) {
        this(id, bimar, null);
    }

    public Bill(int id, Patient bimar, String naamBakhsh) {
        this.id = id;
        this.bimar = bimar;
        this.naamBakhsh = naamBakhsh;
    }

    public int getId() {
        return id;
    }

    public Patient getBimar() {
        return bimar;
    }

    public String getNaamBakhsh() {
        return naamBakhsh;
    }

    
    protected double hazineJanebiBakhsh() {
        if (naamBakhsh == null) {
            return 0;
        }
        switch (naamBakhsh) {
            case "جراحی":
                return 300000;
            case "اورژانس":
                return 100000;
            case "داخلی":
                return 50000;
            default:
                return 0;
        }
    }

    public abstract double mohasebeHazine();

    public abstract String getNoeBil();
}
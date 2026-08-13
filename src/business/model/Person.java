package business.model;

public abstract class Person {

    private int id;
    private String naam;
    private String shomareMeli;
    private int sen;

    public Person(int id, String naam, String shomareMeli, int sen) {
        this.id = id;
        this.naam = naam;
        this.shomareMeli = shomareMeli;
        this.sen = sen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getShomareMeli() {
        return shomareMeli;
    }

    public void setShomareMeli(String shomareMeli) {
        this.shomareMeli = shomareMeli;
    }

    public int getSen() {
        return sen;
    }

    public void setSen(int sen) {
        this.sen = sen;
    }
}
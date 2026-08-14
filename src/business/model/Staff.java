package business.model;



public class Staff {

    private int id;
    private String naam;
    private String semat; //برای سمت ها تو آخرین ورژن تعریف کردم
    private int bakhshId;
    private String naamBakhsh; // برای نمایش راحت تر گزارشها گذاشتمش

    
    
    public Staff(int id, String naam, String semat, int bakhshId) {
        this.id = id;
        this.naam = naam;
        this.semat = semat;
        this.bakhshId = bakhshId;
    }
    
    

    public int getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public String getSemat() {
        return semat;
    }
    
    

    public int getBakhshId() {
        return bakhshId;
    }

    public String getNaamBakhsh() {
        return naamBakhsh;
    }

    public void setNaamBakhsh(String naamBakhsh) {
        this.naamBakhsh = naamBakhsh;
    }
    
    

    @Override
    public String toString() {
        return naam + " - " + semat + (naamBakhsh != null ? " - " + naamBakhsh : "");
    }
}

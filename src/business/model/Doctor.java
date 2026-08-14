package business.model;

public class Doctor extends Person {

    private String takhasos;
    private String naamBakhsh;
    private int saatShoru;
    private int saatPayan;
    private int zarfiatNobat;
    private int tedadNobat;
    
    
    

    public Doctor(int id, String naam, String shomareMeli, int sen, String takhasos,
                  String naamBakhsh, int saatShoru, int saatPayan, int zarfiatNobat) {
        super(id, naam, shomareMeli, sen);
        this.takhasos = takhasos;
        this.naamBakhsh = naamBakhsh;
        this.saatShoru = saatShoru;
        this.saatPayan = saatPayan;
        this.zarfiatNobat = zarfiatNobat;
        //this.zarfiatN = zarfiatN;
        this.tedadNobat = 0;
    }

    public String getTakhasos() {
        return takhasos;
    }

    public String getNaamBakhsh() {
        return naamBakhsh;
    }

    public int getSaatShoru() {
        return saatShoru;
    }

    
    public int getSaatPayan() {
        return saatPayan;
    }

    public int getZarfiatNobat() {
        return zarfiatNobat;
    }

    public int getTedadNobat() {
        return tedadNobat;
    }

    
    
    
    public void setTedadNobat(int tedadNobat) {
        this.tedadNobat = tedadNobat;
    }

    public boolean saatDarShiftAst(int saat) {
        return saat >= saatShoru && saat <= saatPayan;
    }

    
    
    public boolean zarfiatDarad() {
        return tedadNobat < zarfiatNobat;
    }

    public void ezafeNobat() {
        if (zarfiatDarad()) {
            tedadNobat++;
        }
    }

    public void hazfNobat() {
        if (tedadNobat > 0) {
            tedadNobat--;
        }
    }

    @Override
    public String toString() {
        return getNaam() + " - " + takhasos + " - " + naamBakhsh;
    }
}

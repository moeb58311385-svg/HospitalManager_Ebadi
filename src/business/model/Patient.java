package business.model;



public class Patient extends Person {

    private boolean bastari;
    private boolean emergency;
    private int bakhshId;
    
    

    
    public Patient(int id, String naam, String shomareMeli, int sen) {
        super(id, naam, shomareMeli, sen);
        this.bastari = false;
        this.emergency = false;
        this.bakhshId = 0;
    }

    public boolean isBastari() {
        return bastari;
    }

    
    
    public void setBastari(boolean bastari) {
        this.bastari = bastari;
    }

    public boolean isEmergency() {
        return emergency;
    }

    
    public void setEmergency(boolean emergency) {
        this.emergency = emergency;
    }

    public int getBakhshId() {
        return bakhshId;
    }

    
    
    public void setBakhshId(int bakhshId) {
        this.bakhshId = bakhshId;
    }

    @Override
    public String toString() {
        return "شماره: " + getId() + " | نام: " + getNaam() + " | کد ملی: " + getShomareMeli();
    }
}

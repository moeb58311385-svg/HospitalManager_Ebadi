package business.model;



public class EmergencyBill extends Bill {

    public EmergencyBill(int id, Patient bimar) {
        super(id, bimar);
    }
    
    

    public EmergencyBill(int id, Patient bimar, String naamBakhsh) {
        super(id, bimar, naamBakhsh);
    }

    
    
    @Override
    public double mohasebeHazine() {
        return 500000 + hazineJanebiBakhsh();
    }

    @Override
    public String getNoeBil() {
        return "ویزیت اورژانسی";
    }
}

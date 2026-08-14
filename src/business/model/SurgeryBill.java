package business.model;



public class SurgeryBill extends Bill {

    public SurgeryBill(int id, Patient bimar) {
        super(id, bimar);
    }

    public SurgeryBill(int id, Patient bimar, String naamBakhsh) {
        super(id, bimar, naamBakhsh);
    }

    
    
    @Override
    public double mohasebeHazine() {
        return 1000000 + hazineJanebiBakhsh();
    }

    @Override
    public String getNoeBil() {
        return "جراحی";
    }
}

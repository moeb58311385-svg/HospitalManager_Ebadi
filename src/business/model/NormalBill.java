package business.model;



public class NormalBill extends Bill {

    public NormalBill(int id, Patient bimar) {
        super(id, bimar);
    }
    
    

    public NormalBill(int id, Patient bimar, String naamBakhsh) {
        super(id, bimar, naamBakhsh);
    }

    
    
    @Override
    public double mohasebeHazine() {
        return 200000 + hazineJanebiBakhsh();
    }

    @Override
    public String getNoeBil() {
        return "ویزیت عادی";
    }
}

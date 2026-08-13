package business.model;

public class SurgeryBill extends Bill {

    public SurgeryBill(int id, Patient bimar) {
        super(id, bimar);
    }

    @Override
    public double mohasebeHazine() {
        return 1000000;
    }

    @Override
    public String getNoeBil() {
        return "جراحی";
    }
}
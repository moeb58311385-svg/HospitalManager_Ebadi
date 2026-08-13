package business.model;

public class EmergencyBill extends Bill {

    public EmergencyBill(int id, Patient bimar) {
        super(id, bimar);
    }

    @Override
    public double mohasebeHazine() {
        return 500000;
    }

    @Override
    public String getNoeBil() {
        return "ویزیت اورژانسی";
    }
}
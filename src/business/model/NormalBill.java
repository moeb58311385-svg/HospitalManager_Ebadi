package business.model;

public class NormalBill extends Bill {

    public NormalBill(int id, Patient bimar) {
        super(id, bimar);
    }

    @Override
    public double mohasebeHazine() {
        return 200000;
    }

    @Override
    public String getNoeBil() {
        return "ویزیت عادی";
    }
}
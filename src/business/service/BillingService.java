package business.service;

import business.model.Bill;
import business.model.EmergencyBill;
import business.model.NormalBill;
import business.model.Patient;
import business.model.SurgeryBill;
import data.BillDAO;
import data.PatientDAO;
import java.util.ArrayList;

public class BillingService {

    private BillDAO billDAO;
    private PatientDAO patientDAO;

    public BillingService() {
        billDAO = new BillDAO();
        patientDAO = new PatientDAO();
    }

    public boolean sabtBilAdi(int bimarId) {
        Patient bimar = patientDAO.getPatientById(bimarId);
        if (bimar == null) {
            System.out.println("بیمار پیدا نشد.");
            return false;
        }

        Bill bil = new NormalBill(0, bimar);
        return sabtBil(bil);
    }

    public boolean sabtBilEmergency(int bimarId) {
        Patient bimar = patientDAO.getPatientById(bimarId);
        if (bimar == null) {
            return false;
        }

        Bill bil = new EmergencyBill(0, bimar);
        return sabtBil(bil);
    }

    public boolean sabtBilJarahi(int bimarId) {
        Patient bimar = patientDAO.getPatientById(bimarId);
        if (bimar == null) {
            return false;
        }

        Bill bil = new SurgeryBill(0, bimar);
        return sabtBil(bil);
    }

    public ArrayList<String> getSoorteshesab(int bimarId) {
        return billDAO.getBillsByPatient(bimarId);
    }

    private boolean sabtBil(Bill bil) {
        boolean natije = billDAO.addBill(bil);

        if (natije) {
            System.out.println("صورتحساب با موفقیت صادر شد.");
            System.out.println("نوع هزینه: " + bil.getNoeBil());
            System.out.println("مبلغ: " + bil.mohasebeHazine() + " تومان");
        }

        return natije;
    }
}

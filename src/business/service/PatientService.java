package business.service;

import business.model.Patient;
import data.PatientDAO;
import java.util.ArrayList;

public class PatientService {

    private PatientDAO patientDAO;

    public PatientService() {
        patientDAO = new PatientDAO();
    }

    public boolean sabtBimar(Patient bimar) {
        if (bimar == null) {
            System.out.println("اطلاعات بیمار صحیح نیست.");
            return false;
        }

        if (bimar.getNaam() == null || bimar.getNaam().isEmpty()) {
            System.out.println("نام بیمار وارد نشده است.");
            return false;
        }

        if (bimar.getShomareMeli() == null || bimar.getShomareMeli().isEmpty()) {
            System.out.println("شماره ملی وارد نشده است.");
            return false;
        }

        if (bimar.getSen() <= 0) {
            System.out.println("سن بیمار صحیح نیست.");
            return false;
        }

        boolean natije = patientDAO.addPatient(bimar);
        if (natije) {
            System.out.println("بیمار با موفقیت ثبت شد.");
        }

        return natije;
    }

    public Patient peydaKardanBimar(int id) {
        return patientDAO.getPatientById(id);
    }

    public ArrayList<Patient> namayeshBimaran() {
        return patientDAO.getAllPatients();
    }

    public boolean virayeshBimar(Patient bimar) {
        if (bimar == null) {
            return false;
        }

        return patientDAO.updatePatient(bimar);
    }
}
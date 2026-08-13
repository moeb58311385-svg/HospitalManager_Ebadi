package business.service;

import business.model.Patient;
import data.DepartmentDAO;
import data.PatientDAO;

public class EmergencyService {

    private PatientDAO patientDAO;
    private BillingService billingService;
    private DepartmentDAO departmentDAO;

    public EmergencyService() {
        patientDAO = new PatientDAO();
        billingService = new BillingService();
        departmentDAO = new DepartmentDAO();
    }

    public boolean faalKardanEmergency(int bimarId) {
        Patient bimar = patientDAO.getPatientById(bimarId);
        if (bimar == null) {
            System.out.println("بیمار پیدا نشد.");
            return false;
        }

        bimar.setEmergency(true);
        patientDAO.updatePatient(bimar);
        billingService.sabtBilEmergency(bimarId);

        System.out.println("بیمار در وضعیت اورژانسی قرار گرفت.");
        System.out.println("بیمار اورژانسی در اولویت قرار دارد.");

        if (departmentDAO.hameBakhshHaPorHastand()) {
            System.out.println("هشدار بحرانی: ظرفیت کل بیمارستان تکمیل است و بیمار اورژانسی جدید وارد شده است.");
        }

        return true;
    }

    public boolean payanEmergency(int bimarId) {
        Patient bimar = patientDAO.getPatientById(bimarId);
        if (bimar == null) {
            return false;
        }

        bimar.setEmergency(false);
        patientDAO.updatePatient(bimar);
        System.out.println("وضعیت اورژانسی بیمار بهبود یافت.");

        return true;
    }
}

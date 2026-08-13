package business.service;

import business.model.Department;
import business.model.Patient;
import data.DepartmentDAO;
import data.PatientDAO;

public class AdmissionService {

    private DepartmentDAO departmentDAO;
    private PatientDAO patientDAO;
    private FinanceService financeService;

    public AdmissionService() {
        departmentDAO = new DepartmentDAO();
        patientDAO = new PatientDAO();
        financeService = new FinanceService();
    }

    public boolean bastariBimar(int bimarId, int bakhshId) {
        Patient bimar = patientDAO.getPatientById(bimarId);
        if (bimar == null) {
            System.out.println("بیمار پیدا نشد.");
            return false;
        }

        Department bakhsh = departmentDAO.getDepartmentById(bakhshId);
        if (bakhsh == null) {
            System.out.println("بخش مورد نظر پیدا نشد.");
            return false;
        }

        if (bimar.isBastari()) {
            System.out.println("این بیمار قبلا بستری شده است.");
            return false;
        }

        int tedadBimaran = departmentDAO.getTedadBimaran(bakhshId);
        if (tedadBimaran >= bakhsh.getZarfiat()) {
            System.out.println("ظرفیت این بخش تکمیل است.");
            return false;
        }

        bimar.setBastari(true);
        bimar.setBakhshId(bakhshId);
        boolean natije = patientDAO.updatePatient(bimar);

        if (natije) {
            System.out.println("بیمار با موفقیت بستری شد.");
            financeService.afzayeshDarAmad(100000, "هزینه پذیرش بیمار");
        }

        return natije;
    }

    public boolean tarkhisBimar(int bimarId, int bakhshId) {
        Patient bimar = patientDAO.getPatientById(bimarId);
        Department bakhsh = departmentDAO.getDepartmentById(bakhshId);

        if (bimar == null || bakhsh == null) {
            System.out.println("اطلاعات بیمار یا بخش صحیح نیست.");
            return false;
        }

        if (!bimar.isBastari() || bimar.getBakhshId() != bakhshId) {
            System.out.println("این بیمار در این بخش بستری نیست.");
            return false;
        }

        bimar.setBastari(false);
        bimar.setBakhshId(0);
        //bimar.BakhshId(0);
        boolean natije = patientDAO.updatePatient(bimar);

        if (natije) {
            System.out.println("بیمار با موفقیت مرخص شد.");

            if (departmentDAO.getTedadBimaran(bakhshId) == 0) {
                System.out.println("تمام بیماران این بخش مرخص شدند.");
                System.out.println("پاداش بهداشت به مبلغ 500000 تومان اضافه شد.");
                financeService.afzayeshDarAmad(500000, "پاداش بهداشت");
            }
        }

        return natije;
    }
}

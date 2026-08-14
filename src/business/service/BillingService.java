package business.service;



import business.model.Bill;
import business.model.Department;
import business.model.EmergencyBill;
import business.model.NormalBill;
import business.model.Patient;
import business.model.SurgeryBill;
import data.BillDAO;
import data.DepartmentDAO;
import data.PatientDAO;
import java.util.ArrayList;
import util.AppLogger;
import util.SoundUtil;

public class BillingService {

    private BillDAO billDAO;
    private PatientDAO patientDAO;
    private DepartmentDAO departmentDAO;
    private FinanceService financeService;
    private String lastMessage;

    public BillingService() {
        billDAO = new BillDAO();
        patientDAO = new PatientDAO();
        departmentDAO = new DepartmentDAO();
        financeService = new FinanceService();
    }

    
    
    public String getLastMessage() {
        return lastMessage;
    }

    
    
    private String naamBakhshBimar(Patient bimar) {
        if (bimar.getBakhshId() <= 0) {
            return null;
        }
        Department bakhsh = departmentDAO.getDepartmentById(bimar.getBakhshId());
        return bakhsh != null ? bakhsh.getNaamBakhsh() : null;
    }

    public boolean sabtBilAdi(int bimarId) {
        Patient bimar = patientDAO.getPatientById(bimarId);
        if (bimar == null) {
            lastMessage = "بیمار پیدا نشد.";
            System.out.println(lastMessage);
            AppLogger.error("صدور صورتحساب عادی ناموفق: بیمار با شماره " + bimarId + " پیدا نشد.");
            SoundUtil.failure();
            return false;
        }

        Bill bil = new NormalBill(0, bimar, naamBakhshBimar(bimar));
        return sabtBil(bil);
    }

    public boolean sabtBilEmergency(int bimarId) {
        Patient bimar = patientDAO.getPatientById(bimarId);
        if (bimar == null) {
            lastMessage = "بیمار پیدا نشد.";
            AppLogger.error("صدور صورتحساب اورژانسی ناموفق: بیمار با شماره " + bimarId + " پیدا نشد.");
            SoundUtil.failure();
            return false;
        }

        Bill bil = new EmergencyBill(0, bimar, naamBakhshBimar(bimar));
        return sabtBil(bil);
    }

    public boolean sabtBilJarahi(int bimarId) {
        Patient bimar = patientDAO.getPatientById(bimarId);
        if (bimar == null) {
            lastMessage = "بیمار پیدا نشد.";
            AppLogger.error("صدور صورتحساب جراحی ناموفق: بیمار با شماره " + bimarId + " پیدا نشد.");
            SoundUtil.failure();
            return false;
        }

        Bill bil = new SurgeryBill(0, bimar, naamBakhshBimar(bimar));
        return sabtBil(bil);
    }

    public ArrayList<String> getSoorteshesab(int bimarId) {
        return billDAO.getBillsByPatient(bimarId);
    }

    private boolean sabtBil(Bill bil) {
        boolean natije = billDAO.addBill(bil);

        
        
        
        if (natije) {
            lastMessage = "صورتحساب با موفقیت صادر شد. نوع هزینه: " + bil.getNoeBil()
                    + " | مبلغ: " + bil.mohasebeHazine() + " تومان";
            System.out.println(lastMessage);
            AppLogger.success("صورتحساب صادر شد. بیمار: " + bil.getBimar().getId()
                    + " | نوع: " + bil.getNoeBil() + " | مبلغ: " + bil.mohasebeHazine() + " تومان"
                    + " | بخش: " + (bil.getNaamBakhsh() == null ? "نامشخص" : bil.getNaamBakhsh()));
            SoundUtil.success();

            // صورتحساب خودکار میره تو حساب بیمارستان
            financeService.afzayeshDarAmad(bil.mohasebeHazine(), "صورتحساب: " + bil.getNoeBil());
        } else {
            lastMessage = "صدور صورتحساب انجام نشد.";
            AppLogger.error("صدور صورتحساب ناموفق برای بیمار " + bil.getBimar().getId());
            SoundUtil.failure();
        }

        return natije;
    }
}

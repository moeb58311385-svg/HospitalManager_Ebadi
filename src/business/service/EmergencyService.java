package business.service;



import business.model.Patient;
import data.DepartmentDAO;
import data.PatientDAO;
import util.AppLogger;
import util.EmergencyModeManager;
import util.SoundUtil;

public class EmergencyService {

    private PatientDAO patientDAO;
    private BillingService billingService;
    private DepartmentDAO departmentDAO;
    private String lastMessage;
    private String lastAlertMessage;
    

    public EmergencyService() {
        patientDAO = new PatientDAO();
        billingService = new BillingService();
        departmentDAO = new DepartmentDAO();
    }
    
    

    public String getLastMessage() {
        return lastMessage;
    }

    public String getLastAlertMessage() {
        return lastAlertMessage;
    }
    

    public boolean faalKardanEmergency(int bimarId) {
        lastAlertMessage = null;
        Patient bimar = patientDAO.getPatientById(bimarId);
        if (bimar == null) {
            lastMessage = "بیمار پیدا نشد.";
            AppLogger.error("فعال‌سازی اورژانس ناموفق: بیمار با شماره " + bimarId + " پیدا نشد.");
            SoundUtil.failure();
            return false;
        }

        bimar.setEmergency(true);
        patientDAO.updatePatient(bimar);
        billingService.sabtBilEmergency(bimarId);

        //ورود به حالت اورژانس، فعلا نوبت نمیده
        EmergencyModeManager.activate();

        lastMessage = "بیمار در وضعیت اورژانسی قرار گرفت. بیمار اورژانسی بدون نوبت در اولویت قرار دارد "
                + "و نوبت‌های عادی به طور موقت به تعویق افتادند.";
        AppLogger.warning("بیمار " + bimarId + " وارد حالت اورژانسی شد.");
        SoundUtil.success();

        if (departmentDAO.hameBakhshHaPorHastand()) {
            lastAlertMessage = "هشدار بحرانی: ظرفیت کل بیمارستان تکمیل است و یک بیمار اورژانسی جدید وارد شده است!";
            AppLogger.warning(lastAlertMessage);
        }

        return true;
    }

    public boolean payanEmergency(int bimarId) {
        Patient bimar = patientDAO.getPatientById(bimarId);
        if (bimar == null) {
            lastMessage = "بیمار پیدا نشد.";
            AppLogger.error("پایان اورژانس ناموفق: بیمار با شماره " + bimarId + " پیدا نشد.");
            SoundUtil.failure();
            return false;
        }

        bimar.setEmergency(false);
        patientDAO.updatePatient(bimar);

        // خروج از حالت اورژانسی و برقراری حالت عادی
        EmergencyModeManager.deactivate();

        lastMessage = "وضعیت اورژانسی بیمار پایان یافت. سیستم "
                + (EmergencyModeManager.isActive() ? "همچنان در حالت اورژانس باقی می‌ماند (بیمار اورژانسی دیگری فعال است)."
                : "به حالت عادی بازگشت و نوبت‌دهی از سر گرفته شد.");
        AppLogger.success("وضعیت اورژانسی بیمار " + bimarId + " پایان یافت.");
        SoundUtil.success();

        return true;
    }
}

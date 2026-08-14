package business.service;


import business.model.Department;

import business.model.Patient;
import data.DepartmentDAO;
import data.PatientDAO;
import util.AppLogger;
import util.SoundUtil;

public class AdmissionService {

    private DepartmentDAO departmentDAO;
    private PatientDAO patientDAO;
    private FinanceService financeService;

    
    
    //پیام آخر برای کاربر
    private String lastMessage;
    // پیام برای یه اتفاق بحرانی
    private String lastAlertMessage;

    public AdmissionService() {
        departmentDAO = new DepartmentDAO();
        patientDAO = new PatientDAO();
        financeService = new FinanceService();
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public String getLastAlertMessage() {
        return lastAlertMessage;
    }

    
    
    public boolean bastariBimar(int bimarId, int bakhshId) {
        lastAlertMessage = null;
        Patient bimar = patientDAO.getPatientById(bimarId);
        if (bimar == null) {
            lastMessage = "بیمار پیدا نشد.";
            AppLogger.error("بستری ناموفق: بیمار با شماره " + bimarId + " پیدا نشد.");
            SoundUtil.failure();
            return false;
        }
        
        

        Department bakhsh = departmentDAO.getDepartmentById(bakhshId);
        if (bakhsh == null) {
            lastMessage = "بخش مورد نظر پیدا نشد.";
            AppLogger.error("بستری ناموفق: بخش با شماره " + bakhshId + " پیدا نشد.");
            SoundUtil.failure();
            return false;
        }

        if (bimar.isBastari()) {
            lastMessage = "این بیمار قبلا بستری شده است.";
            AppLogger.warning("تلاش برای بستری مجدد بیمار " + bimarId + " که قبلا بستری بوده است.");
            SoundUtil.failure();
            return false;
        }

        int tedadBimaran = departmentDAO.getTedadBimaran(bakhshId);
        if (tedadBimaran >= bakhsh.getZarfiat()) {
            lastMessage = "ظرفیت این بخش تکمیل است. عملیات بستری لغو شد.";
            AppLogger.warning("بستری رد شد: ظرفیت بخش «" + bakhsh.getNaamBakhsh() + "» تکمیل است ("
                    + tedadBimaran + "/" + bakhsh.getZarfiat() + ").");
            SoundUtil.failure();
            return false;
        }

        bimar.setBastari(true);
        bimar.setBakhshId(bakhshId);
        boolean natije = patientDAO.updatePatient(bimar);

        
        
        if (natije) {
            lastMessage = "بیمار با موفقیت بستری شد.";
            AppLogger.success("بیمار " + bimarId + " در بخش «" + bakhsh.getNaamBakhsh() + "» بستری شد.");
            SoundUtil.success();
            financeService.afzayeshDarAmad(100000, "هزینه پذیرش بیمار");

            // اگه ظرفیت بیمارستان پر شد
            if (departmentDAO.hameBakhshHaPorHastand()) {
                lastAlertMessage = "هشدار بحرانی: با این بستری، ظرفیت کل بیمارستان تکمیل شده است.";
                AppLogger.warning(lastAlertMessage);
            }
        } else {
            lastMessage = "بستری بیمار انجام نشد.";
            AppLogger.error("خطای پایگاه داده هنگام بستری بیمار " + bimarId);
            SoundUtil.failure();
        }

        return natije;
    }

    public boolean tarkhisBimar(int bimarId, int bakhshId) {
        lastAlertMessage = null;
        Patient bimar = patientDAO.getPatientById(bimarId);
        Department bakhsh = departmentDAO.getDepartmentById(bakhshId);
        

        if (bimar == null || bakhsh == null) {
            lastMessage = "اطلاعات بیمار یا بخش صحیح نیست.";
            AppLogger.error("ترخیص ناموفق: اطلاعات بیمار (" + bimarId + ") یا بخش (" + bakhshId + ") صحیح نیست.");
            SoundUtil.failure();
            return false;
        }

        if (!bimar.isBastari() || bimar.getBakhshId() != bakhshId) {
            lastMessage = "این بیمار در این بخش بستری نیست.";
            AppLogger.warning("تلاش اشتباه برای مرخص کردن بیمار " + bimarId + " از بخش " + bakhshId);
            SoundUtil.failure();
            return false;
        }

        // بررسی شلوغ بودن بخش
        boolean bakhshGhablanPorBoode = departmentDAO.getTedadBimaran(bakhshId) >= bakhsh.getZarfiat();

        bimar.setBastari(false);
        bimar.setBakhshId(0);
        boolean natije = patientDAO.updatePatient(bimar);
        
        

        if (natije) {
            lastMessage = "بیمار با موفقیت مرخص شد.";
            AppLogger.success("بیمار " + bimarId + " از بخش «" + bakhsh.getNaamBakhsh() + "» مرخص شد.");
            SoundUtil.success();

            if (departmentDAO.getTedadBimaran(bakhshId) == 0) {
                lastMessage += " تمام بیماران این بخش مرخص شدند.";
                financeService.afzayeshDarAmad(500000, "پاداش بهداشت");

                if (bakhshGhablanPorBoode) {
                    lastAlertMessage = "پیام موفقیت: بخش شلوغ «" + bakhsh.getNaamBakhsh()
                            + "» به طور کامل تخلیه شد و مبلغ 500,000 تومان پاداش بهداشت به بودجه اضافه شد.";
                } else {
                    lastAlertMessage = "تمام بیماران بخش «" + bakhsh.getNaamBakhsh()
                            + "» مرخص شدند و مبلغ 500,000 تومان پاداش بهداشت به بودجه اضافه شد.";
                }
                AppLogger.success(lastAlertMessage);
            }
        } else {
            lastMessage = "بیمار مرخص نشد.";
            AppLogger.error("خطای پایگاه داده هنگام مرخص کردن بیمار " + bimarId);
            SoundUtil.failure();
        }

        return natije;
    }
}

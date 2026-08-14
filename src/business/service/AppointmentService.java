package business.service;


import business.model.Appointment;
import business.model.Doctor;
import business.model.Patient;
import data.AppointmentDAO;
import data.DoctorDAO;
import data.PatientDAO;
import java.time.LocalDate;
import util.AppLogger;
import util.EmergencyModeManager;
import util.SoundUtil;



public class AppointmentService {

    private AppointmentDAO appointmentDAO;
    private DoctorDAO doctorDAO;
    private PatientDAO patientDAO;
    private FinanceService financeService;
    private String lastMessage;

    public AppointmentService() {
        appointmentDAO = new AppointmentDAO();
        doctorDAO = new DoctorDAO();
        patientDAO = new PatientDAO();
        financeService = new FinanceService();
    }

   
    
    public String getLastMessage() {
        return lastMessage;
    }

    public boolean sabtNobat(int bimarId, int doctorId, LocalDate tarikh, int saat) {
       // برای حالت اورژانسی تعریف کردم که نوبت عادی انجام نشه
        if (EmergencyModeManager.isActive()) {
            lastMessage = "به دلیل فعال بودن حالت اورژانس، نوبت‌دهی عادی به طور موقت متوقف شده است. لطفا بعدا تلاش کنید.";
            AppLogger.warning("ثبت نوبت عادی برای بیمار " + bimarId + " به دلیل حالت اورژانس رد شد.");
            SoundUtil.failure();
            return false;
        }

        Patient bimar = patientDAO.getPatientById(bimarId);
        if (bimar == null) {
            lastMessage = "بیمار پیدا نشد.";
            AppLogger.error("ثبت نوبت ناموفق: بیمار با شماره " + bimarId + " پیدا نشد.");
            SoundUtil.failure();
            return false;
        }

        Doctor doctor = doctorDAO.getDoctorById(doctorId);
        if (doctor == null) {
            lastMessage = "پزشک پیدا نشد.";
            AppLogger.error("ثبت نوبت ناموفق: پزشک با شماره " + doctorId + " پیدا نشد.");
            SoundUtil.failure();
            return false;
        }

        
        
        
        if (!doctor.saatDarShiftAst(saat)) {
            lastMessage = "این ساعت خارج از شیفت کاری پزشک است.";
            AppLogger.warning("ثبت نوبت رد شد: ساعت " + saat + " خارج از شیفت پزشک " + doctorId + " است.");
            SoundUtil.failure();
            return false;
        }

        
        
        int tedadNobat = appointmentDAO.getTedadNobat(doctorId, tarikh);
        doctor.setTedadNobat(tedadNobat);
        if (!doctor.zarfiatDarad()) {
            lastMessage = "ظرفیت نوبت‌دهی پزشک برای این روز تکمیل است.";
            AppLogger.warning("ثبت نوبت رد شد: ظرفیت نوبت‌دهی پزشک " + doctorId + " در تاریخ " + tarikh + " تکمیل است.");
            SoundUtil.failure();
            return false;
        }

        
        Appointment nobat = new Appointment(0, bimar, doctor, tarikh, saat);
        boolean natije = appointmentDAO.addAppointment(nobat);

        if (natije) {
            lastMessage = "نوبت با موفقیت ثبت شد.";
            AppLogger.success("نوبت برای بیمار " + bimarId + " با پزشک " + doctorId
                    + " در تاریخ " + tarikh + " ساعت " + saat + " ثبت شد.");
            SoundUtil.success();
            financeService.afzayeshDarAmad(100000, "هزینه پایه ثبت نوبت");
        } else {
            lastMessage = "خطا در ثبت نوبت";
            AppLogger.error("خطای پایگاه داده هنگام ثبت نوبت برای بیمار " + bimarId);
            SoundUtil.failure();
        }

        return natije;
    }

    public boolean anjamVizit(int nobatId) {
        boolean natije = appointmentDAO.completeAppointment(nobatId);

        if (natije) {
            appointmentDAO.deleteAppointment(nobatId);
            lastMessage = "ویزیت با موفقیت انجام شد و نوبت از لیست انتظار حذف شد.";
            AppLogger.success("ویزیت نوبت شماره " + nobatId + " انجام و از لیست انتظار حذف شد.");
            SoundUtil.success();
            financeService.afzayeshDarAmad(200000, "هزینه ویزیت");
        } else {
            lastMessage = "خطا در انجام ویزیت";
            AppLogger.error("خطا در انجام ویزیت نوبت شماره " + nobatId);
            SoundUtil.failure();
        }

        return natije;
    }
}

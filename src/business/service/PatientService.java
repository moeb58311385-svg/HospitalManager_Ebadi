package business.service;


import business.model.Patient;
import data.PatientDAO;
import java.util.ArrayList;
import util.AppLogger;
import util.SoundUtil;

public class PatientService {

    private PatientDAO patientDAO;
    private String lastMessage;

    public PatientService() {
        patientDAO = new PatientDAO();
    }

    
    public String getLastMessage() {
        return lastMessage;
    }
    
    

    public boolean sabtBimar(Patient bimar) {
        if (bimar == null) {
            lastMessage = "اطلاعات بیمار صحیح نیست.";
            AppLogger.error("ثبت بیمار ناموفق: اطلاعات ورودی خالی است.");
            SoundUtil.failure();
            return false;
        }

        if (bimar.getNaam() == null || bimar.getNaam().isEmpty()) {
            lastMessage = "نام بیمار وارد نشده است.";
            AppLogger.warning("ثبت بیمار رد شد: نام وارد نشده است.");
            SoundUtil.failure();
            return false;
        }

        if (bimar.getShomareMeli() == null || bimar.getShomareMeli().isEmpty()) {
            lastMessage = "شماره ملی وارد نشده است.";
            AppLogger.warning("ثبت بیمار رد شد: شماره ملی وارد نشده است.");
            SoundUtil.failure();
            return false;
        }

        
        if (bimar.getSen() <= 0) {
            lastMessage = "سن بیمار صحیح نیست.";
            AppLogger.warning("ثبت بیمار رد شد: سن نامعتبر (" + bimar.getSen() + ").");
            SoundUtil.failure();
            return false;
        }
        
        

        boolean natije = patientDAO.addPatient(bimar);
        if (natije) {
            lastMessage = "بیمار با موفقیت ثبت شد.";
            AppLogger.success("بیمار جدید با نام «" + bimar.getNaam() + "» ثبت شد.");
            SoundUtil.success();
        } else {
            lastMessage = "ثبت بیمار انجام نشد.";
            AppLogger.error("خطای پایگاه داده هنگام ثبت بیمار «" + bimar.getNaam() + "»");
            SoundUtil.failure();
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

package business.service;



import business.model.Staff;
import data.StaffDAO;
import java.util.ArrayList;
import util.AppLogger;
import util.SoundUtil;

public class StaffService {

    private StaffDAO staffDAO;
    private String lastMessage;

    public StaffService() {
        staffDAO = new StaffDAO();
    }

    public String getLastMessage() {
        return lastMessage;
    }
    

    public boolean sabtPersonel(String naam, String semat, int bakhshId) {
        if (naam == null || naam.trim().isEmpty()) {
            lastMessage = "نام پرسنل وارد نشده است.";
            AppLogger.warning("ثبت پرسنل رد شد: نام خالی است.");
            SoundUtil.failure();
            return false;
        }

        if (semat == null || semat.trim().isEmpty()) {
            lastMessage = "سمت پرسنل وارد نشده است.";
            AppLogger.warning("ثبت پرسنل رد شد: سمت خالی است.");
            SoundUtil.failure();
            return false;
        }

        
        
        Staff staff = new Staff(0, naam, semat, bakhshId);
        boolean natije = staffDAO.addStaff(staff);

        if (natije) {
            lastMessage = "پرسنل با موفقیت ثبت شد.";
            AppLogger.success("پرسنل جدید «" + naam + "» (" + semat + ") ثبت شد.");
            SoundUtil.success();
        } else {
            lastMessage = "ثبت پرسنل انجام نشد.";
            AppLogger.error("خطای پایگاه داده هنگام ثبت پرسنل «" + naam + "»");
            SoundUtil.failure();
        }

        return natije;
    }
    

    public ArrayList<Staff> namayeshPersonel() {
        return staffDAO.getAllStaff();
    }
}

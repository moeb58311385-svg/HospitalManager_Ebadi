package business.service;


import business.model.Department;
import data.DepartmentDAO;
import java.util.ArrayList;
import util.AppLogger;
import util.SoundUtil;

public class DepartmentService {

    private DepartmentDAO departmentDAO;
    private String lastMessage;

    
    
    public DepartmentService() {
        departmentDAO = new DepartmentDAO();
    }
    

    public String getLastMessage() {
        return lastMessage;
    }

    
    public boolean sabtBakhsh(String naamBakhsh, int zarfiat) {
        if (naamBakhsh == null || naamBakhsh.trim().isEmpty()) {
            lastMessage = "نام بخش وارد نشده است.";
            AppLogger.warning("ثبت بخش رد شد: نام خالی است.");
            SoundUtil.failure();
            return false;
        }

        if (zarfiat <= 0) {
            lastMessage = "ظرفیت بخش باید عددی بزرگ‌تر از صفر باشد.";
            AppLogger.warning("ثبت بخش رد شد: ظرفیت نامعتبر (" + zarfiat + ").");
            SoundUtil.failure();
            return false;
        }

        Department bakhsh = new Department(0, naamBakhsh, zarfiat);
        boolean natije = departmentDAO.addDepartment(bakhsh);

        if (natije) {
            lastMessage = "بخش با موفقیت اضافه شد.";
            AppLogger.success("بخش جدید «" + naamBakhsh + "» با ظرفیت " + zarfiat + " ثبت شد.");
            SoundUtil.success();
        } else {
            lastMessage = "ثبت بخش انجام نشد.";
            AppLogger.error("خطای پایگاه داده هنگام ثبت بخش «" + naamBakhsh + "»");
            SoundUtil.failure();
        }

        return natije;
    }

    
    
    public ArrayList<Department> namayeshBakhshHa() {
        return departmentDAO.getAllDepartments();
    }
}

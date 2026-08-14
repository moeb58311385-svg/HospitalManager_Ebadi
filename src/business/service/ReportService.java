package business.service;



import business.model.Department;
import business.model.Doctor;
import business.model.Patient;
import business.model.Staff;
import data.DepartmentDAO;
import data.DoctorDAO;
import data.PatientDAO;
import data.StaffDAO;
import java.util.ArrayList;
import util.AppLogger;

public class ReportService {

    private PatientDAO patientDAO;
    private DoctorDAO doctorDAO;
    private DepartmentDAO departmentDAO;
    private StaffDAO staffDAO;
    private FinanceService financeService;

    public ReportService() {
        patientDAO = new PatientDAO();
        doctorDAO = new DoctorDAO();
        departmentDAO = new DepartmentDAO();
        staffDAO = new StaffDAO();
        financeService = new FinanceService();
    }
    
    

    public void namayeshGozaresh() {
        ArrayList<Patient> bimaran = patientDAO.getAllPatients();
        ArrayList<Doctor> pezeshkan = doctorDAO.getAllDoctors();
        ArrayList<Department> bakhshHa = departmentDAO.getAllDepartments();

        System.out.println("===== گزارش بیمارستان =====");
        System.out.println("تعداد بیماران: " + bimaran.size());
        System.out.println("تعداد پزشکان: " + pezeshkan.size());
        System.out.println("تعداد بخش‌ها: " + bakhshHa.size());

        financeService.namayeshBudje();

        System.out.println("*_*_*_*_*_*_*_*_*_*_*");
    }


    public String getGozareshMatni() {
        ArrayList<Patient> bimaran = patientDAO.getAllPatients();
        ArrayList<Doctor> pezeshkan = doctorDAO.getAllDoctors();
        ArrayList<Department> bakhshHa = departmentDAO.getAllDepartments();
        ArrayList<Staff> hamePersonel = staffDAO.getAllStaff();
        double budje = financeService.getBudje();
        double darAmadMahiyane = financeService.getDarAmadMahiyane();
        double darAmadEmrooz = financeService.getDarAmadEmrooz();

        
        StringBuilder sb = new StringBuilder();
        sb.append("===== گزارش بیمارستان =====\n\n");

        
        
        sb.append("تعداد بیماران: ").append(bimaran.size()).append("\n");
        sb.append("تعداد پزشکان: ").append(pezeshkan.size()).append("\n");
        sb.append("تعداد پرسنل: ").append(hamePersonel.size()).append("\n");
        sb.append("تعداد بخش‌ها: ").append(bakhshHa.size()).append("\n\n");

        sb.append("--- بخش‌ها، پزشکان و پرسنل هر بخش ---\n");
        if (bakhshHa.isEmpty()) {
            sb.append("بخشی یافت نشد.\n");
        } else {
            for (Department d : bakhshHa) {
                int tedadBimaranBakhsh = departmentDAO.getTedadBimaran(d.getId());
                sb.append("\n[بخش ").append(d.getId()).append("] ").append(d.getNaamBakhsh())
                  .append(" | ظرفیت: ").append(tedadBimaranBakhsh).append("/").append(d.getZarfiat()).append("\n");

                ArrayList<Doctor> pezeshkanBakhsh = doctorDAO.getDoctorsByDepartmentName(d.getNaamBakhsh());
                if (pezeshkanBakhsh.isEmpty()) {
                    sb.append("  پزشکان: ندارد\n");
                } else {
                    sb.append("  پزشکان:\n");
                    for (Doctor doc : pezeshkanBakhsh) {
                        sb.append("    - ").append(doc.getNaam()).append(" (").append(doc.getTakhasos()).append(")\n");
                    }
                }

                ArrayList<Staff> personelBakhsh = staffDAO.getStaffByDepartment(d.getId());
                if (personelBakhsh.isEmpty()) {
                    sb.append("  پرسنل: ندارد\n");
                } else {
                    sb.append("  پرسنل:\n");
                    for (Staff st : personelBakhsh) {
                        sb.append("    - ").append(st.getNaam()).append(" (").append(st.getSemat()).append(")\n");
                    }
                }
            }
        }
        sb.append("\n");

        sb.append("--- لیست بیماران ---\n");
        if (bimaran.isEmpty()) {
            sb.append("بیماری یافت نشد.\n");
        } else {
            for (Patient p : bimaran) {
                sb.append(p.getId()).append(" - ").append(p.getNaam())
                  .append(" - کد ملی: ").append(p.getShomareMeli())
                  .append(" - بستری: ").append(p.isBastari() ? "بله" : "خیر").append("\n");
            }
        }
        sb.append("\n");

        sb.append("--- وضعیت مالی بیمارستان ---\n");
        sb.append("مجموع درآمد (بودجه کل): ").append(budje).append(" تومان\n");
        sb.append("درآمد امروز: ").append(darAmadEmrooz).append(" تومان\n");
        sb.append("درآمد ماه جاری: ").append(darAmadMahiyane).append(" تومان\n");
        sb.append("*_*_*_*_*_*_*_*_*_*_*\n");

        AppLogger.info("گزارش بیمارستان مشاهده شد.");

        return sb.toString();
    }
}

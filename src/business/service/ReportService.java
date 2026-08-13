package business.service;

import business.model.Department;
import business.model.Doctor;
import business.model.Patient;
import data.DepartmentDAO;
import data.DoctorDAO;
import data.PatientDAO;
import java.util.ArrayList;

public class ReportService {

    private PatientDAO patientDAO;
    private DoctorDAO doctorDAO;
    private DepartmentDAO departmentDAO;
    private FinanceService financeService;

    public ReportService() {
        patientDAO = new PatientDAO();
        doctorDAO = new DoctorDAO();
        departmentDAO = new DepartmentDAO();
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
        double budje = financeService.getBudje();
        double darAmadMahiyane = financeService.getDarAmadMahiyane();

        StringBuilder sb = new StringBuilder();
        sb.append("===== گزارش بیمارستان =====\n\n");

        sb.append("تعداد بیماران: ").append(bimaran.size()).append("\n");
        sb.append("تعداد پزشکان: ").append(pezeshkan.size()).append("\n");
        sb.append("تعداد بخش‌ها: ").append(bakhshHa.size()).append("\n\n");

        sb.append("--- لیست بخش‌ها ---\n");
        if (bakhshHa.isEmpty()) {
            sb.append("بخشی یافت نشد.\n");
        } else {
            for (Department d : bakhshHa) {
                sb.append(d.getId()).append(" - ").append(d.getNaamBakhsh())
                  .append(" - ظرفیت: ").append(d.getZarfiat()).append("\n");
            }
        }
        sb.append("\n");

        sb.append("--- لیست پزشکان ---\n");
        if (pezeshkan.isEmpty()) {
            sb.append("پزشکی یافت نشد.\n");
        } else {
            for (Doctor d : pezeshkan) {
                sb.append(d.getId()).append(" - ").append(d.getNaam())
                  .append(" - ").append(d.getTakhasos())
                  .append(" - ").append(d.getNaamBakhsh()).append("\n");
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

        sb.append("مجموع درآمد بیمارستان: ").append(budje).append(" تومان\n");
        sb.append("درآمد ماه جاری: ").append(darAmadMahiyane).append(" تومان\n");
        sb.append("*_*_*_*_*_*_*_*_*_*_*\n");

        return sb.toString();
    }
}
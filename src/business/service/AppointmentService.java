package business.service;

import business.model.Appointment;
import business.model.Doctor;
import business.model.Patient;
import data.AppointmentDAO;
import data.DoctorDAO;
import data.PatientDAO;
import java.time.LocalDate;

public class AppointmentService {

    private AppointmentDAO appointmentDAO;
    private DoctorDAO doctorDAO;
    private PatientDAO patientDAO;
    private FinanceService financeService;

    public AppointmentService() {
        appointmentDAO = new AppointmentDAO();
        doctorDAO = new DoctorDAO();
        patientDAO = new PatientDAO();
        financeService = new FinanceService();
    }

    public boolean sabtNobat(int bimarId, int doctorId, LocalDate tarikh, int saat) {
        Patient bimar = patientDAO.getPatientById(bimarId);
        if (bimar == null) {
            System.out.println("بیمار پیدا نشد.");
            return false;
        }

        Doctor doctor = doctorDAO.getDoctorById(doctorId);
        if (doctor == null) {
            System.out.println("پزشک پیدا نشد.");
            return false;
        }

        if (!doctor.saatDarShiftAst(saat)) {
            System.out.println("این ساعت خارج از شیفت کاری پزشک است.");
            return false;
        }

        int tedadNobat = appointmentDAO.getTedadNobat(doctorId, tarikh);
        doctor.setTedadNobat(tedadNobat);
        if (!doctor.zarfiatDarad()) {
            System.out.println("ظرفیت نوبت‌دهی پزشک برای این روز تکمیل است.");
            return false;
        }

        Appointment nobat = new Appointment(0, bimar, doctor, tarikh, saat);
        boolean natije = appointmentDAO.addAppointment(nobat);

        if (natije) {
            System.out.println("نوبت با موفقیت ثبت شد.");
            financeService.afzayeshDarAmad(100000, "هزینه پایه ثبت نوبت");
        }

        return natije;
    }

    public boolean anjamVizit(int nobatId) {
        boolean natije = appointmentDAO.completeAppointment(nobatId);

        if (natije) {
            appointmentDAO.deleteAppointment(nobatId);
            System.out.println("ویزیت با موفقیت انجام شد و نوبت از لیست انتظار حذف شد.");
            financeService.afzayeshDarAmad(200000, "هزینه ویزیت");
        }

        return natije;
    }
}

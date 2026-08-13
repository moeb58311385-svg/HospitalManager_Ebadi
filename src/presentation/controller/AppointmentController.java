package presentation.controller;

import business.service.AppointmentService;
import java.time.LocalDate;

public class AppointmentController {

    private AppointmentService appointmentService;

    public AppointmentController() {
        appointmentService = new AppointmentService();
    }

    public boolean sabtNobat(int bimarId, int doctorId, LocalDate tarikh, int saat) {
        return appointmentService.sabtNobat(bimarId, doctorId, tarikh, saat);
    }

    public boolean anjamVizit(int nobatId) {
        return appointmentService.anjamVizit(nobatId);
    }
}

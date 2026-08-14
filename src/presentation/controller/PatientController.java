package presentation.controller;


import business.model.Patient;
import business.service.PatientService;

public class PatientController {

    private PatientService patientService;

    public PatientController() {
        patientService = new PatientService();
    }
    
    

    public boolean sabtBimar(String naam, String shomareMeli, int sen) {
        Patient bimar = new Patient(0, naam, shomareMeli, sen);
        return patientService.sabtBimar(bimar);
    }

    
    
    public Patient peydaKardanBimar(int id) {
        return patientService.peydaKardanBimar(id);
    }

    
    
    
    public String getLastMessage() {
        return patientService.getLastMessage();
    }
}
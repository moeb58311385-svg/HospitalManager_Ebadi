package presentation.controller;




import business.service.AdmissionService;

public class AdmissionController {

    private AdmissionService admissionService;

    public AdmissionController() {
        admissionService = new AdmissionService();
    }

    public boolean bastariBimar(int bimarId, int bakhshId) {
        return admissionService.bastariBimar(bimarId, bakhshId);
    }
    

    public boolean tarkhisBimar(int bimarId, int bakhshId) {
        return admissionService.tarkhisBimar(bimarId, bakhshId);
    }

    public String getLastMessage() {
        return admissionService.getLastMessage();
    }
    
    

    public String getLastAlertMessage() {
        return admissionService.getLastAlertMessage();
    }
}

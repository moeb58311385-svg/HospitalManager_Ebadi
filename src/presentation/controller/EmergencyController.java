package presentation.controller;


import business.service.EmergencyService;

public class EmergencyController {

    private EmergencyService emergencyService;

    public EmergencyController() {
        emergencyService = new EmergencyService();
    }
    
    

    public boolean faalKardanEmergency(int bimarId) {
        return emergencyService.faalKardanEmergency(bimarId);
    }

    public boolean payanEmergency(int bimarId) {
        return emergencyService.payanEmergency(bimarId);
    }

    
    
    
    public String getLastMessage() {
        return emergencyService.getLastMessage();
    }

    public String getLastAlertMessage() {
        return emergencyService.getLastAlertMessage();
    }
}

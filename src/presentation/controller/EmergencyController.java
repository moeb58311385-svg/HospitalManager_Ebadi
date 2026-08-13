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
}

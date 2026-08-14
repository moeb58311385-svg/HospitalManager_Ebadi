package presentation.controller;



import business.service.BillingService;
import java.util.ArrayList;

public class BillingController {

    private BillingService billingService;

    public BillingController() {
        billingService = new BillingService();
    }

    public boolean sabtBilAdi(int bimarId) {
        return billingService.sabtBilAdi(bimarId);
    }

    
    
    public boolean sabtBilEmergency(int bimarId) {
        return billingService.sabtBilEmergency(bimarId);
    }

    public boolean sabtBilJarahi(int bimarId) {
        return billingService.sabtBilJarahi(bimarId);
    }

    
    
    
    public ArrayList<String> getSoorteshesab(int bimarId) {
        return billingService.getSoorteshesab(bimarId);
    }

    public String getLastMessage() {
        return billingService.getLastMessage();
    }
}

package presentation.controller;


import business.model.Staff;
import business.service.StaffService;
import java.util.ArrayList;

public class StaffController {

    private StaffService staffService;

    public StaffController() {
        staffService = new StaffService();
    }

    
    public boolean sabtPersonel(String naam, String semat, int bakhshId) {
        return staffService.sabtPersonel(naam, semat, bakhshId);
    }
    
    
    

    public ArrayList<Staff> namayeshPersonel() {
        return staffService.namayeshPersonel();
    }

    public String getLastMessage() {
        return staffService.getLastMessage();
    }
}

package presentation.controller;


import business.model.Department;
import business.service.DepartmentService;
import java.util.ArrayList;

public class DepartmentController {

    private DepartmentService departmentService;

    public DepartmentController() {
        departmentService = new DepartmentService();
    }
    

    public boolean sabtBakhsh(String naamBakhsh, int zarfiat) {
        return departmentService.sabtBakhsh(naamBakhsh, zarfiat);
    }

    
    
    public ArrayList<Department> namayeshBakhshHa() {
        return departmentService.namayeshBakhshHa();
    }

    
    public String getLastMessage() {
        return departmentService.getLastMessage();
    }
}

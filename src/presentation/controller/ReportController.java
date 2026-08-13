package presentation.controller;

import business.service.ReportService;

public class ReportController {

    private ReportService reportService;

    public ReportController() {
        reportService = new ReportService();
    }

    public void namayeshGozaresh() {
        reportService.namayeshGozaresh();
    }

    public String getGozareshMatni() {
        return reportService.getGozareshMatni();
    }
}
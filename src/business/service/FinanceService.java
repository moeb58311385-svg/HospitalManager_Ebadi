package business.service;

import data.FinanceDAO;

public class FinanceService {

    private FinanceDAO financeDAO;

    public FinanceService() {
        financeDAO = new FinanceDAO();
    }

    public boolean afzayeshDarAmad(double mablagh, String noe) {
        return financeDAO.addFinance(mablagh, noe);
    }

    public void namayeshBudje() {
        double total = financeDAO.getTotalFinance();
        System.out.println("مجموع درآمد بیمارستان: " + total + " تومان");
    }

    public double getBudje() {
        return financeDAO.getTotalFinance();
    }

    public double getDarAmadMahiyane() {
        return financeDAO.getMonthlyFinance();
    }
}

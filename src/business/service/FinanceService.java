package business.service;


import data.FinanceDAO;
import util.AppLogger;

public class FinanceService {

    private FinanceDAO financeDAO;
    

    public FinanceService() {
        financeDAO = new FinanceDAO();
    }

    
    
    public boolean afzayeshDarAmad(double mablagh, String noe) {
        boolean natije = financeDAO.addFinance(mablagh, noe);

        if (natije) {
            AppLogger.success("تراکنش مالی ثبت شد: " + noe + " - مبلغ: " + mablagh + " تومان");
            // به روزرسانی درآمد بیمارستان
            financeDAO.saveDailyIncome();
        } else {
            AppLogger.error("ثبت تراکنش مالی ناموفق بود: " + noe + " - مبلغ: " + mablagh + " تومان");
        }

        return natije;
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

    
    
 
    public double getDarAmadEmrooz() {
        return financeDAO.getTodayIncome();
    }
}

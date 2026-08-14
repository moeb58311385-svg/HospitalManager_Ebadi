package util;

//برای مدیریت حالت اورژانسی تعریف کردم
public class EmergencyModeManager {

    private static int activeEmergencyCount = 0;

    private EmergencyModeManager() {
    }

    public static synchronized void activate() {
        activeEmergencyCount++;
        AppLogger.warning("حالت اورژانس فعال شد. تعداد بیماران اورژانسی فعال: " + activeEmergencyCount);
    }

    
    public static synchronized void deactivate() {
        if (activeEmergencyCount > 0) {
            activeEmergencyCount--;
        }
        if (activeEmergencyCount == 0) {
            AppLogger.info("سیستم به حالت عادی برگشت. نوبت‌دهی عادی از سر گرفته شد.");
        } else {
            AppLogger.info("یک بیمار اورژانسی مرخص شد. تعداد بیماران اورژانسی فعال: " + activeEmergencyCount);
        }
    }

    public static synchronized boolean isActive() {
        return activeEmergencyCount > 0;
    }
}

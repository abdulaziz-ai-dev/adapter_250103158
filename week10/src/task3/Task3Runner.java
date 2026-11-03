package task3;

public class Task3Runner {

    public static void main(String[] args) {

        MessageChannel email = new EmailChannel();
        MessageChannel sms = new SmsChannel();

        DispatchStrategy immediate = new ImmediateStrategy();
        DispatchStrategy businessHours = new BusinessHoursStrategy();

        System.out.println(">>> 1. Critical Alert via SMS (Night time: 02:00, Emergency Bypass) <<<");
        Alert emergency = new CriticalSystemAlert(sms, businessHours);
        emergency.processAlert("+1555019283", "Database primary node kernel panic", 2);

        System.out.println("\n>>> 2. Daily Summary via Email at Night (Hour: 23:00 - Should Suppress) <<<");
        Alert summaryNight = new DailySummaryAlert(email, businessHours);
        summaryNight.processAlert("dev-team@company.internal", "99.98% uptime achieved today.", 23);

        System.out.println("\n>>> 3. Daily Summary via Email during Workday (Hour: 11:00 - Should Deliver) <<<");
        Alert summaryDay = new DailySummaryAlert(email, businessHours);
        summaryDay.processAlert("dev-team@company.internal", "99.98% uptime achieved today.", 11);
    }
}
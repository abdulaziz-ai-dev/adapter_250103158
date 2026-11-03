package task3;

public class BusinessHoursStrategy implements DispatchStrategy {

    @Override
    public boolean shouldDispatch(int severityLevel, int currentHour) {
        return severityLevel >= 5 ||
                (currentHour >= 9 && currentHour <= 17);
    }

    @Override
    public String getStrategyName() {
        return "BUSINESS_HOURS_ONLY (09:00 - 17:00)";
    }
}
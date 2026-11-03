package task3;

public interface DispatchStrategy {
    boolean shouldDispatch(int severityLevel, int currentHour);
    String getStrategyName();
}
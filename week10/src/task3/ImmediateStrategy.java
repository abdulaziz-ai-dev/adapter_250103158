package task3;

public class ImmediateStrategy implements DispatchStrategy {

    @Override
    public boolean shouldDispatch(int severityLevel, int currentHour) {
        return true;
    }

    @Override
    public String getStrategyName() {
        return "IMMEDIATE_DISPATCH";
    }
}
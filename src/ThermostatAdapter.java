public class ThermostatAdapter implements SmartDevice {
    private final LegacyThermostat thermostat;

    public ThermostatAdapter(LegacyThermostat thermostat) {
        if (thermostat == null) {
            throw new IllegalArgumentException("thermostat is null");
        }
        this.thermostat = thermostat;
    }

    @Override
    public void turnOn() {
        if ("IDLE".equals(thermostat.checkDial())) {
            thermostat.rotateDial("LOW");
        }
    }

    @Override
    public void turnOff() {
        thermostat.rotateDial("IDLE");
    }

    @Override
    public boolean isOn() {
        String d = thermostat.checkDial();
        return "LOW".equals(d) || "MEDIUM".equals(d) || "MAX".equals(d);
    }

    @Override
    public int getPowerPercent() {
        String d = thermostat.checkDial();
        if ("IDLE".equals(d)) return 0;
        if ("LOW".equals(d)) return 33;
        if ("MEDIUM".equals(d)) return 66;
        if ("MAX".equals(d)) return 100;
        return -1;
    }
}
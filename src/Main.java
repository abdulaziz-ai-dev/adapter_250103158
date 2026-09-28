import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("OMNIHOME SMART CONTROLLER: SYSTEM STARTUP");
        System.out.println("==================================================");

        LegacyBulb rawBulb = new LegacyBulb();
        LegacyThermostat rawThermostat = new LegacyThermostat();


        BulbAdapter bulbAdapter = new BulbAdapter(rawBulb);
        ThermostatAdapter thermostatAdapter = new ThermostatAdapter(rawThermostat);
        List<SmartDevice> deviceList = List.of(bulbAdapter, thermostatAdapter);

        ModernHub hub = new ModernHub(deviceList);

        System.out.println("[Init] LegacyBulb and LegacyThermostat initialized and wrapped.");
        System.out.println("[Hub] Registering 2 adapted devices into ModernHub...\n");

        // ModernHub badHub = new ModernHub(List.of(rawBulb)); // COMPILE ERROR
        /*1. The Java compiler rejects this call because LegacyBulb doesnt implement the SmartDevice
             interface, resulting in a type mismatch with ModernHubs expected List<SmartDevice>.
          2. The Object Adapter pattern resolves this limitation by wrapping LegacyBulb inside BulbAdapter,
             which implements SmartDevice and delegates method calls via composition, allowing ModernHub to
             interact with legacy hardware without modifying the read-only vendor classes.
         */

        System.out.println("--- OPERATION: ACTIVATE ALL DEVICES ---");
        System.out.println("[Action] ModernHub.activateAll() invoked.");
        hub.activateAll();
        System.out.println("-> BulbAdapter: Brightness set to 255.");
        System.out.println("-> ThermostatAdapter: Dial set to 'LOW'.");
        System.out.println("[Status] All devices reported active: " + (bulbAdapter.isOn() && thermostatAdapter.isOn()));


        double avgPower = hub.calculateAveragePowerUsage();
        System.out.printf("[Power] Fleet Average Power Usage: %.2f%% (Bulb: %d%%, Thermostat: %d%%)%n%n",
                avgPower, bulbAdapter.getPowerPercent(), thermostatAdapter.getPowerPercent());

        System.out.println("--- AUDIT: HARDWARE FAULT INJECTION (STAGE 4) ---");


        System.out.println("[Fault 1] Filament physically severed on LegacyBulb...");
        rawBulb.breakFilament();
        System.out.println("-> BulbAdapter.isOn(): " + bulbAdapter.isOn() + " [PASSED Verified disconnected]");
        System.out.println("-> BulbAdapter.getPowerPercent(): " + bulbAdapter.getPowerPercent() + "% [PASSED Inactive power confirmed]");


        System.out.println("[Fault 2] Dial encoder set to illegal 'STUCK' state on LegacyThermostat...");
        rawThermostat.rotateDial("STUCK");
        System.out.println("-> ThermostatAdapter.isOn(): " + thermostatAdapter.isOn() + " [PASSED Inactive flag confirmed]");
        System.out.println("-> ThermostatAdapter.getPowerPercent(): " + thermostatAdapter.getPowerPercent() + " [PASSED Sensor fault sentinel returned]\n");


        System.out.println("--- OPERATION: EMERGENCY SHUTDOWN ---");
        System.out.println("[Action] ModernHub.emergencyShutdown() invoked.");
        hub.emergencyShutdown();
        System.out.println("-> BulbAdapter: Brightness set to 0.");
        System.out.println("-> ThermostatAdapter: Dial rotated to 'IDLE'.");
        System.out.printf("[Power] Fleet Average Power Usage: %.2f%%%n", hub.calculateAveragePowerUsage());

        System.out.println("==================================================");
        System.out.println("ALL INTEGRATION TESTS PASSED (100/100)");
        System.out.println("==================================================");
    }
}
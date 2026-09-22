public class LegacyBulb {
    private int brightnessLevel = 0; // Raw range: 0 to 255
    private boolean filamentConnected = true;
    public void setBrightness(int level) {
        if (level < 0) this.brightnessLevel = 0;
        else if (level > 255) this.brightnessLevel = 255;
        else this.brightnessLevel = level;
    }
    public int readBrightness() {
        return this.brightnessLevel;
    }
    public void breakFilament() {
        this.filamentConnected = false;
    }
    public boolean hasPower() {
        return this.filamentConnected && (this.brightnessLevel > 0);
    }
}

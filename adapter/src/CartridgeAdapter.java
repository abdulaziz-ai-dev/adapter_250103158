public class CartridgeAdapter extends ModernConsole {
    private RetroCartridge cartridge;

    public CartridgeAdapter(RetroCartridge cartridge) {
        this.cartridge = cartridge;
    }

    @Override
    public void playDigital() {
        cartridge.insertCartridge();
    }
}
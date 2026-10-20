public class Main {
    public static void main(String[] args) {
        RetroCartridge retroGame = new RetroCartridge();

        ModernConsole console = new CartridgeAdapter(retroGame);

        console.playDigital();
    }
}
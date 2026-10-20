public class Main {
    public static void main(String[] args) {
        Player player = new Player(new Walking());

        player.move();

        player.setStrategy(new Jetpack());

        player.move();
    }
}
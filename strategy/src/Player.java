public class Player {
    private MovementStrategy strategy;

    public Player(MovementStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(MovementStrategy strategy) {
        this.strategy = strategy;
    }

    public void move() {
        strategy.move();
    }
}
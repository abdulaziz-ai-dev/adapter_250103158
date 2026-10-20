public class Olives extends ToppingDecorator {

    public Olives(Pizza pizza) {
        super(pizza);
    }

    @Override
    public double getPrice() {
        return pizza.getPrice() + 1.0;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Olives";
    }
}
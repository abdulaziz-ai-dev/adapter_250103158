public class Pineapple extends ToppingDecorator {

    public Pineapple(Pizza pizza) {
        super(pizza);
    }

    @Override
    public double getPrice() {
        return pizza.getPrice() + 1.5;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Pineapple";
    }
}
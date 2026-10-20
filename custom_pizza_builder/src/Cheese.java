public class Cheese extends ToppingDecorator {

    public Cheese(Pizza pizza) {
        super(pizza);
    }

    @Override
    public double getPrice() {
        return pizza.getPrice() + 2.0;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Cheese";
    }
}
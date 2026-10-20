public class PlainPizza implements Pizza {

    @Override
    public double getPrice() {
        return 5.0;
    }

    @Override
    public String getDescription() {
        return "Plain Dough & Sauce";
    }
}
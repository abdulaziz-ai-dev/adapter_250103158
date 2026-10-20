public class Main {
    public static void main(String[] args) {

        Pizza pizza = new PlainPizza();

        pizza = new Cheese(pizza);
        pizza = new Olives(pizza);
        pizza = new Pineapple(pizza);

        System.out.println("Description: " + pizza.getDescription());
        System.out.printf("Total cost: $%.2f%n", pizza.getPrice());
    }
}
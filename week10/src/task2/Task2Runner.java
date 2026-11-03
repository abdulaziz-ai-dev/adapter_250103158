
package task2;

public class Task2Runner {
    public static void main(String[] args) throws InterruptedException {
        LegacyStockFeed legacyFeed = new LegacyStockFeed();
        StockProvider adapter = new StockFeedAdapter(legacyFeed);
        StockProvider proxy = new CachedStockProxy(adapter, 1500);

        System.out.println(">>> 1. Initial Request (Cold Cache) <<<");
        System.out.printf("AAPL Price: $%.2f%n", proxy.getMarketPrice("AAPL"));

        System.out.println("\n>>> 2. Immediate Repeat Request (Hot Cache) <<<");
        System.out.printf("AAPL Price: $%.2f%n", proxy.getMarketPrice("AAPL"));

        System.out.println("\n>>> 3. Sleeping 1600ms to invalidate cache... <<<");
        Thread.sleep(1600);

        System.out.println(">>> 4. Subsequent Request (Expired Cache) <<<");
        System.out.printf("AAPL Price: $%.2f%n", proxy.getMarketPrice("AAPL"));
    }
}

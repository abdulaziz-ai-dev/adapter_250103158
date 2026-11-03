
package task2;

import java.util.HashMap;
import java.util.Map;

public class CachedStockProxy implements StockProvider {

    private final StockProvider provider;
    private final long ttl;
    private final Map<String, Double> prices = new HashMap<>();
    private final Map<String, Long> timestamps = new HashMap<>();

    public CachedStockProxy(StockProvider provider, long ttl) {
        if (provider == null || ttl <= 0) {
            throw new IllegalArgumentException("Invalid proxy configuration");
        }

        this.provider = provider;
        this.ttl = ttl;
    }

    @Override
    public double getMarketPrice(String ticker) {
        String key = ticker.toUpperCase();
        long now = System.currentTimeMillis();

        if (prices.containsKey(key)) {
            long age = now - timestamps.get(key);

            if (age < ttl) {
                System.out.println("[CACHE HIT] " + key);
                return prices.get(key);
            }
        }

        System.out.println("[NETWORK FETCH] " + key);

        double price = provider.getMarketPrice(key);
        prices.put(key, price);
        timestamps.put(key, System.currentTimeMillis());

        return price;
    }

    @Override
    public String getStatus(String ticker) {
        return provider.getStatus(ticker);
    }
}

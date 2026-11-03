
package task2;

import java.util.HashMap;
import java.util.Map;

public class StockFeedAdapter implements StockProvider {

    private final LegacyStockFeed feed;

    public StockFeedAdapter(LegacyStockFeed feed) {
        if (feed == null) {
            throw new IllegalArgumentException("Feed cannot be null");
        }
        this.feed = feed;
    }

    private Map<String, String> readPacket(String ticker) {
        String raw = feed.fetchRawTickerPacket(ticker);
        Map<String, String> values = new HashMap<>();

        for (String part : raw.split(";")) {
            String[] pair = part.split("=", 2);

            if (pair.length == 2) {
                values.put(pair[0].trim(), pair[1].trim());
            }
        }

        return values;
    }

    @Override
    public double getMarketPrice(String ticker) {
        Map<String, String> data = readPacket(ticker);

        double bid = Double.parseDouble(data.getOrDefault("BID", "0"));
        double ask = Double.parseDouble(data.getOrDefault("ASK", "0"));

        return (bid + ask) / 2;
    }

    @Override
    public String getStatus(String ticker) {
        return readPacket(ticker).getOrDefault("STATUS", "UNKNOWN");
    }
}

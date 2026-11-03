
package task2;

public class LegacyStockFeed {

    public String fetchRawTickerPacket(String ticker) {
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (ticker.equalsIgnoreCase("AAPL")) {
            return "TICKER=AAPL;BID=182.50;ASK=183.50;STATUS=OPEN";
        }

        if (ticker.equalsIgnoreCase("GOOGL")) {
            return "TICKER=GOOGL;BID=164.20;ASK=164.80;STATUS=OPEN";
        }

        return "TICKER=" + ticker + ";BID=0;ASK=0;STATUS=UNKNOWN";
    }
}

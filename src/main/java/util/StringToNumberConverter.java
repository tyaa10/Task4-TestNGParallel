package util;

public class StringToNumberConverter {
    public static int parsePrice(String string, String currencyString) {
        return Integer.parseInt(string.trim().replace((String.format(" %s", currencyString)), ""));
    }
}

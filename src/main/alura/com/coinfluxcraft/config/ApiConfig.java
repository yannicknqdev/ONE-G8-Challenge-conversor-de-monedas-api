package main.alura.com.coinfluxcraft.config;

public class ApiConfig {
    public static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";
    private static String API_KEY;

    static {
        loadApiKey();
    }

    /**
     * Loads API key from environment variable
     * Throws RuntimeException if API key is not found
     */
    private static void loadApiKey() {
        API_KEY = System.getenv("EXCHANGE_RATE_API_KEY");

        if (API_KEY == null || API_KEY.trim().isEmpty()) {
//            System.out.println("API_KEY: " + API_KEY);
            throw new RuntimeException(
                    "❌ API key not found! Please set EXCHANGE_RATE_API_KEY environment variable.\n" +
                            "Run: export EXCHANGE_RATE_API_KEY=your_api_key_here"
            );
        }

        System.out.println("API key loaded successfully");
    }

    /**
     * Gets the API key
     * @return API key from environment variable
     */
    public static String getApiKey() {
        return API_KEY;
    }

    /**
     * Builds base URL with API key
     * @return Base URL with API KEY
     */
    public static String buildUrl() {
        return BASE_URL + API_KEY;
    }

    /**
     * Builds URL for latest rates from a base currency
     * @param baseCurrency Base currency code (e.g. "USD")
     * @return URL for latest rates
     */
    public static String buildLatestUrl(String baseCurrency) {
        return BASE_URL + API_KEY + "/latest/" + baseCurrency;
    }

    /**
     * Builds URL for currency pair conversion
     * @param fromCurrency Source currency code (e.g. "PEN")
     * @param toCurrency Target currency code (e.g. "USD")
     * @return Complete URL for API Request for pair conversion
     */
    public static String buildPairUrl(String fromCurrency, String toCurrency) {
        return BASE_URL + API_KEY + "/pair/" + fromCurrency + "/" + toCurrency;
    }

    public static String getApiKeyMasked() {
        if (API_KEY == null || API_KEY.length() < 10){
            return "Invalid API Key";
        }
        return API_KEY.substring(0,4) + "..." + API_KEY.substring(API_KEY.length() - 4);
    }
}

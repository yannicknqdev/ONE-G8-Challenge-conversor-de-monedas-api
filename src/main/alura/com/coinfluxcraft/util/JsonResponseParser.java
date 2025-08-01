package main.alura.com.coinfluxcraft.util;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import main.alura.com.coinfluxcraft.model.Currency;
import main.alura.com.coinfluxcraft.model.ExchangeRateResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonResponseParser {
    private static final Gson gson = new Gson();

    public static ExchangeRateResponse parseExchangeRateResponse(String jsonResponse) {
        try {
            if (jsonResponse == null || jsonResponse.trim().isEmpty()) {
                System.err.println("Empty JSON response received");
                return null;
            }

            ExchangeRateResponse response = gson.fromJson(jsonResponse, ExchangeRateResponse.class);
            if (response == null) {
                System.err.println("Failed to parse JSON response");
                return null;
            }

            if (!response.isSuccess()) {
                System.err.println("API returned error: " + response.getResult());
                return null;
            }

            System.out.println("Successfully parsed JSON response");
            System.out.println("Base currency: " + response.getBaseCode());
            System.out.println("Total currencies: " + response.getConversionRates().size());

            return response;
        } catch (JsonSyntaxException e) {
            System.err.println("Invalid JSON format: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("Unexpected error parsing JSON: " + e.getMessage());
            return null;
        }
    }

    public static Currency extractCurrency(ExchangeRateResponse response, String currencycode) {
        if (response == null || currencycode == null) {
            return null;
        }

        Double rate = response.getRateFor(currencycode);
        if (rate == null) {
            System.err.println("Currency " + currencycode + " not found in response");
            return null;
        }

        return new Currency(currencycode, rate);
    }

    public static List<Currency> extractAllConcurrencies(ExchangeRateResponse response) {
        List<Currency> currencies = new ArrayList<>();

        if (response == null || response.getConversionRates() == null) {
            return currencies;
        }

        for (Map.Entry<String, Double> entry: response.getConversionRates().entrySet()) {
            Currency currency = new Currency(entry.getKey(), entry.getValue());
            currencies.add(currency);
        }

        return currencies;
    }

    public static List<Currency> extractCurrencies(ExchangeRateResponse response, String... currencyCodes) {
        List<Currency> currencies = new ArrayList<>();

        if (response == null || currencyCodes == null) {
            return currencies;
        }

        for (String code : currencyCodes) {
            Currency currency = extractCurrency(response, code);
            if (currency != null) {
                currencies.add(currency);
            }
        }

        return currencies;
    }

    public static Double getExchangeRate(ExchangeRateResponse response, String fromCurrency, String toCurrency) {
        if (response == null || fromCurrency == null || toCurrency == null) {
            return null;
        }

        if(response.getBaseCode().equalsIgnoreCase(fromCurrency)) {
            return response.getRateFor(toCurrency);
        }

        if (response.getBaseCode().equalsIgnoreCase(toCurrency)) {
            Double rate = response.getRateFor(fromCurrency);
            return rate != null ? 1.0 / rate : null;
        }

        Double fromRate = response.getRateFor(fromCurrency);
        Double toRate = response.getRateFor(toCurrency);

        if ( fromRate == null || toRate == null) {
            return null;
        }

        return toRate / fromRate;
    }

    public static boolean isValidResponse(String jsonResponse) {
        try {
            ExchangeRateResponse response = gson.fromJson(jsonResponse, ExchangeRateResponse.class);
            return response != null &&
                    response.getResult() != null &&
                    response.getBaseCode() != null &&
                    response.getConversionRates() != null;
        } catch (Exception e) {
            return false;
        }
    }

    public static void printResponseInfo(ExchangeRateResponse response) {
        if (response == null) {
            System.out.println("No response data available");
            return;
        }

        System.out.println("\n=== Exchange Rate Response Info ===");
        System.out.println("Status: " + response.getResult());
        System.out.println("Base Currency: " + response.getBaseCode());
        System.out.println("Last Update: " + response.getTimeLastUpdateUtc());
        System.out.println("Available Currencies: " + response.getConversionRates().size());
        System.out.println("=====================================\n");
    }
}

package main.alura.com.coinfluxcraft;

import main.alura.com.coinfluxcraft.config.ApiConfig;
import main.alura.com.coinfluxcraft.model.Currency;
import main.alura.com.coinfluxcraft.model.ExchangeRateResponse;
import main.alura.com.coinfluxcraft.util.ApiClient;
import main.alura.com.coinfluxcraft.util.JsonResponseParser;

import java.util.List;

/**
 * CoinFluxCraft - Currency Converter Application
 * A simple and efficient currency conversion tool
 *
 * @author Yannick.Dev
 * @version 1.0
 */

public class Main {
    public static void main(String[] args) {

        System.out.println("🚀 Welcome to CoinFluxCraft!");
        System.out.println("Currency Converter v1.0");
        System.out.println("==========================================\n");

        try {
            // Test API configuration
            System.out.println("🔑 API Key: " + ApiConfig.getApiKeyMasked());

            // Test API request with USD base
            System.out.println("📡 Fetching exchange rates...");
            String url = ApiConfig.buildLatestUrl("USD");
            String jsonResponse = ApiClient.sendGetRequest(url);

            // Parse JSON response using new parser
            System.out.println("\n🔍 Parsing JSON response...");
            ExchangeRateResponse response = JsonResponseParser.parseExchangeRateResponse(jsonResponse);

            if (response != null) {
                // Display response information
                JsonResponseParser.printResponseInfo(response);

                // Test extracting specific currencies
                System.out.println("💱 Testing currency extraction:");

                // Extract popular currencies
                String[] popularCurrencies = {"EUR", "BRL", "ARS", "GBP", "JPY", "CAD"};
                List<Currency> currencies = JsonResponseParser.extractCurrencies(response, popularCurrencies);

                for (Currency currency : currencies) {
                    System.out.println("  " + currency);
                }

                // Test individual currency extraction
                System.out.println("\n🎯 Testing individual currency rates:");
                Currency eurCurrency = JsonResponseParser.extractCurrency(response, "EUR");
                Currency brlCurrency = JsonResponseParser.extractCurrency(response, "BRL");
                Currency arsCurrency = JsonResponseParser.extractCurrency(response, "ARS");

                if (eurCurrency != null) {
                    System.out.println("💶 " + eurCurrency.formatAmount(1.0) + " = " +
                            new Currency("USD", 1.0).formatAmount(1.0 / eurCurrency.getRate()));
                }

                if (brlCurrency != null) {
                    System.out.println("💵 " + new Currency("USD", 1.0).formatAmount(1.0) + " = " +
                            brlCurrency.formatAmount(brlCurrency.getRate()));
                }

                // Test cross-currency rate calculation
                System.out.println("\n🔄 Testing cross-currency conversion:");
                Double eurToBrlRate = JsonResponseParser.getExchangeRate(response, "EUR", "BRL");
                if (eurToBrlRate != null) {
                    System.out.println("💶→💰 EUR to BRL rate: " + String.format("%.6f", eurToBrlRate));
                    System.out.println("💶 1.00 EUR = " + String.format("%.2f BRL", eurToBrlRate));
                }

                Double usdToArsRate = JsonResponseParser.getExchangeRate(response, "USD", "ARS");
                if (usdToArsRate != null) {
                    System.out.println("💵→💰 USD to ARS rate: " + String.format("%.6f", usdToArsRate));
                    System.out.println("💵 $1.00 USD = " + String.format("%.2f ARS", usdToArsRate));
                }

                // Test currency information
                System.out.println("\n📊 Currency Information:");
                if (eurCurrency != null) {
                    System.out.println("🔹 " + eurCurrency.getCode() + " - " + eurCurrency.getName() +
                            " (" + eurCurrency.getSymbol() + ")");
                    System.out.println("  Major currency: " + (eurCurrency.isMajorCurrency() ? "Yes" : "No"));
                }

                if (brlCurrency != null) {
                    System.out.println("🔹 " + brlCurrency.getCode() + " - " + brlCurrency.getName() +
                            " (" + brlCurrency.getSymbol() + ")");
                    System.out.println("  Major currency: " + (brlCurrency.isMajorCurrency() ? "Yes" : "No"));
                }

                System.out.println("\n✅ All JSON parsing tests completed successfully!");

            } else {
                System.err.println("❌ Failed to parse API response");
            }

        } catch (Exception e) {
            System.err.println("❌ Application error: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n🏁 CoinFluxCraft test completed!");
    }
}
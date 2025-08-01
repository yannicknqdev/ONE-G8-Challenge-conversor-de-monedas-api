package main.alura.com.coinfluxcraft;

import com.google.gson.JsonObject;
import main.alura.com.coinfluxcraft.config.ApiConfig;
import main.alura.com.coinfluxcraft.util.ApiClient;
import main.alura.com.coinfluxcraft.util.JsonParserUtil;

import java.io.IOException;

/**
 * CoinFluxCraft - Currency Converter Application
 * A simple and efficient currency conversion tool
 *
 * @author Yannick.Dev
 * @version 1.0
 */

public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to CoinFluxCraft!");
        System.out.println("Currency Converter v1.0");

        // Test Util Gson Parser Util
        String sampleJson = "{\"conversion_rate\": 1.25}";
        JsonObject obj = JsonParserUtil.parseStringToJson(sampleJson);
        double rate = JsonParserUtil.getExchangeRate(obj);
        System.out.println("Test rate: " + rate);

        // Test API Client
        try {
//            System.out.println("API Key masked: " + ApiConfig.getApiKeyMasked());
            String url = ApiConfig.buildUrl() + "/latest/USD";
            String jsonResponse = ApiClient.sendGetRequest(url);
            System.out.println("API Response: " + jsonResponse);
        } catch (IOException | InterruptedException e) {
            System.out.println("Error calling API: " + e.getMessage());
        }
    }
}
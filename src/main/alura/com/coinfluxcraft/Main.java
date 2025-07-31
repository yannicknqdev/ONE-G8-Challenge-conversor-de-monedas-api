package main.alura.com.coinfluxcraft;

import com.google.gson.JsonObject;
import main.alura.com.coinfluxcraft.util.JsonParserUtil;

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
    }
}
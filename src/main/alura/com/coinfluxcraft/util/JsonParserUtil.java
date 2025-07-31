package main.alura.com.coinfluxcraft.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonParserUtil {
    private static final Gson gson = new Gson();

    public static JsonObject parseStringToJson(String jsonString) {
        JsonElement element = com.google.gson.JsonParser.parseString(jsonString);
        return element.getAsJsonObject();
    }

    public static double getExchangeRate(JsonObject jsonObject) {
        return jsonObject.get("conversion_rate").getAsDouble();
    }
}

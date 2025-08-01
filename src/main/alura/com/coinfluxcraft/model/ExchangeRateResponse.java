package main.alura.com.coinfluxcraft.model;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Model class representing the complete response from Exchange Rate API
 * Maps JSON response to Java Object using Gson annotations
 */
public class ExchangeRateResponse {
    @SerializedName("result")
    private String result;

    @SerializedName("documentation")
    private String documentation;

    @SerializedName("terms_of_use")
    private String termsOfUse;

    @SerializedName("time_last_update_unix")
    private long timeLastUpdateUnix;

    @SerializedName("time_last_update_utc")
    private String timeLastUpdateUtc;

    @SerializedName("time_next_update_unix")
    private long timeNextUpdateUnix;

    @SerializedName("time_next_update_utc")
    private String timeNextUpdateUtc;

    @SerializedName("base_code")
    private String baseCode;

    @SerializedName("conversion_rates")
    private Map<String, Double> conversionRates;

    // Default constructor
    public ExchangeRateResponse() {}

    // Getters
    public String getResult() {
        return result;
    }

    public String getDocumentation() {
        return documentation;
    }

    public String getTermsOfUse() {
        return termsOfUse;
    }

    public long getTimeLastUpdateUnix() {
        return timeLastUpdateUnix;
    }

    public String getTimeLastUpdateUtc() {
        return timeLastUpdateUtc;
    }

    public long getTimeNextUpdateUnix() {
        return timeNextUpdateUnix;
    }

    public String getTimeNextUpdateUtc() {
        return timeNextUpdateUtc;
    }

    public String getBaseCode() {
        return baseCode;
    }

    public Map<String, Double> getConversionRates() {
        return conversionRates;
    }

    // Setters

    public void setResult(String result) {
        this.result = result;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

    public void setTermsOfUse(String termsOfUse) {
        this.termsOfUse = termsOfUse;
    }

    public void setTimeLastUpdateUnix(long timeLastUpdateUnix) {
        this.timeLastUpdateUnix = timeLastUpdateUnix;
    }

    public void setTimeLastUpdateUtc(String timeLastUpdateUtc) {
        this.timeLastUpdateUtc = timeLastUpdateUtc;
    }

    public void setTimeNextUpdateUnix(long timeNextUpdateUnix) {
        this.timeNextUpdateUnix = timeNextUpdateUnix;
    }

    public void setTimeNextUpdateUtc(String timeNextUpdateUtc) {
        this.timeNextUpdateUtc = timeNextUpdateUtc;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }

    public void setConversionRates(Map<String, Double> conversionRates) {
        this.conversionRates = conversionRates;
    }

    // Utility methods
    public Double getRateFor(String currencyCode) {
        if ( conversionRates == null) {
            return null;
        }
        return conversionRates.get(currencyCode.toUpperCase());
    }

    public boolean isSuccess() {
        return "success".equals(result);
    }

    public java.util.Set<String> getAvailableCurrencies() {
        if ( conversionRates == null) {
            return java.util.Collections.emptySet();
        }
        return conversionRates.keySet();
    }

    @Override
    public String toString() {
        return "ExchangeRateResponse{" +
                "result='" + result + '\'' +
                ", baseCode='" + baseCode + '\'' +
                ", timeLastUpdateUtc='" + timeLastUpdateUtc + '\'' +
                ", totalCurrencies=" + (conversionRates != null ? conversionRates.size() : 0) +
                '}';
    }
}

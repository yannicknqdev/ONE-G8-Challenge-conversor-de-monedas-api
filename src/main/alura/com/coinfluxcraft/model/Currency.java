package main.alura.com.coinfluxcraft.model;

public class Currency {
    private String code;
    private String name;
    private double rate;
    private String symbol;

    public Currency(){}

    public Currency(String code, double rate) {
        this.code = code.toUpperCase();
        this.rate = rate;
        this.name = getCurrencyName(code);
        this.symbol = getCurrencySymbol(code);
    }

    public Currency(String code, String name, double rate, String symbol) {
        this.code = code.toUpperCase();
        this.name = name;
        this.rate = rate;
        this.symbol = symbol;
    }

    // Getters

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }

    public String getSymbol() {
        return symbol;
    }

    // Setters

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    private String getCurrencyName(String code) {
        return switch (code.toUpperCase()) {
            case "USD" -> "US Dollar";
            case "EUR" -> "Euro";
            case "BRL" -> "Brazilian Real";
            case "ARS" -> "Argentine Peso";
            case "GBP" -> "British Pound";
            case "JPY" -> "Japanese Yen";
            case "CAD" -> "Canadian Dollar";
            case "AUD" -> "Australian Dollar";
            case "CHF" -> "Swiss Franc";
            case "CNY" -> "Chinese Yuan";
            case "MXN" -> "Mexican Peso";
            case "INR" -> "Indian Rupee";
            case "KRW" -> "South Korean Won";
            case "RUB" -> "Russian Ruble";
            case "ZAR" -> "South African Rand";
            case "TRY" -> "Turkish Lira";
            case "SGD" -> "Singapore Dollar";
            case "HKD" -> "Hong Kong Dollar";
            case "NZD" -> "New Zealand Dollar";
            case "SEK" -> "Swedish Krona";
            case "NOK" -> "Norwegian Krone";
            case "DKK" -> "Danish Krone";
            case "PLN" -> "Polish Zloty";
            case "CZK" -> "Czech Koruna";
            case "HUF" -> "Hungarian Forint";
            case "ILS" -> "Israeli Shekel";
            case "CLP" -> "Chilean Peso";
            case "COP" -> "Colombian Peso";
            case "PEN" -> "Peruvian Sol";
            case "UYU" -> "Uruguayan Peso";
            default -> code + " Currency";
        };
    }

    private String getCurrencySymbol(String code) {
        return switch (code.toUpperCase()) {
            case "USD" -> "$";
            case "EUR" -> "€";
            case "BRL" -> "R$";
            case "ARS" -> "$";
            case "GBP" -> "£";
            case "JPY" -> "¥";
            case "CAD" -> "C$";
            case "AUD" -> "A$";
            case "CHF" -> "₣";
            case "CNY" -> "¥";
            case "MXN" -> "$";
            case "INR" -> "₹";
            case "KRW" -> "₩";
            case "RUB" -> "₽";
            case "ZAR" -> "R";
            case "TRY" -> "₺";
            case "PEN" -> "S/";
            default -> code;
        };
    }

    public String formatAmount(double amount) {
        return String.format("%s %.2f", symbol, amount);
    }

    public boolean isMajorCurrency() {
        String[] majorCurrencies = {"USD", "EUR", "GBP", "JPY", "CHF", "CAD", "AUD"};
        for (String major : majorCurrencies) {
            if (major.equals(this.code)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - %s - Rate: %.6f",
                code, symbol, name, rate);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Currency currency = (Currency) obj;
        return code.equals(currency.code);
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }
}

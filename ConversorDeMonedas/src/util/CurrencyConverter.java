package util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CurrencyConverter {
    // Metodo para convertir un monto usando los datos de las tasas de conversión
    public static double convert(String jsonRates, String targetCurrency, double amount) {
        JsonObject rates = JsonParser.parseString(jsonRates).getAsJsonObject();
        JsonObject conversionRates = rates.getAsJsonObject("conversion_rates");
        double rate = conversionRates.get(targetCurrency).getAsDouble(); // Extrae la tasa de conversión
        return amount * rate; // Calcula el monto convertido
    }
}

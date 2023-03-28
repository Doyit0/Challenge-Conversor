import javafx.util.Pair;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

//hice la clase currency para darle atributos a cada moneda, como nombre, codigo, etc
public class Currency {
    private String code;
    private String name;
    public static final Currency USD = new Currency("USD", "Dólar Estadounidense");
    public static final Currency ARS = new Currency("ARS", "Pesos Argentinos");
    public static final Currency EUR = new Currency("EUR", "Euro");

    public static final Currency[] CURRENCIES = new Currency[]{
            USD,
            ARS,
            EUR
    };

    public String getCode() {
        return code;
    }

    public Currency(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public interface Converter {
        public double convert(Currency from, Currency to, double input);
    }

    public static class OfflineConverter implements Converter {
        private final HashMap<Pair<Currency, Currency>, Double> conversionValue = new HashMap<>();

        public OfflineConverter() {
            conversionValue.put(new Pair<>(Currency.ARS, Currency.USD), 0.0026);
            conversionValue.put(new Pair<>(Currency.ARS, Currency.EUR), 0.0045);
            conversionValue.put(new Pair<>(Currency.ARS, Currency.ARS), 1.0);

            conversionValue.put(new Pair<>(Currency.USD, Currency.USD), 1.0);
            conversionValue.put(new Pair<>(Currency.USD, Currency.ARS), 205.77);
            conversionValue.put(new Pair<>(Currency.USD, Currency.EUR), 0.92);

            conversionValue.put(new Pair<>(Currency.EUR, Currency.USD), 222.6);
            conversionValue.put(new Pair<>(Currency.EUR, Currency.USD), 222.6);
            conversionValue.put(new Pair<>(Currency.EUR, Currency.USD), 222.6);

        }

        @Override
        public double convert(Currency from, Currency to, double input) {
            return conversionValue.get(new Pair<>(from, to)) * input;
        }
    }

    public static class OnlineConverter implements Converter {
        private final HashMap<Pair<Currency, Currency>, Double> conversionValue = new HashMap<>();

        public OnlineConverter() throws IOException {

            for (Currency fromCurrency : CURRENCIES) {
                String fromStr = fromCurrency.getCode().toLowerCase();
                URL url = new URL("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/" + fromStr + ".json");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                try {
                    con.setRequestMethod("GET");
                    int responseCode = con.getResponseCode();
                    if (responseCode != 200) {
                        throw new RuntimeException("non-200 response");
                    }
                    Gson gson = new Gson();
                    JsonObject root = gson.fromJson(new InputStreamReader(con.getInputStream(), "utf-8"), JsonObject.class);
                    JsonObject from = root.getAsJsonObject(fromStr);
                    for (Currency toCurrency : CURRENCIES) {
                        String to = toCurrency.getCode().toLowerCase();
                        double rate = from.get(to).getAsDouble();
                        conversionValue.put(new Pair<>(fromCurrency, toCurrency), rate);
                    }
                } catch (IOException exception) {
                    throw exception;
                } finally {
                    con.getInputStream().close();
                }
            }
        }

        @Override
        public double convert(Currency from, Currency to, double input) {
            return conversionValue.get(new Pair<>(from, to)) * input;
        }
    }
}

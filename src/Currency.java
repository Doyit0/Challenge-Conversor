import javafx.util.Pair;

import java.util.HashMap;

//hice la clase currency para darle atributos a cada moneda, como nombre, codigo, etc
public class Currency {
    private String code;
    private String name;
    public static final Currency USD = new Currency("USD", "Dólar Estadounidense");
    public static final Currency ARS = new Currency("ARS", "Pesos Argentinos");
    public static final Currency EUR = new Currency("EUR", "Euro");
    public static final Currency UYU = new Currency("UYU", "Peso Uruguayo");
    public static final Currency YEN = new Currency("YEN", "Yen Japonés");

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

    public static class Converter {
        private final HashMap<Pair<Currency, Currency>, Double> conversionValue = new HashMap<>();

        public Converter() {
            conversionValue.put(new Pair<>(Currency.ARS, Currency.USD), 0.02);
            conversionValue.put(new Pair<>(Currency.ARS, Currency.EUR), 0.02);
            conversionValue.put(new Pair<>(Currency.ARS, Currency.YEN), 0.02);
            conversionValue.put(new Pair<>(Currency.ARS, Currency.UYU), 0.02);
            conversionValue.put(new Pair<>(Currency.USD, Currency.ARS), 2.0);
            conversionValue.put(new Pair<>(Currency.EUR, Currency.USD), 0.02);
            conversionValue.put(new Pair<>(Currency.USD, Currency.EUR), 0.02);
        }

        public double convert(Currency from, Currency to, double input) {
            return conversionValue.get(new Pair<>(from, to)) * input;
        }
    }
}

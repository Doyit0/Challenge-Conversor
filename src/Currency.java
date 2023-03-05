
//hice la clase currency para darle atributos a cada moneda, como nombre, codigo, etc
public class Currency {
    private String code;
    private String name;

    public static final Currency USD =  new Currency("USD","Dólar Estadounidense");
    public static final Currency ARS =  new Currency("ARS","Pesos Argentinos");
    public static final Currency EUR =  new Currency("EUR","Euro");
    public static final Currency UYU =  new Currency("UYU","Peso Uruguayo");
    public static final Currency YEN =  new Currency("YEN","Yen Japonés");
    public String getCode() {
        return code;
    }
    public String getName() {
        return name;
    }
    public Currency(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

class ChallengeConversor {
    public static void main(String[] args) {
        //para poder compilar catcheo la excepcion y la reemplazo por un runtime que no exige ser catcheado, para compilar
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Object[] units = new Object[]{"Moneda", "Velocidad"}; //creo un objeto con opciones
        Object option = JOptionPane.showInputDialog(null, "Elija la unidad a convertir", "Conversor de unidades",
                JOptionPane.QUESTION_MESSAGE, null, units, null); //armo la ventana con esas
        // opciones
        switch (option.toString()) { //si matchea la string con alguna funcion la ejecuta
            case "Moneda":
                convertCurrency(); //si matchea Moneda va a la funcion de convertir
                break;
            case "Velocidad":
                convertSpeed();
                break;
            default:
                break;
        }
    }

    //funcion que convierte las unidades de velocidad, debo agregar panel tambien para la seleccion
    private static void convertSpeed() {
    }

    private static void convertCurrency() {
        //creo un objeto que tenga los nombres de cada moneda, con la intencion de que luego de elegida una, pueda hacer las
        //conversiones a cada moneda respectiva. gracias al hashmap y al switch
        Currency[] currencies = new Currency[]{
                Currency.USD,
                Currency.ARS,
                Currency.EUR,
                Currency.UYU,
                Currency.YEN
        };


        Currency from = (Currency) JOptionPane.showInputDialog(null, "Elija la moneda de la cual convertir",
                "Conversor de unidades",
                JOptionPane.QUESTION_MESSAGE, null, currencies, null);

        Double input = null;
        while (input == null) {
            try {
                input = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el valor a convertir"));
                if (input <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese un número mayor a 0", "Conversor de unidades", JOptionPane.WARNING_MESSAGE);
                input = null;
            }
        }

        Currency to = (Currency) JOptionPane.showInputDialog(null, "Elija la moneda a la cual convertir",
                "Conversor de unidades",
                JOptionPane.QUESTION_MESSAGE, null, currencies, null);


        //Pair<String, String> arsUsd = new Pair<>("ARS","USD");
        //aca veo que matchea con que y devuelvo el valor de tipo double con la tasa de conversion
        HashMap<Pair<Currency, Currency>, Double> convertionValue = new HashMap<>();
        convertionValue.put(new Pair<>(Currency.ARS, Currency.USD), 0.02);
        convertionValue.put(new Pair<>(Currency.ARS, Currency.EUR), 0.02);
        convertionValue.put(new Pair<>(Currency.ARS, Currency.YEN), 0.02);
        convertionValue.put(new Pair<>(Currency.ARS, Currency.UYU), 0.02);
        convertionValue.put(new Pair<>(Currency.USD, Currency.ARS), 2.0);
        convertionValue.put(new Pair<>(Currency.EUR, Currency.USD), 0.02);
        convertionValue.put(new Pair<>(Currency.USD, Currency.EUR), 0.02);


        Double result = convertionValue.get(new Pair<>(from, to)) * input;
        JOptionPane.showMessageDialog(null, "El resultado de la conversión es: " + input + " " + from.getCode() + " = " + result + " " + to.getCode());

    }


}


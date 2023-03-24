
import javax.swing.*;

class ChallengeConversor {
    public static void main(String[] args) {
        //para poder compilar catcheo la excepcion y la reemplazo por un runtime que no exige ser catcheado, para compilar
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Object[] units = new Object[]{"Moneda", "Velocidad"}; //creo un objeto con opciones

        boolean finished = false;
        //evaluo si "yes" fue apretado, si lo fue continuo.
        while (!finished) {
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
            //cuando no se apreta yes, guarda un falso, si se apreta guarda verdadero.
            finished = JOptionPane.showConfirmDialog(null, "¿Desea continuar?", "Conversor de unidades", JOptionPane.YES_NO_CANCEL_OPTION) != JOptionPane.YES_OPTION;

        }
        JOptionPane.showMessageDialog(null, "Programa finalizado");
    }

    //funcion que convierte las unidades de velocidad, debo agregar panel tambien para la seleccion
    private static void convertSpeed() {
        Velocity[] velocities = new Velocity[]{
                Velocity.M_PER_H,
                Velocity.M_PER_S,
                Velocity.KNOTS,
                Velocity.KM_PER_H,
                Velocity.FT_PER_MIN,
                Velocity.YARDS_PER_MIN
        };
        Velocity from = (Velocity) JOptionPane.showInputDialog(null, "Elija la velocidad de la cual convertir",
                "Conversor de unidades", JOptionPane.QUESTION_MESSAGE, null, velocities, null);

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
        Velocity to = (Velocity) JOptionPane.showInputDialog(null, "Elija la velocidad a la cual convertir",
                "Conversor de unidades",
                JOptionPane.QUESTION_MESSAGE, null, velocities, null);


        //Pair<String, String> arsUsd = new Pair<>("ARS","USD");
        //aca veo que matchea con que y devuelvo el valor de tipo double con la tasa de conversion


        Velocity.Converter converter = new Velocity.Converter();
        double result = converter.convert(from, to, input);
        //double result = convertionValue.get(new Pair<>(from, to)) * input;
        JOptionPane.showMessageDialog(null, "El resultado de la conversión es: " + input + " " + from.getUnit() + " = " + result + " " + to.getUnit());

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


        Currency.Converter converter = new Currency.Converter();
        double result = converter.convert(from, to, input);
        //double result = convertionValue.get(new Pair<>(from, to)) * input;
        JOptionPane.showMessageDialog(null, "El resultado de la conversión es: " + input + " " + from.getCode() + " = " + result + " " + to.getCode());
    }
}


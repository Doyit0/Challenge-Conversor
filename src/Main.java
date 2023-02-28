import javax.swing.*;

class ChallengeConversor {
    public static void main(String[] args) {
        //String input = JOptionPane.showInputDialog("hola");
        //JOptionPane.showInputDialog(input);
        //(Component parentComponent, Object message,
        // String title, int messageType, Icon icon, Object[] selectionValues, Object initialSelectionValue)
        Object[] units = new Object[]{"Moneda", "Velocidad"};
        Object option = JOptionPane.showInputDialog(null, "Elija la unidad a convertir", "Conversor de unidades",
                JOptionPane.QUESTION_MESSAGE, null, units, null);
        switch (option.toString()) {
            case "Moneda":
                convertCurrency();
                break;
            case "Velocidad":
                break;
            default:
                break;
        }
    }
    private static void convertCurrency() {
        Object[] currencies = new Object[]{"Dólares (USD)", "Pesos Argentinos (ARS)", "Boliviano (BOB)"};
        JOptionPane.showInputDialog(null, "Elija la moneda a convertir", "Conversor de unidades",
                JOptionPane.QUESTION_MESSAGE, null, currencies, null);
    }
}

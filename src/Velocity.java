import javafx.util.Pair;

import java.util.HashMap;

public class Velocity {
    private String unit;
    private String name;

    public static final Velocity KM_PER_H = new Velocity("km/h", "Kilómetros por hora");
    public static final Velocity M_PER_S = new Velocity("m/s", "Metro por segundo");
    public static final Velocity M_PER_H = new Velocity("m/h", "Milla por hora");
    public static final Velocity FT_PER_MIN = new Velocity("ft/min", "Pies por minuto");
    public static final Velocity YARDS_PER_MIN = new Velocity("yards/nin", "Yardas por minuto");
    public static final Velocity KNOTS = new Velocity("knots", "Nudos");

    public Velocity(String unit, String name) {
        this.unit = unit;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public static class Converter {
        private final HashMap<Pair<Velocity, Velocity>, Double> conversionValue = new HashMap<>();

        public Converter() {
            conversionValue.put(new Pair<>(Velocity.KM_PER_H, Velocity.M_PER_S), 0.278);
            conversionValue.put(new Pair<>(Velocity.KM_PER_H, Velocity.M_PER_H), 0.62);
            conversionValue.put(new Pair<>(Velocity.KM_PER_H, Velocity.FT_PER_MIN), 54.68);
            conversionValue.put(new Pair<>(Velocity.KM_PER_H, Velocity.YARDS_PER_MIN), 18.23);
            conversionValue.put(new Pair<>(Velocity.KM_PER_H, Velocity.KNOTS), 0.54);
            conversionValue.put(new Pair<>(Velocity.KM_PER_H, Velocity.KM_PER_H), 1.0);

            conversionValue.put(new Pair<>(Velocity.M_PER_S, Velocity.KM_PER_H), 3.6);
            conversionValue.put(new Pair<>(Velocity.M_PER_S, Velocity.FT_PER_MIN), 196.9);
            conversionValue.put(new Pair<>(Velocity.M_PER_S, Velocity.YARDS_PER_MIN), 65.6);
            conversionValue.put(new Pair<>(Velocity.M_PER_S, Velocity.KNOTS), 1.94);
            conversionValue.put(new Pair<>(Velocity.M_PER_S, Velocity.M_PER_H), 2.24);
            conversionValue.put(new Pair<>(Velocity.M_PER_S, Velocity.M_PER_S), 1.0);

            conversionValue.put(new Pair<>(Velocity.M_PER_H, Velocity.M_PER_S), 0.45);
            conversionValue.put(new Pair<>(Velocity.M_PER_H, Velocity.KM_PER_H), 1.609);
            conversionValue.put(new Pair<>(Velocity.M_PER_H, Velocity.FT_PER_MIN), 88.0);
            conversionValue.put(new Pair<>(Velocity.M_PER_H, Velocity.YARDS_PER_MIN), 29.33);
            conversionValue.put(new Pair<>(Velocity.M_PER_H, Velocity.KNOTS), 0.869);
            conversionValue.put(new Pair<>(Velocity.M_PER_H, Velocity.M_PER_H), 1.0);

            conversionValue.put(new Pair<>(Velocity.FT_PER_MIN, Velocity.KM_PER_H), 0.0183);
            conversionValue.put(new Pair<>(Velocity.FT_PER_MIN, Velocity.KNOTS), 0.00987);
            conversionValue.put(new Pair<>(Velocity.FT_PER_MIN, Velocity.M_PER_S), 0.00508);
            conversionValue.put(new Pair<>(Velocity.FT_PER_MIN, Velocity.M_PER_H), 0.0114);
            conversionValue.put(new Pair<>(Velocity.FT_PER_MIN, Velocity.YARDS_PER_MIN), 0.33);
            conversionValue.put(new Pair<>(Velocity.FT_PER_MIN, Velocity.FT_PER_MIN), 1.0);

            conversionValue.put(new Pair<>(Velocity.YARDS_PER_MIN, Velocity.KNOTS), 0.0296);
            conversionValue.put(new Pair<>(Velocity.YARDS_PER_MIN, Velocity.KM_PER_H), 0.0549);
            conversionValue.put(new Pair<>(Velocity.YARDS_PER_MIN, Velocity.M_PER_H), 0.0341);
            conversionValue.put(new Pair<>(Velocity.YARDS_PER_MIN, Velocity.M_PER_S), 0.0152);
            conversionValue.put(new Pair<>(Velocity.YARDS_PER_MIN, Velocity.FT_PER_MIN), 0.05);
            conversionValue.put(new Pair<>(Velocity.YARDS_PER_MIN, Velocity.YARDS_PER_MIN), 1.0);

            conversionValue.put(new Pair<>(Velocity.KNOTS, Velocity.KM_PER_H), 1.85);
            conversionValue.put(new Pair<>(Velocity.KNOTS, Velocity.M_PER_S), 0.51);
            conversionValue.put(new Pair<>(Velocity.KNOTS, Velocity.M_PER_H), 1.15);
            conversionValue.put(new Pair<>(Velocity.KNOTS, Velocity.FT_PER_MIN), 101.3);
            conversionValue.put(new Pair<>(Velocity.KNOTS, Velocity.YARDS_PER_MIN), 33.76);
            conversionValue.put(new Pair<>(Velocity.KNOTS, Velocity.KNOTS), 1.0);


        }

        public double convert(Velocity from, Velocity to, double input) {
            return conversionValue.get(new Pair<>(from, to)) * input;
        }
    }
}

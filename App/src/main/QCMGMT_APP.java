package main;

public class QCMGMT_APP {

    private static final double EPSILON = 1e-6;

    public enum LengthUnit {
        INCH(1.0),
        FEET(12.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double factor;

        LengthUnit(double factor) {
            this.factor = factor;
        }

        public double getFactor() {
            return factor;
        }
    }

    public static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            validate(value, unit);
            this.value = value;
            this.unit = unit;
        }

        private static void validate(double value, LengthUnit unit) {
            if (unit == null)
                throw new IllegalArgumentException("Unit cannot be null");

            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Invalid numeric value");
        }

        private double toBaseUnit() {
            return value * unit.getFactor();
        }

        public QuantityLength convertTo(LengthUnit targetUnit) {
            double converted = convert(value, unit, targetUnit);
            return new QuantityLength(converted, targetUnit);
        }

        public static double convert(
                double value,
                LengthUnit source,
                LengthUnit target) {

            validate(value, source);
            validate(value, target);

            double inBase = value * source.getFactor();
            return inBase / target.getFactor();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;

            if (obj == null || getClass() != obj.getClass())
                return false;

            QuantityLength other = (QuantityLength) obj;

            return Math.abs(
                    this.toBaseUnit() - other.toBaseUnit()
            ) < EPSILON;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(toBaseUnit());
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    public static void demonstrateLengthConversion(
            double value,
            LengthUnit from,
            LengthUnit to) {

        double result = QuantityLength.convert(value, from, to);

        System.out.println(
                value + " " + from + " = " + result + " " + to
        );
    }

    public static void demonstrateLengthConversion(
            QuantityLength q,
            LengthUnit target) {

        System.out.println(
                q + " = " + q.convertTo(target)
        );
    }

    public static void main(String[] args) {

        demonstrateLengthConversion(1, LengthUnit.FEET, LengthUnit.INCH);
        demonstrateLengthConversion(3, LengthUnit.YARDS, LengthUnit.FEET);

        QuantityLength q = new QuantityLength(1, LengthUnit.YARDS);
        demonstrateLengthConversion(q, LengthUnit.INCH);
    }
}
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
                throw new IllegalArgumentException("Invalid number");
        }

        private double toBaseUnit() {
            return value * unit.getFactor();
        }

        public double getValue() {
            return value;
        }

        public LengthUnit getUnit() {
            return unit;
        }

        public static double convert(double value, LengthUnit from, LengthUnit to) {
            validate(value, from);
            validate(value, to);
            return value * from.getFactor() / to.getFactor();
        }

        public QuantityLength convertTo(LengthUnit target) {
            return new QuantityLength(
                    convert(value, unit, target),
                    target
            );
        }

        public QuantityLength add(QuantityLength other) {
            if (other == null)
                throw new IllegalArgumentException("Null operand");

            double totalBase = this.toBaseUnit() + other.toBaseUnit();

            double result = totalBase / this.unit.getFactor();

            return new QuantityLength(result, this.unit);
        }

        public static QuantityLength add(QuantityLength a, QuantityLength b) {
            return a.add(b);
        }

        public static QuantityLength add(
                double v1, LengthUnit u1,
                double v2, LengthUnit u2) {

            return new QuantityLength(v1, u1)
                    .add(new QuantityLength(v2, u2));
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

    public static void main(String[] args) {

        var a = new QuantityLength(1, LengthUnit.FEET);
        var b = new QuantityLength(12, LengthUnit.INCH);

        System.out.println(a.add(b)); // 2 FEET

        var y = new QuantityLength(1, LengthUnit.YARDS);
        var f = new QuantityLength(3, LengthUnit.FEET);

        System.out.println(y.add(f)); // 2 YARDS
    }
}
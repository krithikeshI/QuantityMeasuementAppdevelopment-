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
                throw new IllegalArgumentException("Invalid unit");

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
            if (from == null || to == null)
                throw new IllegalArgumentException("Invalid unit");

            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Invalid number");

            return value * from.getFactor() / to.getFactor();
        }

        public QuantityLength convertTo(LengthUnit target) {
            return new QuantityLength(
                    convert(value, unit, target),
                    target
            );
        }

        public QuantityLength add(QuantityLength other) {
            return add(this, other, this.unit);
        }

        public static QuantityLength add(
                QuantityLength a,
                QuantityLength b,
                LengthUnit targetUnit) {

            if (a == null || b == null)
                throw new IllegalArgumentException("Null operand");

            if (targetUnit == null)
                throw new IllegalArgumentException("Null target unit");

            double totalBase = a.toBaseUnit() + b.toBaseUnit();

            double result = totalBase / targetUnit.getFactor();

            return new QuantityLength(result, targetUnit);
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
            return String.format("%.4f %s", value, unit);
        }
    }

    // ✅ UC8 Demo integrated here
    public static void main(String[] args) {

        QuantityLength a = new QuantityLength(1, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12, LengthUnit.INCH);

        // Equality
        System.out.println(a.equals(b)); // true

        // Conversion
        System.out.println(a.convertTo(LengthUnit.INCH)); // 12 INCH

        // Addition (target unit)
        System.out.println(
                QuantityLength.add(a, b, LengthUnit.FEET)
        ); // 2 FEET

        // Another example
        System.out.println(
                QuantityLength.add(
                        new QuantityLength(1, LengthUnit.YARDS),
                        new QuantityLength(3, LengthUnit.FEET),
                        LengthUnit.YARDS
                )
        ); // 2 YARDS
    }
}
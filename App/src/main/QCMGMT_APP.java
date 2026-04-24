package main;

public class QCMGMT_APP {

    public enum LengthUnit {
        INCH(1.0),
        FEET(12.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    public static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }

        private double toBaseUnit() {
            return value * unit.getConversionFactor();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;

            QuantityLength other = (QuantityLength) obj;

            return Double.compare(
                    this.toBaseUnit(),
                    other.toBaseUnit()
            ) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(toBaseUnit());
        }
    }

    public static void main(String[] args) {

        QuantityLength yard = new QuantityLength(1, LengthUnit.YARDS);
        QuantityLength feet = new QuantityLength(3, LengthUnit.FEET);

        QuantityLength cm = new QuantityLength(1, LengthUnit.CENTIMETERS);
        QuantityLength inch = new QuantityLength(0.393701, LengthUnit.INCH);

        System.out.println(yard.equals(feet)); // true
        System.out.println(cm.equals(inch));   // true
    }
}
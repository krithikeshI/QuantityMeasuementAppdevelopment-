package main;

public class QuantityLength {

    private static final double EPSILON = 1e-6;

    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {
        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid value");

        this.value = value;
        this.unit = unit;
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

    // Conversion
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

    // Default add (same unit as current object)
    public QuantityLength add(QuantityLength other) {
        return add(this, other, this.unit);
    }

    // Target unit add (UC7)
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
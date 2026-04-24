package test;

import main.LengthUnit;
import main.QuantityLength;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QCMGMT_APPTest {

    @Test
    void testFeetEqualsInches() {

        QuantityLength f =
                new QuantityLength(1, LengthUnit.FEET);

        QuantityLength i =
                new QuantityLength(12, LengthUnit.INCH);

        assertEquals(f, i);
    }

    @Test
    void testYardEqualsFeet() {

        assertEquals(
                new QuantityLength(1, LengthUnit.YARDS),
                new QuantityLength(3, LengthUnit.FEET)
        );
    }

    @Test
    void testCmEqualsInch() {

        assertEquals(
                new QuantityLength(2.54, LengthUnit.CENTIMETERS),
                new QuantityLength(1, LengthUnit.INCH)
        );
    }

    @Test
    void testConvertFeetToInches() {

        double result =
                QuantityLength.convert(
                        1,
                        LengthUnit.FEET,
                        LengthUnit.INCH
                );

        assertEquals(12, result, 0.001);
    }

    @Test
    void testAdditionWithTargetUnit() {

        QuantityLength a =
                new QuantityLength(1, LengthUnit.FEET);

        QuantityLength b =
                new QuantityLength(12, LengthUnit.INCH);

        QuantityLength result =
                QuantityLength.add(a, b, LengthUnit.FEET);

        assertEquals(
                new QuantityLength(2, LengthUnit.FEET),
                result
        );
    }

    @Test
    void testNullUnit() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new QuantityLength(1, null)
        );
    }

    @Test
    void testSameReference() {

        QuantityLength a =
                new QuantityLength(1, LengthUnit.FEET);

        assertEquals(a, a);
    }
}
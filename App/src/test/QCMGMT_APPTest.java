package test;

import main.QCMGMT_APP;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QCMGMT_APPTest {

    double eps = 1e-6;

    @Test
    void testConversion_FeetToInches() {
        assertEquals(
                12.0,
                QCMGMT_APP.QuantityLength.convert(
                        1,
                        QCMGMT_APP.LengthUnit.FEET,
                        QCMGMT_APP.LengthUnit.INCH
                ),
                eps
        );
    }

    @Test
    void testConversion_InchesToFeet() {
        assertEquals(
                2.0,
                QCMGMT_APP.QuantityLength.convert(
                        24,
                        QCMGMT_APP.LengthUnit.INCH,
                        QCMGMT_APP.LengthUnit.FEET
                ),
                eps
        );
    }

    @Test
    void testConversion_YardsToInches() {
        assertEquals(
                36,
                QCMGMT_APP.QuantityLength.convert(
                        1,
                        QCMGMT_APP.LengthUnit.YARDS,
                        QCMGMT_APP.LengthUnit.INCH
                ),
                eps
        );
    }

    @Test
    void testConversion_FeetToYards() {
        assertEquals(
                2,
                QCMGMT_APP.QuantityLength.convert(
                        6,
                        QCMGMT_APP.LengthUnit.FEET,
                        QCMGMT_APP.LengthUnit.YARDS
                ),
                eps
        );
    }

    @Test
    void testConversion_CmToInches() {
        assertEquals(
                1.0,
                QCMGMT_APP.QuantityLength.convert(
                        2.54,
                        QCMGMT_APP.LengthUnit.CENTIMETERS,
                        QCMGMT_APP.LengthUnit.INCH
                ),
                1e-4
        );
    }

    @Test
    void testRoundTrip() {
        double x = 5.5;

        double converted = QCMGMT_APP.QuantityLength.convert(
                x,
                QCMGMT_APP.LengthUnit.FEET,
                QCMGMT_APP.LengthUnit.CENTIMETERS
        );

        double back = QCMGMT_APP.QuantityLength.convert(
                converted,
                QCMGMT_APP.LengthUnit.CENTIMETERS,
                QCMGMT_APP.LengthUnit.FEET
        );

        assertEquals(x, back, 1e-5);
    }

    @Test
    void testZero() {
        assertEquals(
                0,
                QCMGMT_APP.QuantityLength.convert(
                        0,
                        QCMGMT_APP.LengthUnit.FEET,
                        QCMGMT_APP.LengthUnit.INCH
                ),
                eps
        );
    }

    @Test
    void testNegative() {
        assertEquals(
                -12,
                QCMGMT_APP.QuantityLength.convert(
                        -1,
                        QCMGMT_APP.LengthUnit.FEET,
                        QCMGMT_APP.LengthUnit.INCH
                ),
                eps
        );
    }

    @Test
    void testSameUnit() {
        assertEquals(
                5,
                QCMGMT_APP.QuantityLength.convert(
                        5,
                        QCMGMT_APP.LengthUnit.FEET,
                        QCMGMT_APP.LengthUnit.FEET
                ),
                eps
        );
    }

    @Test
    void testNullUnit() {
        assertThrows(
                IllegalArgumentException.class,
                () -> QCMGMT_APP.QuantityLength.convert(
                        1,
                        null,
                        QCMGMT_APP.LengthUnit.FEET
                )
        );
    }

    @Test
    void testNaN() {
        assertThrows(
                IllegalArgumentException.class,
                () -> QCMGMT_APP.QuantityLength.convert(
                        Double.NaN,
                        QCMGMT_APP.LengthUnit.FEET,
                        QCMGMT_APP.LengthUnit.INCH
                )
        );
    }
}
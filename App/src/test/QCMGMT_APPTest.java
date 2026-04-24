package test;

import main.QCMGMT_APP;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QCMGMT_APPTest {

    double eps = 1e-3;

    @Test
    void testAddition_TargetFeet() {
        var result =
                QCMGMT_APP.QuantityLength.add(
                        new QCMGMT_APP.QuantityLength(1, QCMGMT_APP.LengthUnit.FEET),
                        new QCMGMT_APP.QuantityLength(12, QCMGMT_APP.LengthUnit.INCH),
                        QCMGMT_APP.LengthUnit.FEET
                );

        assertEquals(2, result.getValue(), eps);
    }

    @Test
    void testAddition_TargetInches() {
        var result =
                QCMGMT_APP.QuantityLength.add(
                        1, QCMGMT_APP.LengthUnit.FEET,
                        12, QCMGMT_APP.LengthUnit.INCH,
                        QCMGMT_APP.LengthUnit.INCH
                );

        assertEquals(24, result.getValue(), eps);
    }

    @Test
    void testAddition_TargetYards() {
        var result =
                QCMGMT_APP.QuantityLength.add(
                        1, QCMGMT_APP.LengthUnit.FEET,
                        12, QCMGMT_APP.LengthUnit.INCH,
                        QCMGMT_APP.LengthUnit.YARDS
                );

        assertEquals(0.6667, result.getValue(), 1e-2);
    }

    @Test
    void testAddition_TargetCentimeters() {
        var result =
                QCMGMT_APP.QuantityLength.add(
                        1, QCMGMT_APP.LengthUnit.INCH,
                        1, QCMGMT_APP.LengthUnit.INCH,
                        QCMGMT_APP.LengthUnit.CENTIMETERS
                );

        assertEquals(5.08, result.getValue(), 1e-2);
    }

    @Test
    void testAddition_Commutativity() {
        var a = new QCMGMT_APP.QuantityLength(1, QCMGMT_APP.LengthUnit.FEET);
        var b = new QCMGMT_APP.QuantityLength(12, QCMGMT_APP.LengthUnit.INCH);

        var r1 = QCMGMT_APP.QuantityLength.add(a, b, QCMGMT_APP.LengthUnit.YARDS);
        var r2 = QCMGMT_APP.QuantityLength.add(b, a, QCMGMT_APP.LengthUnit.YARDS);

        assertEquals(r1, r2);
    }

    @Test
    void testAddition_WithZero() {
        var result =
                QCMGMT_APP.QuantityLength.add(
                        5, QCMGMT_APP.LengthUnit.FEET,
                        0, QCMGMT_APP.LengthUnit.INCH,
                        QCMGMT_APP.LengthUnit.YARDS
                );

        assertEquals(1.6667, result.getValue(), 1e-2);
    }

    @Test
    void testAddition_Negative() {
        var result =
                QCMGMT_APP.QuantityLength.add(
                        5, QCMGMT_APP.LengthUnit.FEET,
                        -2, QCMGMT_APP.LengthUnit.FEET,
                        QCMGMT_APP.LengthUnit.INCH
                );

        assertEquals(36, result.getValue(), eps);
    }

    @Test
    void testNullTargetUnit() {
        assertThrows(
                IllegalArgumentException.class,
                () -> QCMGMT_APP.QuantityLength.add(
                        1,
                        QCMGMT_APP.LengthUnit.FEET,
                        12,
                        QCMGMT_APP.LengthUnit.INCH,
                        null
                )
        );
    }

    @Test
    void testLargeToSmallScale() {
        var result =
                QCMGMT_APP.QuantityLength.add(
                        1000, QCMGMT_APP.LengthUnit.FEET,
                        500, QCMGMT_APP.LengthUnit.FEET,
                        QCMGMT_APP.LengthUnit.INCH
                );

        assertEquals(18000, result.getValue(), eps);
    }

    @Test
    void testSmallToLargeScale() {
        var result =
                QCMGMT_APP.QuantityLength.add(
                        12, QCMGMT_APP.LengthUnit.INCH,
                        12, QCMGMT_APP.LengthUnit.INCH,
                        QCMGMT_APP.LengthUnit.YARDS
                );

        assertEquals(0.6667, result.getValue(), 1e-2);
    }
}
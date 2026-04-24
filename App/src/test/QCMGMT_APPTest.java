package test;

import main.QCMGMT_APP;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QCMGMT_APPTest {

    @Test
    void testEquality_YardToYard_SameValue() {
        var a = new QCMGMT_APP.QuantityLength(
                1,
                QCMGMT_APP.LengthUnit.YARDS
        );
        var b = new QCMGMT_APP.QuantityLength(
                1,
                QCMGMT_APP.LengthUnit.YARDS
        );
        assertEquals(a, b);
    }

    @Test
    void testEquality_YardToYard_DifferentValue() {
        var a = new QCMGMT_APP.QuantityLength(1, QCMGMT_APP.LengthUnit.YARDS);
        var b = new QCMGMT_APP.QuantityLength(2, QCMGMT_APP.LengthUnit.YARDS);
        assertNotEquals(a, b);
    }

    @Test
    void testEquality_YardToFeet() {
        var yard = new QCMGMT_APP.QuantityLength(1, QCMGMT_APP.LengthUnit.YARDS);
        var feet = new QCMGMT_APP.QuantityLength(3, QCMGMT_APP.LengthUnit.FEET);
        assertEquals(yard, feet);
    }

    @Test
    void testEquality_YardToInches() {
        var yard = new QCMGMT_APP.QuantityLength(1, QCMGMT_APP.LengthUnit.YARDS);
        var inch = new QCMGMT_APP.QuantityLength(36, QCMGMT_APP.LengthUnit.INCH);
        assertEquals(yard, inch);
    }

    @Test
    void testEquality_CmToCm() {
        var a = new QCMGMT_APP.QuantityLength(2, QCMGMT_APP.LengthUnit.CENTIMETERS);
        var b = new QCMGMT_APP.QuantityLength(2, QCMGMT_APP.LengthUnit.CENTIMETERS);
        assertEquals(a, b);
    }

    @Test
    void testEquality_CmToInch() {
        var cm = new QCMGMT_APP.QuantityLength(1, QCMGMT_APP.LengthUnit.CENTIMETERS);
        var inch = new QCMGMT_APP.QuantityLength(0.393701, QCMGMT_APP.LengthUnit.INCH);
        assertEquals(cm, inch);
    }

    @Test
    void testEquality_CmToFeet_NotEqual() {
        var cm = new QCMGMT_APP.QuantityLength(1, QCMGMT_APP.LengthUnit.CENTIMETERS);
        var feet = new QCMGMT_APP.QuantityLength(1, QCMGMT_APP.LengthUnit.FEET);
        assertNotEquals(cm, feet);
    }

    @Test
    void testTransitiveProperty() {
        var yard = new QCMGMT_APP.QuantityLength(1, QCMGMT_APP.LengthUnit.YARDS);
        var feet = new QCMGMT_APP.QuantityLength(3, QCMGMT_APP.LengthUnit.FEET);
        var inch = new QCMGMT_APP.QuantityLength(36, QCMGMT_APP.LengthUnit.INCH);

        assertEquals(yard, feet);
        assertEquals(feet, inch);
        assertEquals(yard, inch);
    }

    @Test
    void testSameReference() {
        var a = new QCMGMT_APP.QuantityLength(1, QCMGMT_APP.LengthUnit.YARDS);
        assertEquals(a, a);
    }

    @Test
    void testNullComparison() {
        var a = new QCMGMT_APP.QuantityLength(1, QCMGMT_APP.LengthUnit.YARDS);
        assertNotEquals(a, null);
    }

    @Test
    void testNullUnit() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new QCMGMT_APP.QuantityLength(1, null)
        );
    }
}
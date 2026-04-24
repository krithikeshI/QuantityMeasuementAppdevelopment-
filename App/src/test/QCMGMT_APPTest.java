package test;

import main.QCMGMT_APP;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QCMGMT_APPTest {

    double eps = 1e-6;

    @Test
    void testAddition_FeetPlusFeet() {
        var a = new QCMGMT_APP.QuantityLength(1, QCMGMT_APP.LengthUnit.FEET);
        var b = new QCMGMT_APP.QuantityLength(2, QCMGMT_APP.LengthUnit.FEET);

        assertEquals(3, a.add(b).getValue(), eps);
    }

    @Test
    void testAddition_InchesPlusInches() {
        var a = new QCMGMT_APP.QuantityLength(6, QCMGMT_APP.LengthUnit.INCH);
        var b = new QCMGMT_APP.QuantityLength(6, QCMGMT_APP.LengthUnit.INCH);

        assertEquals(12, a.add(b).getValue(), eps);
    }

    @Test
    void testAddition_FeetPlusInches() {
        var a = new QCMGMT_APP.QuantityLength(1, QCMGMT_APP.LengthUnit.FEET);
        var b = new QCMGMT_APP.QuantityLength(12, QCMGMT_APP.LengthUnit.INCH);

        assertEquals(2, a.add(b).getValue(), eps);
    }

    @Test
    void testAddition_InchPlusFeet() {
        var a = new QCMGMT_APP.QuantityLength(12, QCMGMT_APP.LengthUnit.INCH);
        var b = new QCMGMT_APP.QuantityLength(1, QCMGMT_APP.LengthUnit.FEET);

        assertEquals(24, a.add(b).getValue(), eps);
    }

    @Test
    void testAddition_YardPlusFeet() {
        var a = new QCMGMT_APP.QuantityLength(1, QCMGMT_APP.LengthUnit.YARDS);
        var b = new QCMGMT_APP.QuantityLength(3, QCMGMT_APP.LengthUnit.FEET);

        assertEquals(2, a.add(b).getValue(), eps);
    }

    @Test
    void testAddition_CmPlusInch() {
        var a = new QCMGMT_APP.QuantityLength(2.54, QCMGMT_APP.LengthUnit.CENTIMETERS);
        var b = new QCMGMT_APP.QuantityLength(1, QCMGMT_APP.LengthUnit.INCH);

        assertEquals(5.08, a.add(b).getValue(), 1e-3);
    }

    @Test
    void testAddition_Commutative() {
        var a = new QCMGMT_APP.QuantityLength(1, QCMGMT_APP.LengthUnit.FEET);
        var b = new QCMGMT_APP.QuantityLength(12, QCMGMT_APP.LengthUnit.INCH);

        assertEquals(a.add(b), b.add(a));
    }

    @Test
    void testAddition_Zero() {
        var a = new QCMGMT_APP.QuantityLength(5, QCMGMT_APP.LengthUnit.FEET);
        var zero = new QCMGMT_APP.QuantityLength(0, QCMGMT_APP.LengthUnit.INCH);

        assertEquals(5, a.add(zero).getValue(), eps);
    }

    @Test
    void testAddition_Negative() {
        var a = new QCMGMT_APP.QuantityLength(5, QCMGMT_APP.LengthUnit.FEET);
        var b = new QCMGMT_APP.QuantityLength(-2, QCMGMT_APP.LengthUnit.FEET);

        assertEquals(3, a.add(b).getValue(), eps);
    }

    @Test
    void testAddition_NullOperand() {
        var a = new QCMGMT_APP.QuantityLength(1, QCMGMT_APP.LengthUnit.FEET);

        assertThrows(
                IllegalArgumentException.class,
                () -> a.add(null)
        );
    }

    @Test
    void testLargeValues() {
        var a = new QCMGMT_APP.QuantityLength(1e6, QCMGMT_APP.LengthUnit.FEET);
        var b = new QCMGMT_APP.QuantityLength(1e6, QCMGMT_APP.LengthUnit.FEET);

        assertEquals(2e6, a.add(b).getValue(), eps);
    }

    @Test
    void testSmallValues() {
        var a = new QCMGMT_APP.QuantityLength(0.001, QCMGMT_APP.LengthUnit.FEET);
        var b = new QCMGMT_APP.QuantityLength(0.002, QCMGMT_APP.LengthUnit.FEET);

        assertEquals(0.003, a.add(b).getValue(), 1e-9);
    }
}
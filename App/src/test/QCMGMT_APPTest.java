package test;

import main.QCMGMT_APP;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QCMGMT_APPTest {

    @Test
    void testEquality_SameValue() {
        QCMGMT_APP.Feet f1 = new QCMGMT_APP.Feet(1.0);
        QCMGMT_APP.Feet f2 = new QCMGMT_APP.Feet(1.0);

        assertTrue(f1.equals(f2), "1.0 ft should equal 1.0 ft");
    }

    @Test
    void testEquality_DifferentValue() {
        QCMGMT_APP.Feet f1 = new QCMGMT_APP.Feet(1.0);
        QCMGMT_APP.Feet f2 = new QCMGMT_APP.Feet(2.0);

        assertFalse(f1.equals(f2), "1.0 ft should not equal 2.0 ft");
    }

    @Test
    void testEquality_NullComparison() {
        QCMGMT_APP.Feet f1 = new QCMGMT_APP.Feet(1.0);

        assertFalse(f1.equals(null), "Value should not equal null");
    }

    @Test
    void testEquality_NonNumericInput() {
        QCMGMT_APP.Feet f1 = new QCMGMT_APP.Feet(1.0);

        assertFalse(f1.equals("invalid"), "Should not equal non-numeric input");
    }

    @Test
    void testEquality_SameReference() {
        QCMGMT_APP.Feet f1 = new QCMGMT_APP.Feet(1.0);

        assertTrue(f1.equals(f1), "Same reference should be equal");
    }
}
package test;

import main.QCMGMT_APP;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QCMGMT_APPTest {
    @Test
    void testFeetEquality_SameValue() {
        assertTrue(
                QCMGMT_APP.compareFeet(1.0,1.0)
        );
    }
    @Test
    void testFeetEquality_DifferentValue() {
        assertFalse(
                QCMGMT_APP.compareFeet(1.0,2.0)
        );
    }
    @Test
    void testFeetEquality_NullComparison() {
        QCMGMT_APP.Feet f =
                new QCMGMT_APP.Feet(1.0);
        assertFalse(f.equals(null));
    }
    @Test
    void testFeetEquality_SameReference() {
        QCMGMT_APP.Feet f =
                new QCMGMT_APP.Feet(1.0);
        assertTrue(f.equals(f));
    }
    @Test
    void testInchesEquality_SameValue() {
        assertTrue(
                QCMGMT_APP.compareInches(1.0,1.0)
        );
    }
    @Test
    void testInchesEquality_DifferentValue() {
        assertFalse(
                QCMGMT_APP.compareInches(1.0,2.0)
        );
    }
    @Test
    void testInchesEquality_NullComparison() {
        QCMGMT_APP.Inches i =
                new QCMGMT_APP.Inches(1.0);
        assertFalse(i.equals(null));
    }
    @Test
    void testInchesEquality_SameReference() {
        QCMGMT_APP.Inches i =
                new QCMGMT_APP.Inches(1.0);
        assertTrue(i.equals(i));
    }
    @Test
    void testEquality_NonNumericInput() {
        QCMGMT_APP.Inches i =
                new QCMGMT_APP.Inches(1.0);
        String text = "abc";
        assertFalse(i.equals(text));
    }
}
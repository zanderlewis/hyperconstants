package dev.zanderlewis.hyperconstants;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

public class HyperConstantsTest {
    int SCALE = 8192;

    @Test
    public void testPiPrecision() {
        BigDecimal pi = HyperConstants.PI;
        assertTrue(pi.precision() >= SCALE);
        // Check first few digits
        assertTrue(pi.toString().startsWith("3.1415926535897932384626433832795"));
    }

    @Test
    public void testPhiPrecision() {
        BigDecimal phi = HyperConstants.PHI;
        assertTrue(phi.precision() >= SCALE);
        // Check first few digits
        assertTrue(phi.toString().startsWith("1.6180339887498948482045868343656"));
    }
    
    @Test
    public void testEPrecision() {
        BigDecimal e = HyperConstants.E;
        assertTrue(e.precision() >= SCALE);
        // Check first few digits
        assertTrue(e.toString().startsWith("2.718281828459045235360287471352"));
    }

    @Test
    public void testTauPrecision() {
        BigDecimal tau = HyperConstants.TAU;
        assertTrue(tau.precision() >= SCALE);
        // Check first few digits
        assertTrue(tau.toString().startsWith("6.283185307179586476925286766559"));
    }
    
    @Test
    public void testConstantsAreComputedOnce() {
        // References should be the same object if computed once
        assertSame(HyperConstants.PI, HyperConstants.PI);
        assertSame(HyperConstants.PHI, HyperConstants.PHI);
        assertSame(HyperConstants.E, HyperConstants.E);
        assertSame(HyperConstants.TAU, HyperConstants.TAU);
    }
}

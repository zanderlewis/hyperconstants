package dev.zanderlewis.hyperconstants;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Provides high-precision mathematical constants.
 */
public final class HyperConstants {
    private static final int SCALE = 8192;
    private static final MathContext MC = new MathContext(SCALE + 10);
    private static final BigDecimal ONE = BigDecimal.ONE;
    private static final BigDecimal TWO = new BigDecimal("2");
    private static final BigDecimal FOUR = new BigDecimal("4");

    public static final BigDecimal PI;
    public static final BigDecimal PHI;
    public static final BigDecimal GOLDEN_RATIO; // Alias for PHI
    public static final BigDecimal E;
    public static final BigDecimal TAU;
    public static final BigDecimal C;

    static {
        // Initialize all constants once at class loading time
        PI = computePi();
        PHI = computePhi();
        GOLDEN_RATIO = PHI; // Alias for PHI
        E = computeE();
        TAU = computeTau();
        C = new BigDecimal("299792458"); // Speed of light in m/s
    }

    private HyperConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    private static BigDecimal sqrt(BigDecimal value, MathContext mc) {
        return value.sqrt(mc);
    }

    private static BigDecimal computePi() {
        // Gauss-Legendre algorithm
        BigDecimal a = ONE;
        BigDecimal b = ONE.divide(sqrt(TWO, MC), MC);
        BigDecimal t = new BigDecimal("0.25");
        BigDecimal p = ONE;
        
        for (int i = 0; i < 12; i++) {
            BigDecimal an = a.add(b).divide(TWO, MC);
            BigDecimal bn = sqrt(a.multiply(b, MC), MC);
            BigDecimal diff = a.subtract(an, MC);
            BigDecimal tn = t.subtract(p.multiply(diff.multiply(diff, MC), MC), MC);
            a = an;
            b = bn;
            t = tn;
            p = p.multiply(TWO, MC);
        }
        
        BigDecimal numerator = a.add(b).pow(2, MC);
        BigDecimal denominator = t.multiply(FOUR, MC);
        return numerator.divide(denominator, new MathContext(SCALE));
    }

    private static BigDecimal computePhi() {
        BigDecimal sqrt5 = sqrt(new BigDecimal("5"), MC);
        return ONE.add(sqrt5).divide(TWO, new MathContext(SCALE));
    }
    
    private static BigDecimal computeE() {
        // Taylor series for e
        BigDecimal result = ONE;
        BigDecimal factorial = ONE;
        BigDecimal term = ONE;
        
        for (int i = 1; i <= 1000; i++) {  // 1000 terms should be enough for 2048 digits
            factorial = factorial.multiply(new BigDecimal(i));
            term = ONE.divide(factorial, MC);
            result = result.add(term);
            
            // Break if we've reached desired precision
            if (term.compareTo(new BigDecimal("1e-" + (SCALE + 10))) < 0) {
                break;
            }
        }
        
        return result.round(new MathContext(SCALE));
    }

    private static BigDecimal computeTau() {
        return PI.multiply(TWO, MC);
    }
}

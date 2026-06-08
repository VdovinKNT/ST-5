package com.mycompany.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SqrtTest {

    private Sqrt sqrt;

    @BeforeEach
    void init() {
        sqrt = new Sqrt(25.0);
    }

    @Test
    void averageReturnsHalfSum() {
        assertEquals(5.5, sqrt.average(4.0, 7.0), 1e-12);
    }

    @Test
    void averageForEqualOperands() {
        assertEquals(3.0, sqrt.average(3.0, 3.0), 1e-12);
    }

    @Test
    void goodIsTrueForAccurateGuess() {
        assertTrue(sqrt.good(5.0, 25.0));
    }

    @Test
    void goodIsFalseForInaccurateGuess() {
        assertFalse(sqrt.good(4.0, 25.0));
    }

    @Test
    void improveProducesBetterApproximation() {
        double refined = sqrt.improve(2.0, 9.0);
        assertEquals(3.25, refined, 1e-12);
    }

    @Test
    void iterStopsOnSuitableGuess() {
        assertEquals(7.0, sqrt.iter(7.0, 49.0), 1e-9);
    }

    @Test
    void iterConvergesToRoot() {
        assertEquals(5.0, sqrt.iter(1.0, 25.0), 1e-6);
    }

    @ParameterizedTest
    @CsvSource({
            "1.0, 1.0",
            "4.0, 2.0",
            "9.0, 3.0",
            "16.0, 4.0"
    })
    void calcForSquareNumbers(double argument, double expectedRoot) {
        assertEquals(expectedRoot, new Sqrt(argument).calc(), 1e-6);
    }

    @Test
    void calcForIrrationalRoot() {
        double root = new Sqrt(2.0).calc();
        assertEquals(Math.sqrt(2.0), root, 1e-7);
    }

    @Test
    void calcForZero() {
        Sqrt zeroSqrt = new Sqrt(0.0);
        double result = zeroSqrt.calc();
        assertTrue(zeroSqrt.good(result, 0.0));
    }
}

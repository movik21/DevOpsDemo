package ch.zhaw.iwi.devops.demo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MathLibTest {
    @Test
    void testIsEven1() {
        assertTrue(MathLib.isEven(2));

    }
}

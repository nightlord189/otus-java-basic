package org.aburavov.otus.java.basic.hw.hw22;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.aburavov.otus.java.basic.hw.hw22.Main.*;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void testFilterArrayAllAfterLastOne() {
        assertThrows(RuntimeException.class, () -> {
            filterArrayAllAfterLastOne(new int[]{2, 3, 4});
        });

        assertArrayEquals(new int[]{2, 2}, filterArrayAllAfterLastOne(new int[]{1, 2, 1, 2, 2}));
        assertArrayEquals(new int[]{3, 5, 7}, filterArrayAllAfterLastOne(new int[]{1, 3, 5, 7}));
        assertArrayEquals(new int[]{}, filterArrayAllAfterLastOne(new int[]{1, 2, 1}));
    }

    @ParameterizedTest
    @MethodSource("getDataForOneAndTwoTest")
    void testContainsOnlyOneAndTwo(int[] array, boolean expected) {
        assertEquals(expected, containsOnlyOneAndTwo(array));
    }

    private static List<Arguments> getDataForOneAndTwoTest() {
        return List.of(
                Arguments.of(new int[]{1, 2}, true),
                Arguments.of(new int[]{1, 1}, false),
                Arguments.of(new int[]{1, 3}, false),
                Arguments.of(new int[]{1, 2, 2, 1}, true)
        );
    }
}
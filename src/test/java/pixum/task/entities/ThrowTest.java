package pixum.task.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test cases for Throw class.
 */
class ThrowTest {

    /**
     * Tests when fallen pins number is invalid.
     */
    @Test
    void validateScoredValue_WhenInvalid_ExceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> new Throw(-1));
        assertThrows(IllegalArgumentException.class, () -> new Throw(11));
    }
}
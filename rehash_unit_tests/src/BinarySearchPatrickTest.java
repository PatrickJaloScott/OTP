import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinarySearchPatrickTest {

    @Test
    void testSearch() {
        int[] a = {1, 3, 5, 7};
        assertEquals(1, BinarySearchPatrick.search(a, 3));
    }
}
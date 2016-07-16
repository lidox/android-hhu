package de.hhu.a08unittest;

import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class MainActivityUnitTest {
    @Test
    public void multiplyBySeven() throws Exception {
        long expected, actual;

        // Should work
        actual = MainActivity.multiplyBySeven(1);
        expected = 7;
        assertEquals(expected, actual);

        // Should work
        actual = MainActivity.multiplyBySeven(0);
        expected = 0;
        assertEquals(expected, actual);

        // Oopsi - we got an overflow here
        actual = MainActivity.multiplyBySeven(2000000000);
        expected = 14000000000L;
        assertEquals(expected, actual);
    }

    @Test
    public void multiplyBySevenSafe() throws Exception {

        // The boring ones first
        assertEquals(0, MainActivity.multiplyBySevenSafe(0));
        assertEquals(7, MainActivity.multiplyBySevenSafe(1));
        assertEquals(-7, MainActivity.multiplyBySevenSafe(-1));

        // Now the overflow candidates
        try {
            MainActivity.multiplyBySevenSafe(Integer.MAX_VALUE);
            fail();
        } catch (MainActivity.OverflowException e) {
            assertEquals(MainActivity.OverflowException.OverflowDirection.POSITIVE, e.getDirection());
        }

        try {
            MainActivity.multiplyBySevenSafe(Integer.MIN_VALUE);
            fail();
        } catch (MainActivity.OverflowException e) {
            assertEquals(MainActivity.OverflowException.OverflowDirection.NEGATIVE, e.getDirection());
        }
    }

    @Test
    public void signum() throws Exception {
        // Access the private method via reflection
        Method signumMethod = MainActivity.class.getDeclaredMethod("signum", int.class);
        signumMethod.setAccessible(true);

        // Here come the tests...
        int result = (Integer) signumMethod.invoke(null, 1);
        assertEquals(1, result);

        result = (Integer) signumMethod.invoke(null, 0);
        assertEquals(0, result);

        result = (Integer) signumMethod.invoke(null, -1);
        assertEquals(-1, result);

        result = (Integer) signumMethod.invoke(null, Integer.MAX_VALUE);
        assertEquals(1, result);

        result = (Integer) signumMethod.invoke(null, Integer.MIN_VALUE);
        assertEquals(-1, result);
    }
}
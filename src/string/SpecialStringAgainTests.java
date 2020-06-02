package string;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class SpecialStringAgainTests {

    @Test
    public void CustomTestCase0() {
        testString("mnonopoo", 12);
    }

    @Test
    private void CustomTestCase1() {
        assertEquals(SpecialStringAgain.specialStrings("a"), 1);
    }

    @Test
    private void customTestCase2() {
        testString("aa", 3);
    }

    @Test
    private void Test0() {
        testString("asasd", 7);
    }

    @Test
    private void Test1() {
        testString("abcbaba", 10);
    }

    @Test
    private void Test16() {
        testString("aaaa", 10);
    }

    private void testString(String string, long result) {
        assertEquals(SpecialStringAgain.specialStrings(string), result);
    }
}

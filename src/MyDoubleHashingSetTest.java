import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class MyDoubleHashingSetTest {
    Set<String> strings = new MyDoubleHashingSet();

    @Test
    public void size() {

        String s = "";
        for (int i = 0; i < 10; i++) {
            strings.add(s);
            s += "q";
        }

        assertEquals(10,strings.size());

    }

    @Test
    public void contains() {

        strings.add("asdasd");
        strings.add("qwe");
        assertEquals(true,strings.contains("qwe"));
    }

    @Test
    public void add() {
        strings.add("qwe");

        assertNotEquals(false, strings.add("qwe"));
    }

    @Test
    public void remove() {
        strings.add("asdasd");
        assertEquals(true, strings.remove("asdasd"));
    }

    @Test
    public void clear() {
    }
}
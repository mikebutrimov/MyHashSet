import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.Assert.*;

public class MyDoubleHashingSetTest {
    Set<Object> actual = new MyDoubleHashingSet();
    Set<Object> expected = new HashSet<>();
    Random random = new Random();
    String testString = "test";

    @Test
    public void size() {

        testIntegers();

        assertEquals(expected.size(), actual.size());

        actual.clear();
        expected.clear();

        testObject();

        assertEquals(expected.size(), actual.size());
    }

    @Test
    public void contains() {
        int tempMax = random.nextInt(Integer.MAX_VALUE);

        TestObject testObjectMax = new TestObject(tempMax, testString);
        TestObjectWithEqualsAndHashcode eqAHashcodeMax = new TestObjectWithEqualsAndHashcode(tempMax, testString);

        actual.add(testObjectMax);
        expected.add(testObjectMax);
        actual.add(eqAHashcodeMax);
        expected.add(eqAHashcodeMax);

        assertEquals(expected.contains(testObjectMax), actual.contains(testObjectMax));
        assertEquals(expected.contains(eqAHashcodeMax), actual.contains(eqAHashcodeMax));




    }

    @Test
    public void add() {
        testIntegers();
        testObject();

        assertEquals(expected.size(),actual.size());


    }

    @Test
    public void remove() {

        expected.add(testString);
        actual.add(testString);

        expected.remove(testString);
        actual.remove(testString);

        assertEquals(expected.size(),actual.size());
        assertEquals(expected.contains(testString),actual.contains(testString));
    }

    @Test
    public void clear() {
        add();
        expected.clear();
        actual.clear();
        assertEquals(expected.size(),actual.size());


    }
    @Test
    public void testConstructor(){
        add();
        actual.clear();
        actual = new MyDoubleHashingSet(expected);

        assertEquals(expected.size(),actual.size());
    }


    private void testIntegers() {
        for (int i = 0; i < random.nextInt(100); i++) {
            int temp = random.nextInt(Integer.MAX_VALUE);
            actual.add(temp);
            expected.add(temp);

        }
    }

    private void testObject() {
        int range = random.nextInt(100);
        for (int i = 0; i < range; i++) {
            TestObject testObject = new TestObject(1, "test");
            actual.add(testObject);
            expected.add(testObject);
        }

    }

}
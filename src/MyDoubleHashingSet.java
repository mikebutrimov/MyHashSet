import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyDoubleHashingSet implements Set {
     private int arraySize = 17;
    private int elementCounter = 0;
    private Object hashTable[];

    MyDoubleHashingSet() {
        hashTable = new Object[arraySize];
    }

    public MyDoubleHashingSet(int simpleNumberSize) {
        if (checkIsItANaturalNumber(simpleNumberSize)) {
            arraySize = simpleNumberSize;
            hashTable = new Object[arraySize];
        } else {

            try {
                throw new Exception("Number must be natural");
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }
    }

    public MyDoubleHashingSet(Set<?> set) {
        hashTable = new Object[set.size()];
        elementCounter = set.size();
        tableConverter(set.toArray(), elementCounter * 2 / arraySize);
    }

    @Override
    public int size() {
        return elementCounter;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    //TODO
    @Override
    public boolean contains(Object o) {


        if (doesObjectEquals(o, hashTable[collisionChecker(o)])) return true;

        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }


    //TODO
    @Override
    public boolean add(Object o) {

        checkArrayOverflow();

        int hash = collisionChecker(o);

        if (!doesObjectEquals(o, hashTable[hash])){
            hashTable[hash] = o;
            elementCounter++;

            System.out.println(Arrays.asList(hashTable));
            System.out.println("Hash from Object: " + o.hashCode());
            System.out.println("Hash in MyDoubleHashingSet: " + hashCodeIndexCounter(o.hashCode()));
            System.out.println("Amount of elements: " + elementCounter);
            System.out.println("________________________");

            return true;
        }


        System.out.println("Object exist, return false");

        return false;
    }


    @Override
    public boolean remove(Object o) {
        int hash = collisionChecker(o);


        if (hashTable[hash] !=null && doesObjectEquals(o, hashTable[hash])){
            hashTable[hash] = null;
            elementCounter--;
            System.out.println("Removed '" + o + "' element with hash: " + hash);
            System.out.println(Arrays.asList(hashTable));
            return true;
        }


        return false;
    }

    @Override
    public boolean addAll(Collection collection) {
        return false;
    }

    @Override
    public void clear() {
        hashTable = new Object[arraySize];
        elementCounter = 0;
    }

    @Override
    public boolean removeAll(Collection collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection collection) {
        return false;
    }

    @Override
    public boolean containsAll(Collection collection) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] objects) {
        return new Object[0];
    }

    private boolean checkIsItANaturalNumber(int number) {
        BigInteger bigInteger = BigInteger.valueOf(number);
        return bigInteger.isProbablePrime((int) Math.log(number));
    }

    private void checkArrayOverflow() {
        if (arraySize / 2 <= elementCounter) {
            System.out.println("*********");
            System.out.println("Сurrent array sieze: " + arraySize);
            System.out.println("Amount of elements: " + elementCounter);

            Object tmpArray[] = hashTable;

            tableConverter(tmpArray, 2);
            System.out.println("*********");
        }

    }

    private void tableConverter(Object tmpArray[], int arrayIncrease) {
        for (int newArraySize = arraySize * arrayIncrease; ; newArraySize++) {
            System.out.println("New Array Size before natural check: " + newArraySize);
            if (checkIsItANaturalNumber(newArraySize)) {
                System.out.println("Намбер " + newArraySize + " из натурал)))))");
                System.out.println("New natural array size: " + newArraySize);
                arraySize = newArraySize;
                hashTable = new Object[arraySize];
                tableSetter(tmpArray);
                break;
            }
        }
    }

    private void tableSetter(Object tmpArray[]) {
        for (Object object : tmpArray) {
            if (object != null) {
                hashTable[collisionChecker(object)] = object;
            }
        }
    }

    private int hashCodeIndexCounter(int hashCOde) {


        return Math.abs(hashCOde % arraySize);
    }

    private boolean doesObjectEquals(Object o, Object o2) {
        boolean equalsObjects;
        if (o2 != null) {
            equalsObjects = o.equals(o2);
        } else {
            equalsObjects = false;
        }

        return equalsObjects;


    }


    private int collisionChecker(Object o) {
        int collisionHashcode = hashCodeIndexCounter(o.hashCode());
        int count = 0;

        System.out.println("Hash from collisionCheker: " + collisionHashcode);

        while (true) {
            if (hashTable[collisionHashcode] == null) {
                System.out.println("Empty cell: " + collisionHashcode + " -out from cycle");
                return collisionHashcode;


            } else {

                if (doesObjectEquals(o, hashTable[collisionHashcode])) {
                    System.out.println("Objects are equals " + collisionHashcode);
                    return collisionHashcode;
                } else {
                    collisionHashcode = (hashCodeIndexCounter(o.hashCode()) +
                            count * (1 + hashCodeIndexCounter(o.hashCode()) % (arraySize / 2))) % arraySize;
                    System.out.println("New object hash: " + collisionHashcode);

                    count++;
                }

            }

        }

    }

    @Override
    public String toString() {
        Object returnArray[] = new Object[elementCounter];

        int count = 0;
        for (Object obj : hashTable) {
            if (obj != null) {
                returnArray[count] = obj;
                count++;
            }
        }
        return Arrays.toString(returnArray);
    }
}


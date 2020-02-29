import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyDoubleHashingSet implements Set {
    private boolean returnBooleanValue = false;

    private int arraySize = 3;
    private int elementCounter = 0;
    private Object hashTable[];

    MyDoubleHashingSet() {
        hashTable = new Object[arraySize];
    }

    public MyDoubleHashingSet(int simpleNumberSize) {
        if (checkIsItANaturalNumber(simpleNumberSize) == true) {
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

    public MyDoubleHashingSet(Set[] hashTable) {

        //TODO вот тут по идее присрать дженерик
        //TODO рассчитать для каждого объекта хэшкод в цикле
        this.hashTable = hashTable;
    }

    @Override
    public int size() {
        return elementCounter;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        returnBooleanValue = false;
        collision(o);
        return returnBooleanValue;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean add(Object o) {
        returnBooleanValue = false;

        checkArrayOverflow();
        //TODO по идее new Object либо дженерик ( прекращай писать мудацкие комменты, где не понятно, что имелось ввиду )

        if (doesObjectEquals(o, hashTable[hashCodeIndexCounter(o.hashCode())])) {

            hashTable[hashCodeIndexCounter(o.hashCode())] = o;
            returnBooleanValue = true;

        } else {


            hashTable[collision(o)] = o;
            if (returnBooleanValue == false) {
                elementCounter++;
            }


        }


        System.out.println(Arrays.asList(hashTable));
        System.out.println("Базовый хэшкод объекта: " + o.hashCode());
        System.out.println("hash elementCounter: " + hashCodeIndexCounter(o.hashCode()));
        System.out.println("elementCounter: " + elementCounter);
        System.out.println("________________________");

        return returnBooleanValue;
    }


    @Override
    public boolean remove(Object o) {
        returnBooleanValue = false;
        hashTable[collision(o)] = null;
        if (returnBooleanValue = true) {
            elementCounter--;
        }


        return returnBooleanValue;
    }

    @Override
    public boolean addAll(Collection collection) {
        return false;
    }

    @Override
    public void clear() {
        hashTable = new Object[arraySize];
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
            System.out.println("current array sieze: " + arraySize);
            System.out.println("elementCounter: " + elementCounter);

            //TODO создаем новую коллекцию *2 и рассчитываем хэшкоды для всех элементов

            Object tmpArray[] = hashTable;

            for (int newArraySize = arraySize * 2; ; newArraySize++) {
                System.out.println("newArraySize " + newArraySize);
                if (checkIsItANaturalNumber(newArraySize)) {
                    System.out.println("Намбер " + newArraySize + " из натурал)))))");
                    System.out.println("new Array size: " + newArraySize);
                    arraySize = newArraySize;
                    hashTable = new Object[arraySize];
                    for (Object object : tmpArray) {
                        if (object != null) {
                            hashTable[collision(object)] = object;
                        }
                    }

                    break;
                }

            }
            System.out.println("*********");
        }

    }

    public int hashCodeIndexCounter(int hashCOde) {


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

    private int collision(Object o) {
        int collisionHashcode = hashCodeIndexCounter(o.hashCode());
        int count = 0;

        System.out.println("hash: " + collisionHashcode);
        // нахер рекурсию с переполнением стэка
        while (true) {
            if (hashTable[collisionHashcode] == null) {
                System.out.println("Ячейка пуста: " + collisionHashcode + " -выход из цикла");
                returnBooleanValue = false;
                break;


            } else {

                if (doesObjectEquals(o, hashTable[collisionHashcode])) {
                    System.out.println("Объекты равны в ячейке " + collisionHashcode);
                    returnBooleanValue = true;
                    break;
                } else {
                    collisionHashcode = (hashCodeIndexCounter(o.hashCode()) +
                            count * (1 + hashCodeIndexCounter(o.hashCode()) % (arraySize / 2))) % arraySize;
                    System.out.println("Новый хэш: " + collisionHashcode);
                    count++;
                }

            }

        }

        return collisionHashcode;
    }

    @Override
    public String toString() {
        return Arrays.toString(hashTable);
    }
}


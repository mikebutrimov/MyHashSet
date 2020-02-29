import java.util.Arrays;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
/*
        Set<String> strings = new MyDoubleHashingSet();
        strings.add("asd");
        strings.add("qwe");*/

/*String hashCOdeTester = "";
        for (int i = 0; i < 25; i++) {
            hashCOdeTester+="q";
            System.out.println("hash code: " + hashCOdeTester.hashCode() + "   остаток от 16:  " +Math.abs(hashCOdeTester.hashCode()%15));
        }*/


/*
        System.out.println("_______________");
        Integer i = new Integer(12);
        Integer i2 = 12;

        String s = new String("qwe");
        String qwe = "qwe";

        System.out.println(i == i2);
        System.out.println(i.equals(i2));
        System.out.println(s.equals(qwe));
        System.out.println(s == qwe);
        System.out.println();
        System.out.println(i.hashCode() + "  " + i2.hashCode());
        System.out.println(s.hashCode() + "  " + qwe.hashCode());

        Set<Integer> integers = new HashSet<>();
        integers.add(i);
        integers.add(i2);
        integers.add(12);
        integers.add(13);
        System.out.println(integers.toString());*/

            //тут переопределены методы hashCOde & equals
        TestObject testObject = new TestObject(12, "qwe");
        TestObject testObject2 = new TestObject(12, "qwe");

        Set<String> testObjects = new MyDoubleHashingSet();

        String s = "";
        for (int i = 0; i < 15; i++){
            System.out.println("Добавялемыый элемент: " + s );
            testObjects.add(s);
                 s += "q";



        }





    }
}

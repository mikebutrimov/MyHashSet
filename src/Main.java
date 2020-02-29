import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Set<String> strings = new HashSet<>();
        strings.add("qwe");
        strings.add("qwe1");
        strings.add("qwe2");




        Set<String> stringSet = new MyDoubleHashingSet(strings);
        System.out.println(stringSet.toString());



    }
}

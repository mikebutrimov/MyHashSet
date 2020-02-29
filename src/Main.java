import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Set<String> strings = new HashSet<>();
        for (int i = 0; i< 10; i++){
           strings.add(i + " object");
        }





        Set<String> stringSet = new MyDoubleHashingSet(strings);
        System.out.println(stringSet.toString());


    }
}

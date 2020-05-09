package OtherStuff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Immutable {
    public static void main(String[] args) {
        System.out.println((new Test1(new ArrayList<>(Arrays.asList("This", " is", " a Test")))).getList());
        Test1 t1 = new Test1(new ArrayList<>(Arrays.asList("This", " is", " t1")));
        Test1 t2 = new Test1(new ArrayList<>(Arrays.asList("This", " is", " t2")));
        System.out.println(t1.toString());
        System.out.println(t2.toString());
        t1 = t2;
        System.out.println(t1.toString());
    }

}

final class Test1 {
    private final List<String> list;

    Test1(List<String> list) {
        this.list = Collections.unmodifiableList(list);
    }

    List<String> getList() {
        return new ArrayList<>(list);
    }

    @Override
    public String toString() {
        String result = "";
        for (String item : list) {
            result += item + " ";
        }
        result.substring(0, result.length() - 1);
        return "Test1{" + result + '}';
    }
}

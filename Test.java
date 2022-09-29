import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        final MapStringMultiSet ls1 = new MapStringMultiSet();
        final MapStringMultiSet ls2 = new MapStringMultiSet();
        try (final Scanner s = new Scanner(System.in)) {
            final Scanner line = new Scanner(s.nextLine());
                    while (line.hasNext()) {
                        ls1.add(line.next());
                    }
                    while (line.hasNext()) {
                        ls2.add(line.next());
                    }
        }
        System.out.println(ls1.intersection(ls2).toString());
        System.out.println(ls1.union(ls2).toString());
        
 //       System.out.println("Fine del Test");
    }
}
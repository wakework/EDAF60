package Seminarie3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Stream {
    public static void main (String[] args) {
        new Stream().run(args);
    }

    void run(String[] args) {
        var total =
            Arrays
            .stream(args)
            .mapToInt(s -> {
                System.out.printf("mappar %s\n", s);
                return toInt(s);
            })
            .filter(k -> {
                System.out.printf("filtrerar %d\n", k);
                return isOdd(k);
            })
            .map(k -> {
                System.out.printf("mappar %d\n", s);
                return k*k;
            })
            .sum();
        System.out.println(total);
    }

    boolean isOdd(int k) {
        return k % 2 != 0;
    }

    int toInt(String s) {
        return s
            .chars()
            .map(k -> k - '0')
            .reduce((acc, a) -> 10 * acc + a);
    }
    
}

interface Person {

    String name();

    int birthYear();
}

class StreamingPeople {
    public static void main(String[] args) {
        new StreamingPeople().run();
    }

    void run() {
        var people = List.of(CH.deliver("Adam", 2014),
                                CH.deliver("Liv", 2018), 
                                CH.deliver("Finn", 2014), 
                                CH.deliver("Sofia", 2018));
    }

    List<Person> findBornAfter(List<Person> people, int year) {
        return 
            people
            .stream()
            .filter(p -> p.birthYear() >= year)
            .toList();
    }

    String findNamesContaining (List<Person> people, 
                                    String partOfName) {
        return
            people
            .stream()
            .map(p -> p.name())
            .filter(name -> name.contains(partOfName))
            .collect(Collectors.joining(", "));
    }
}

class Factorials {
    public static void main(String[] args) {
        new Factorials().run();
    }

    void run() {
        IntStream
            .rangeClosed(1, 10)
            .forEach(k -> System.out.printf("%3d: %10\n", k, factorial(k)));
    }

    private Object factorial(int k) {
        return null;
    }

    
}
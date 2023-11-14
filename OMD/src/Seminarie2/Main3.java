package Seminarie2;

import java.util.ArrayList;
import java.util.List;

/**
 * Svar: Template Method! Enda som skiljer är värdet i första append satsen.
 */

public class Main3 {
    
    public static void main (String[] args) {
        new Main3().run();
    }
    private void run() {
        var items = List.of("Adam", "Bodil", "Cecilia");
        test(new StarList(), items);
        test(new EnumList(), items);
        test(new Counterization(idx -> "(" + (2 * idx + 1) + ")"), items);

    }
    void test(Counter counter, List<String> items) {
            
    }
}

abstract class Counter extends ArrayList<String> {

    protected abstract String label(int idx);

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (var i = 0; i < size(); i++) {
            builder.append(label(i)); // okej att skicka in ett värde som inte gör något
            builder.append(get(i));
            builder.append('\n');
        }

        return builder.toString();
    }
}

class Counterization extends Counter {
    private Counter counter;

    public Counterization (Counter counter) {
        this.counter = counter;
    }

    protected String label(int idx) {
        return counter.label(idx);
    }
}

class StarList extends Counter {

    protected String label(int idx) {
        return "* ";
    }
}

class EnumList extends Counter {

    protected String label(int idx) {
        return (idx + 1) + ". ";
    }
}

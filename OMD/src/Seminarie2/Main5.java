package Seminarie2;

public class Main5 {
    
    public static void main (String[] args) {
        new Main5().run(args);
    }

    Optional<Integer> toInt(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    void run(String[] args) {
        var first =
            Optionals
            .lift(args, 0)
            .flatMap(s -> toInt(s))
            .orElse(0);
        var second =
            Optionals
            .lift(args, 1)
            .flatMap(s -> toInt(s))
            .orElse(0);
        System.out.println(first + second);
    }
}

/**
 * Bär eventuella värden! Annars tomma.
 */

class Optional<T> {

    private T value;

    private Optional (T value) {
        this.value = value;
    }

    public static <T> Optional<T> empty() {
        return new Optional<>(null);
    }

    public static <T> Optional<T> of(T t) {
        return new Optional<>(t);
    }

    public static <T> Optional<T> ofNullable(T t) {
        return new Optional<>(t);
    }

    public boolean isPresent() {
        return value != null;
    }

    // ?
    public T orElse(T defaultValue) {
        return 
            isPresent() 
            ? value 
            : defaultValue;
    }

    public <U> Optional<U> map(Function<T, U> f) {
        return
            isPresent()
            ? of(f.valueAt(value))
            : empty();
    }

    public <U> Optional<U> flatMap(Function<T, Optional<U>> f) {
        return
            isPresent()
            ? f.valueAt(value)
            : empty();
    }
}

interface Function<T, U> {
    U valueAt(T  t);
}

class Optionals {
    
    public static <T> Optional<T> lift(T[] values, int index) {
        return 
            index < values.length
            ? Optional.of(values[index])
            : Optional.empty();
    }
}

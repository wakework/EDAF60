package Seminarie3;

import java.util.stream.IntStream;

/**
 * SOLID!
 */
public class Random {
    public int nextInt(int bound) { // ger ett rektangelfördelat heltal i intelvallet [0..bound]
        return 0;
    } 

    public double nextDouble() { // ger ett rektangelfördelat reellt tal i intervallet [0..1]
        return 0.0;
    }
    public double nextGaussian() { // ger ett normalfördelat slumptal med medelvärde 0 och standardavvikelse 1
        return 0.0;
    }
}

interface DoubleRandom {
    double next();
}

class Simulation {
    public static void main(String[] args) {
        new Simulation().run();
    }

    void run() {
        var rand = new Random();
        simulate(() -> rand.nextDouble());
        simulate(() -> 10 * rand.nextDouble());
        simulate(() -> 10 * rand.nextGaussian() + 100);
    }

    void simulate(DoubleRandom rand) {
        IntStream
            .range(1, 17)
            .forEach(k -> System.out.println(rand.next()));
    }
}
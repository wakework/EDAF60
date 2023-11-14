package Tentor;

import java.util.LinkedList;
import java.util.List;

public class Ex201810 {}

// 3 BooleanWord

interface Word {
    void add(Word left, Word right);

    void mul(Word left, Word right);

    void copy(Word other);

    boolean equals(Word other);

    String toString();
}

class BooleanWord implements Word {

    private boolean logic;

    public BooleanWord (boolean logic) {
        this.logic = logic;
    }

    @Override
    public void add(Word left, Word right) {
        logic = toBoolean(left).logic || toBoolean(right).logic;
    }

    private BooleanWord toBoolean(Word other) {
        return (BooleanWord) other;
    }

    @Override
    public void mul(Word left, Word right) {
        logic = toBoolean(left).logic && toBoolean(right).logic;
    }

    @Override
    public void copy(Word other) {
        logic = toBoolean(other).logic;
    }

    @Override
    public boolean equals(Word other) {
        return logic == toBoolean(other).logic;
    }

    public String toString() {
        if(logic == true) {
            return "True";
        } else {
            return "False";
        }
    }

}

interface WordFactory {
    Word word(String s);
}

class BooleanWordFactory implements WordFactory {

    @Override
    public Word word(String s) {
        return new BooleanWord(!s.equals("0"));
    }
}


// 4. Observer/Observable

interface Observer {
    void update(Observable obs, Object obj);
}

class Observable {

    private List<Observer> observers = new LinkedList<>();
    private boolean changed = false;

    public void addObserver(Observer e) {
        observers.add(e);
    }

    public void setChanged() {
        changed = true;
    }

    public boolean hasChanged() {
        return changed;
    }

    public void clearChanged() {
        changed = false;
    }

    public void notifyObservers(Object object) {
        if (!changed) {
            return;
        }

        for(Observer e : observers) {
            e.update(this, object);
        }

        clearChanged();
    }

    public void notifyObservers() {
        notifyObservers(null);
    }
}

// View
class MotionDetector extends Observable {

    // Om motiondetected -> notifiera alla som lyssnar.
    public void motionDetected(double distance, double angle) {
        setChanged();
        notifyObservers();
    }
}

// Model
class Alarm implements Observer {
    public void start() {
        // . . . startar alarmet . . .
    }

    // Om den blir uppdaterad -> starta larmet.
    @Override
    public void update(Observable obs, Object obj) {
        start();   
    }
}

// Controller
class SurveillanceSystem extends Observable implements Observer {
    
    // Observera detector!
    public void add(MotionDetector detector) {
        detector.addObserver(this);
    }

    // Olika Observable listor!
    public void add(Alarm alarm) {
        addObserver(alarm);
    }

    @Override
    public void update(Observable obs, Object obj) {
        setChanged();
        notifyObservers();
    }
} // Sätt Observer/Observable i eget paket! MVC på surveillance system.


// 5. Streams

// Slumptal
interface RealRandom {
    double next();
}

class Random {
    double nextDouble() {
        return 0;
    }

    double nextGaussian() {
        return 0;
    }
}

// Simulering
interface Simulation {
    void run(RealRandom rng);
}

// Databas
interface DB {
    void addSample(double sample);
}

class NormalRandom implements RealRandom {

    private Random rand = new Random();
    private double mean, std;

    public NormalRandom (double mean, double std) {
        this.mean = mean;
        this.std = std;
    }

    @Override
    public double next() {
        return rand.nextGaussian() * mean + std;
    }
}

class UniformRandom implements RealRandom {

    private Random rand = new Random();
    private double min, max;

    public UniformRandom (double min, double max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public double next() {
        return min + (max - min) * rand.nextDouble();
    }
}

// Lämpligt mönster - Decorator Pattern!

// Generell Decorator (Template) - inbakad klass, protected handle och final interface-metod.
abstract class RealRandomDecorator implements RealRandom {
    private RealRandom rand;

    public RealRandomDecorator (RealRandom random) {
        rand = random;
    }

    protected abstract void handle(double sample);

    public final double next() {
        var sample = rand.next();
        handle(sample);
        return sample;
    }
}

class RealRandomStat extends RealRandomDecorator {

    private double sumX, sumX2; //summa, summa kvadrat
    private int n; //antal

    public RealRandomStat(RealRandom random) {
        super(random);
    }

    @Override
    protected void handle(double sample) {
        sumX += sample;
        sumX2 += sample * sample;
        n++;
    }

    public double mean() {
        return sumX / n;
    }

    public double std() {
        return Math.sqrt((sumX2 - n * mean() * mean())/(n - 1));
    }
}

class RealRandomDB extends RealRandomDecorator {

    private DB db;

    public RealRandomDB(RealRandom random, DB db) {
        super(random);
        this.db = db;
    }

    @Override
    protected void handle(double sample) {
        db.addSample(sample);
    }
}

class BigBangSimulation implements Simulation {
    
    public static void main(String[] args) {
        var random = new UniformRandom(0, 4);
        var stats = new RealRandomStat(random);

        new BigBangSimulation().run(stats);
    }

    @Override
    public void run(RealRandom rng) {
        // TODO Auto-generated method stub
    }
    
}

package Tentor;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Ex201610 {}


// 4. Observer/Observable

class Stock extends Observable {

    private double value;

    public void update(double value) {
        this.value = value;

        setChanged();
        notifyObservers();
    }

    public double value() {
        return value;
    }
}

// Måste Observera varje stock
class Portfolio extends Observable {

    private List<Stock> portfolio;
    private double firstSum;

    public Portfolio () {
        portfolio = new ArrayList<>();
        firstSum = 0;
    }

    public void add(Stock stock) {
        firstSum += stock.value();
        portfolio.add(stock);
    }

    /**
     * private void update() { setChanged(); notifyObservers(); }
     */

    public double currentYield() {
        double value = portfolio
            .stream() // Skapa stream
            .mapToDouble(s -> s.value()) // Ändra till doubles
            .sum(); // metod för DoubleStream

        return value / firstSum;
    }
}

class PortfolioTracker extends Observable {
    private Portfolio portfolio;
    private double yieldLimit;

    public PortfolioTracker (Portfolio portfolio, double yieldLimit) {
        this.portfolio = portfolio;
        this.yieldLimit = yieldLimit;
        this.portfolio.addObserver((obs, obj) -> {
            PortfolioTracker.this.checkLimit();
        });
    }

    private void checkLimit() {
        if (portfolio.currentYield() < yieldLimit) {
            setChanged();
            notifyObservers();
        }
    }
}

// 5. Command Pattern

interface Drawing {
    void clear();
    void useForegroundColor();
    void useBackgroundColor();
    void moveTo(int x, int y);
    void circle(int radius);
    void rectangle(int w, int h);
}

interface DrawCommand {
    void draw(Drawing d);
    void undo();
}

abstract class DrawShape implements DrawCommand {
    protected int x,y;
    protected Drawing d;

    public DrawShape (int x, int y) {
        this.x = x;
        this.y = y;
    }

    protected abstract void handle();

    @Override
    public void draw(Drawing d) {
        this.d = d;
        d.useForegroundColor();
        move();
    }

    @Override
    public void undo() {
        d.useBackgroundColor();
        move();
    }

    private void move() {
        d.moveTo(x, y);
        handle();
    }
}

class DrawCircle extends DrawShape {
    private int radius;

    public DrawCircle(int x, int y, int radius) {
        super(x, y);
        this.radius = radius;
    }

    @Override
    protected void handle() {
        d.circle(radius);
    }
}

class DrawSquare extends DrawShape {
    private int side;

    public DrawSquare(int x, int y, int side) {
        super(x, y);
        this.side = side;
    }

    @Override
    protected void handle() {
        d.rectangle(side, side);
    }
}

// 6. 

interface Action {
    void execute(Drawing d, Stack<DrawCommand> history);
}

class DrawAction implements Action {
    private DrawCommand cmd;

    public DrawAction (DrawCommand cmd) {
        this.cmd = cmd;
    }

    @Override
    public void execute(Drawing d, Stack<DrawCommand> history) {
        history.push(cmd);
        cmd.draw(d);
    }
}

class Undo implements Action {

    @Override
    public void execute(Drawing d, Stack<DrawCommand> history) {
        if(!history.isEmpty()) {
            history.pop().undo();
        }
    }
}

class Exit implements Action {

    @Override
    public void execute(Drawing d, Stack<DrawCommand> history) {
        history.clear();
        System.exit(0);
    }
}

interface GUI {
    Action next();
}

class G implements GUI {

    @Override
    public Action next() {
        // TODO Auto-generated method stub
        return null;
    }
    
}

class Dr implements Drawing {

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void useForegroundColor() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void useBackgroundColor() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void moveTo(int x, int y) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void circle(int radius) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void rectangle(int w, int h) {
        // TODO Auto-generated method stub
        
    }
    
}

class Main6 {
    public static void main(String[] args) {
        new Main6().run(new G(), new Dr());
    }

    void run(GUI gui, Drawing d) {
        Stack<DrawCommand> history = new Stack<>();
        while(true) { // Standard System loop.
            gui.next().execute(d, history);
        }
    }
}


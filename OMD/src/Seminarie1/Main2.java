package Seminarie1;

import java.util.*;

public class Main2 {
    public static void main (String[] args) {
        
    }
}

interface Graphics {

    void drawRectangle(int x, int y, int width, int height);
    void drawCircle(int x, int y, int radius);

}

interface Shape {

    void paint(Graphics g);

}

abstract class MovableShape implements Shape {
    
    protected int x, y;

    protected MovableShape (int x, int y) {
        moveTo(x, y);
    }

    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move (int dx, int dy) {
        moveTo(x + dx, y + dy);
    }
}

class Square extends MovableShape {

    private int side;

    public Square (int x, int y, int side) {
        super(x, y);
        this.side = side;
    }

    @Override
    public void paint(Graphics g) {
        g.drawRectangle(x, y, side, side);
    }
}

class Circle extends MovableShape {

    private int radius;

    public Circle(int x, int y, int radius) {
        super(x, y);
        this.radius = radius;
    }

    @Override
    public void paint(Graphics g) {
        g.drawCircle(x, y, radius);
    }
}

/**
 * Composite Pattern exempel.
 */
class Figure implements Shape {

    private List<Shape> shapes = new ArrayList<>();

    public void add(Shape s) {
        shapes.add(s);
    }

    public void paint (Graphics g) {
        for (var shape : shapes) {
            shape.paint(g);
        }
    }
}
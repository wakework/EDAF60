package Seminarie1;

import java.util.ArrayList;
import java.util.List;

public class Main6 {
    
    public static void main (String[] args) {
        new Main6().run();
    }

    void distanceBetween(String ps0, String ps1, PointFactory pf) {
        var p0 = pf.create(ps0);
        var p1 = pf.create(ps1);
        var distance = p0.distanceTo(p1);
        System.out.println(distance);
    }

    void run() {
        distanceBetween("0.0 0.0", "3.0 4.0", new Point2DFactory());
        distanceBetween("0.0 0.0 0.0", "3.0 4.0 5.0", new Point3DFactory());
    }
}

interface PointFactory {
    Point create (String s);
}

abstract class PointNDFactory implements PointFactory {

    public Point create(String s) {
        var coords = s.split("\\s+");
        var coordinates = new ArrayList<Double>();
        for (var c : coords) {
            coordinates.add(Double.parseDouble(c));
        }
        return instantiate(coordinates);
    }

    protected abstract Point instantiate(List<Double> coords);
    
}

class Point2DFactory extends PointNDFactory {

    protected Point instantiate(List<Double> coords) {
        return new Point2D(coords.get(0), coords.get(1));
    }

}

class Point3DFactory extends PointNDFactory {

    protected Point instantiate(List<Double> coords) {
        return new Point3D(coords.get(0), coords.get(1), coords.get(2));
    }

}

/**
 * ------------------------------------------------------------------------------
 * ------------------------------------------------------------------------------
 * ------------------------------------------------------------------------------
 */

interface Point {

    public double distanceTo(Point other);

}

class Point2D implements Point {

    private double x, y;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    private Point2D ref(Point other) {
        return (Point2D) other;
    }

    public double distanceTo(Point other) {
        var dx = x - ref(other).x;
        var dy = y - ref(other).y;
        return Math.hypot(dx, dy);
    }
}

class Point3D implements Point {

    private double x, y, z;

    public Point3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    private Point3D ref(Point other) {
        return (Point3D) other;
    }

    public double distanceTo(Point other) {
        var dx = x - ref(other).x;
        var dy = y - ref(other).y;
        var dz = z - ref(other).z;
        return Math.hypot(Math.hypot(dx, dy), dz);
    }
}

class Segment {

    private Point p0, p1;

    public Segment(Point p0, Point p1) {
        this.p0 = p0;
        this.p1 = p1;
    }

    public double length() {
        return p0.distanceTo(p1);
    }
}
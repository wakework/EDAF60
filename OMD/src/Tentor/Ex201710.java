package Tentor;

import java.util.Optional;

public class Ex201710 {}

// 1. Sekvensdiagram och Strategy-Pattern

interface Strategy {
    int op(D d);
}

class B implements Strategy {

    @Override
    public int op(D d) {
        return 25;
    }
}

class C implements Strategy {

    @Override
    public int op(D d) {
        return 32;
    }
    
}

class D {
    public D () {}
}

class A {
    private Strategy current;

    public void doIt (D d) {
        current.op(d);
    }

    public void setStrategy(Strategy st) {
        current = st;
    }
}

class Main {
    public static void main(String[] args) {
        A a = new A();
        a.setStrategy(new B());
        a.doIt(new D());
        a.setStrategy(new C());
        a.doIt(new D());
    }
}


// 3. Optional-mönster

interface Company {
    Optional<Department> getDepartment(String code);
}

interface Department {
    Optional<Employee> getEmployee(int nbr);
}

interface Employee {
    String name();
}

class Main3 {
    public static void main(String[] args) {
    }

    public String findName(Company company, String deptCode, int emCode) {
        return 
            company
            .getDepartment(deptCode)
            .flatMap(e -> e.getEmployee(emCode))
            .map(e -> e.name()) // Ifall tidigare inte fångat värdet
            .orElse("Not Found"); // Kolla returtyp
    }
}


// 4. MVC och DecoratorPattern

interface Point2D {
    Point2D move(double dx, double dy);

    double distanceTo(Point2D other);

    String toString();
}

interface Robot {
    Point2D getPosition();

    void move(double dx, double dy);
}

interface RobotController {
    void run(Robot robot);
}

class VacuumCleaner implements Robot {
    private Point2D pos;

    public VacuumCleaner(Point2D pos) {
        this.pos = pos;
    }

    public void move(double dx, double dy) {}

    public Point2D getPosition() {
        return pos;
    }
}

abstract class RobotDecorator implements Robot {

    protected Robot robot; // change to protected for visibility

    public RobotDecorator (Robot robot) {
        this.robot = robot;
    }

    @Override
    public Point2D getPosition() {
        return robot.getPosition();
    }

    protected abstract void handle(double dx, double dy);

    @Override
    public final void move(double dx, double dy) {
        robot.move(dx, dy);
        handle(dx, dy);
    }
}

class RobotLogger extends RobotDecorator {

    public RobotLogger (Robot robot) {
        super(robot);
    }

    @Override
    protected void handle(double dx, double dy) {
        System.out.println("Moving to " + robot.getPosition().toString());
    }
}

interface RobotSupervisor {
    void alert(String message);
}

class RobotGuardian extends RobotDecorator {
    private Point2D origin;
    private double distance;
    private RobotSupervisor supervisor;

    public RobotGuardian(Robot robot, Point2D origin, 
                double distance, RobotSupervisor supervisor) {
        super(robot);
        this.origin = origin;
        this.distance = distance;
        this.supervisor = supervisor;
    }

    @Override
    protected void handle(double dx, double dy) {
        var d = warning(robot.getPosition(), origin);
        if(d > distance) {
            supervisor.alert("WARNING: The robot is now "
                    + d + " m from origin");
        }
    }
    
    private double warning(Point2D pos, Point2D base) {
        return pos.distanceTo(base);
    }
}

class Point2 implements Point2D {

    public Point2 (double dx, double dy) {

    }

    @Override
    public Point2D move(double dx, double dy) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public double distanceTo(Point2D other) {
        // TODO Auto-generated method stub
        return 0;
    }
    
}

class Main4 {
    public static void main(String[] args) {
        // VacuumCleaner vc = new VacuumCleaner(new Point2(0, 0));
        // var log = new RobotLogger(vc);
        // var g = new RobotGuardian(log, origin, distance, supervisor)
    }
}


// 5. Computer och Template Method

interface Instruction {
    void execute(Memory m, ProgramCounter pc);
}

interface Memory {}

interface ProgramCounter {
    void set(int label);
}
interface Operand {
    double value(Memory m);
}

abstract class Template implements Instruction {
    protected int label;
    protected Operand op1, op2;

    public Template(int label, Operand op1, Operand op2) {
        this.label = label;
        this.op1 = op1;
        this.op2 = op2;
    }

    protected abstract boolean evaluate(double l, double r);

    public void execute(Memory m, ProgramCounter pc) {
        if (evaluate(op1.value(m), op2.value(m))) {
            pc.set(label);
        } 
    }
}

class JumpLess extends Template {

    public JumpLess(int label, Operand op1, Operand op2) {
        super(label, op1, op2);
    }

    @Override
    protected boolean evaluate(double l, double r) {
        return l < r;
    }

}



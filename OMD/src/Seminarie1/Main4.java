package Seminarie1;

public class Main4 {
    public static void main (String[] args) {
    }
}

/**
 * Typexempel på Template Pattern.
 */

interface Expr {
    String toString();
}

abstract class BinOp implements Expr {

    private Expr left, right;

    public BinOp (Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }
    
    protected abstract String operatorName();

    public final String toString() {
        return String.format("%s %s %s", left.toString(), operatorName(), right.toString());
    }
}

class Add extends BinOp {

    public Add (Expr left, Expr right) {
        super(left, right);
    }

    public String operatorName() {
        return "+";
    }
}

class Mul extends BinOp {

    public Mul (Expr left, Expr right) {
        super(left, right);
    }

    public String operatorName() {
        return "*";
    }
}
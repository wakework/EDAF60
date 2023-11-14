package Computer.Software;
import Computer.Hardware.*;

public class Mul extends BinOperation {

    public Mul(Operand left, Operand right, Address address) {
        super(left, right, address);
    }

    @Override
    protected void calc(Word w1, Word w2) {
        w1.mul(w1, w2);
    }

    @Override
    public String toString() {
        return ("Multiply " + left.toString() + " and " + right.toString() + " into " + address.toString());
    }
}

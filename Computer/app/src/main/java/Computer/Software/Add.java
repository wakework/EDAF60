package Computer.Software;

import Computer.Hardware.*;

public class Add extends BinOperation {

    public Add(Operand left, Operand right, Address address){
        super(left, right, address);
    }

    @Override
    protected void calc(Word w1, Word w2) {
        w1.add(w1, w2);
    }

    @Override
    public String toString() {
        return ("Add " + left.toString() + " and " + right.toString() 
                                            + " into " + address.toString());
    }
    
}

package Computer.Software;

import Computer.Hardware.*;

/**
 * Template Method for all instructions. Dont-Repeat-Yourself!
 */
public abstract class BinOperation implements Instruction {

    protected Operand left, right;
    protected Address address;

    public BinOperation (Operand left, Operand right, Address address) {
        this.left = left;
        this.right = right;
        this.address = address;
    }

    public void execute(Memory memory, ProgramCounter programCounter) {

        var w1 = left.getWord(memory);
        var w2 = right.getWord(memory);
        calc(w1, w2);

        memory.addWord(w1.getWord(memory), address);
    }

    protected abstract void calc(Word w1, Word w2);

    public abstract String toString();
    
}

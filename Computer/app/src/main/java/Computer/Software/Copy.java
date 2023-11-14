package Computer.Software;

import Computer.Hardware.*;

/**
 * The Value, either a word or address in our case, copies to
 * the address with index idx.
 */
public class Copy implements Instruction {

    private Operand value;
    private Address address;

    public Copy(Operand value, Address address) {
        this.value = value;
        this.address = address;
    }

    /**
     * Executes the copy.
     */
    @Override
    public void execute(Memory memory, ProgramCounter programCounter) {
        var word = value.getWord(memory);
        memory.addWord(word, address);
    }

    @Override
    public String toString() {
        return "Copy " + value + " to " + address;
    }
    
}

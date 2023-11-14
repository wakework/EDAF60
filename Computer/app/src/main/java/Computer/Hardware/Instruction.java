package Computer.Hardware;

/**
 * Instruction interface.
 */
public interface Instruction {

    public void execute(Memory memory, ProgramCounter programCounter);

    public String toString();
    
}

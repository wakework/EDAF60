package Computer.Software;
import Computer.Hardware.Instruction;
import Computer.Hardware.Memory;
import Computer.Hardware.ProgramCounter;

public class Halt implements Instruction {

    @Override
    public void execute(Memory memory, ProgramCounter programCounter) {
        programCounter.halt();
    }
    
    @Override
    public String toString() {
        return "Halt";
    }
}

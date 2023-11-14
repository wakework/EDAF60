package Computer.Software;

import Computer.Hardware.*;

public class Jump implements Instruction {

    private int step;

    public Jump (int step) {
        this.step = step;
    }

    @Override
    public void execute(Memory memory, ProgramCounter programCounter) {
        programCounter.setIndex(step - 1);
        
    }

    @Override
    public String toString() {
        return "Jump to " + step;
    }
    
}

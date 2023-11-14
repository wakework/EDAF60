package Computer.Software;

import Computer.Hardware.*;

public class Print implements Instruction {

    private Operand w;

    public Print (Operand w) {
        this.w = w;
    }

    @Override
    public void execute(Memory memory, ProgramCounter programCounter) {
        System.out.println(w.getWord(memory).toString());
    }

    @Override
    public String toString() {
        return "Print " + w.toString();
    }
    
}

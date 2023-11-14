package Computer.Software;

import Computer.Hardware.*;

public class JumpEq implements Instruction {

    private int step;
    private Operand w1, w2;

    public JumpEq (int step, Operand w1, Operand w2) {
        this.step = step;
        this.w1 = w1;
        this.w2 = w2;
    }

    @Override
    public void execute(Memory memory, ProgramCounter programCounter) {
        if (w1.getWord(memory).equals(w2.getWord(memory))) { 
            programCounter.setIndex(step - 1);
        }
    }

    @Override
    public String toString() {
        return "Jump to " + step + " if " + w1.toString() + " == " + w2.toString();
    }
    
}

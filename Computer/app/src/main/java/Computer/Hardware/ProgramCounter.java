package Computer.Hardware;

/**
 * ProgramCounter counts which instruction index is next, and knows if halt is called.
 */
public class ProgramCounter {

    private int count;
    private boolean halt;

    public ProgramCounter() {
        count = 0;
        halt = false;
    }

    public int getIndex() {
        return count;
    }

    public void setIndex(int step) {
        count = step;
    }

    public void halt() {
        halt = true;
    }

    public boolean isHalt() {
        return halt;
    }
    
    public void newIns() {
        count++;
    }
}

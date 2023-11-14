package Computer.Hardware;

import Computer.Software.Operand;

/**
 * Stores the index for a word in the memory.
 */
public class Address implements Operand {

    private int idx;

    public Address (int idx) {
        this.idx = idx;
    }

    /**
     * Gets the word at index idx in the memory.
     */
    @Override
    public Word getWord(Memory memory) {
        return memory.getWord(idx);
    }

    public int getIdx() {
        return idx;
    }

    @Override
    public String toString() {
        return "[" + idx + "]";
    }
    
}

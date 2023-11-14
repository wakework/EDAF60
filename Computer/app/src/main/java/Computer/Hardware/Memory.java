package Computer.Hardware;

/**
 * Memory of a Computer.
 */
public class Memory {

    private Word[] memory;
    
    public Memory(int size, WordFactory wf) {
        memory = wf.memorySize(size);
    }

    public Word getWord(int index) {
        return memory[index];
    }

    /**
     * Adds the word to the memory, on address a with index idx.
     */
    public void addWord(Word word, Address a) {
        int index = a.getIdx();
        memory[index] = word;
    }

}

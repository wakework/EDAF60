package Computer.Hardware;

/**
 * WordFactory interface for all factories. Create a Word type, and memorySize
 * to check size of memory.
 * 
 * Single-Responsibility-Principle and Open/Closed Principle!
 */
public interface WordFactory {
    
    public Word word(String s);

    public Word[] memorySize(int size);
}

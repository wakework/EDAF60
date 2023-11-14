package Computer.Software;

import Computer.Hardware.Memory;
import Computer.Hardware.Word;

/**
 * Operand for availability between address, word or own implementation.
 */
public interface Operand {

    public Word getWord(Memory memory);

    public String toString();
    
}

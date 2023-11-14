package Computer.Software;

import Computer.Hardware.Word;
import Computer.Hardware.WordFactory;

/**
 * Factory for LongWords. Single-Responsibility-Principle and Open/Closed Principle!
 */
public class LongWordFactory implements WordFactory {

    @Override
    public LongWord word(String s) {
        return new LongWord(Long.parseLong(s));
    }

    @Override
    public Word[] memorySize(int size) {
        return new LongWord[size];
    }
    
}

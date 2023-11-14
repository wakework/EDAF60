package Computer.Software;

import Computer.Hardware.Word;
import Computer.Hardware.WordFactory;

/**
 * Factory for ByteWords. Single-Responsibility-Principle and Open/Closed Principle!
 */
public class ByteWordFactory implements WordFactory {

    @Override
    public Word word(String s) {
        return new ByteWord(Byte.parseByte(s));
    }

    @Override
    public Word[] memorySize(int size) {
        return new ByteWord[size];
    } 
}

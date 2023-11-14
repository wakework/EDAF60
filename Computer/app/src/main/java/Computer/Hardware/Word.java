package Computer.Hardware;

import Computer.Software.Operand;

/**
 * Abstract class for a word.
 */
public abstract class Word implements Operand {

    @Override
    public Word getWord(Memory memory) {
        return this;
    }

    public abstract void add(Word w1, Word w2);

    public abstract void mul(Word w1, Word w2);

    public abstract boolean equals(Word w);

    public abstract String toString();
     
}

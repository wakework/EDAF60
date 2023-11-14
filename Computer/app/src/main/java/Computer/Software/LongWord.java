package Computer.Software;

import Computer.Hardware.Word;

public class LongWord extends Word{

	private Long value;

    public LongWord(Long value) {
        this.value = value;
    }

	@Override
	public void add(Word w1, Word w2) {
		value = (Long) (toLong(w1) + toLong(w2));
	}

	@Override
	public void mul(Word w1, Word w2) {
		value = (Long) (toLong(w1) * toLong(w2));
	}

	@Override
	public boolean equals(Word w) {
		if (w instanceof LongWord) {
			return toLong(w).equals(value);
		}
		return false;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	private Long toLong(Word w) {
		var s = w.toString();
		return Long.parseLong(s);
	}
    
}

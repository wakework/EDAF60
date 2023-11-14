package Computer.Software;

import Computer.Hardware.Word;

public class ByteWord extends Word {

    private Byte value;
    
    public ByteWord (Byte value) {
		this.value = value;
    }

	@Override
	public void add(Word w1, Word w2) {
        value = (byte) (toByte(w1) + toByte(w2));
	}

	@Override
	public void mul(Word w1, Word w2) {
		value = (byte) (toByte(w1) * toByte(w2));
	}

	@Override
	public boolean equals(Word w) {
		if(w instanceof ByteWord) {
			return toByte(w).equals(value);
		}
		return false;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	private Byte toByte(Word w){
		return Byte.parseByte(w.toString());
	}

}

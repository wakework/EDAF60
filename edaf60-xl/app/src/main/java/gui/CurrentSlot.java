package gui;

import java.awt.Color;

public class CurrentSlot {

    private SlotLabel slot;

    public CurrentSlot(){ }

    // get the current slot label
    public String getLabel() {
        return slot.getLabel();
    }

    public SlotLabel getSlot() {
        return slot;
    }

    public void remove() {
        slot.setText("");
    }

    // new currentslot
    public void setCurrent(SlotLabel newCurrent) {

        if (this.slot != null) {
            this.slot.setBackground(Color.WHITE);
        }

        this.slot = newCurrent;
        slot.setBackground(Color.YELLOW);
        
    }
    
}

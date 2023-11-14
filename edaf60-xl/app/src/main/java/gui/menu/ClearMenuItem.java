package gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

import gui.CurrentSlot;
import gui.XL;
import sheet.Sheet;

class ClearMenuItem extends JMenuItem implements ActionListener {

    private XL xl;
    private CurrentSlot current;

    public ClearMenuItem(XL xl, CurrentSlot current) {
        super("Clear");
        this.xl = xl;
        this.current = current;
        addActionListener(this);
    }

    // Button to clear Current Slot
    public void actionPerformed(ActionEvent e) {
        Sheet s = xl.sheet();
        s.remove(current.getLabel());
        current.remove();
    }
}

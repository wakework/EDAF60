package gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

import gui.SlotLabel;
import gui.XL;
import sheet.Sheet;

class ClearAllMenuItem extends JMenuItem implements ActionListener {

    private XL xl;

    public ClearAllMenuItem(XL xl) {
        super("Clear all");
        this.xl = xl;
        addActionListener(this);
    }

    // Button to Clear All cells
    public void actionPerformed(ActionEvent e) {
        Sheet s = xl.sheet();
        s.clearAll();
        
        for(SlotLabel slot : xl.slotList()) {
            slot.setText("");
        }

        xl.editor().clearField();
    }
}

package gui;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.JTextField;

import util.XLException;

public class Editor extends JTextField implements KeyListener {

    private CurrentSlot current;
    private XL xl;
    private StatusLabel status;

    public Editor(XL xl, CurrentSlot current, StatusLabel status) {
        addKeyListener(this);
        setBackground(Color.WHITE);
        this.current = current;
        this.xl = xl;
        this.status = status;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            var enter = current.getLabel();

            try {
                xl.sheet().addToSlot(enter, getText());
                current.getSlot().update(enter);
            } catch (Exception e1) {
                status.update(e1.getMessage());
                throw new XLException(e1.getMessage());
            }

            // Update view when changing one slot.
            for (SlotLabel slot : xl.slotList()) {
                slot.update(slot.getLabel());
            }
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void update(String address) {
        if(xl.sheet().getCell(address) != null) {
            setText(xl.sheet().getCell(address).displayEditor());   
        }
    }

    public void clearField() {
        setText("");
    }
}

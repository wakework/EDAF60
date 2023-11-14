package gui;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.SwingConstants;
import util.XLException;

public class SlotLabel extends ColoredLabel implements MouseListener {

    private CurrentSlot current;
    private String address;
    private CurrentLabel label;
    private StatusLabel status;
    private XL xl;

    public SlotLabel(XL xl, String address, CurrentSlot current, CurrentLabel label, StatusLabel status) {
        super("                    ", Color.WHITE, SwingConstants.CENTER);
        this.current = current;
        this.address = address;
        this.label = label;
        this.status = status;
        this.xl = xl;
        addMouseListener(this);
    }

    public String getLabel() {
        return address;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Clear fields
        xl.editor().clearField();
        status.clearStatus();

        // Update fields
        current.setCurrent((SlotLabel) e.getSource());
        String word = current.getSlot().getLabel();
        label.update(word);
        xl.editor().update(word);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public void update(String address) {
        try {
            setText(xl.sheet().displayCell(address));
        } catch (XLException e) {
            status.update(e.getMessage());
        }
    }
}

package gui;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.WEST;

public class SheetPanel extends BorderPanel {

    private SlotLabels slotLabels;

    public SheetPanel(int rows, int columns, XL xl, CurrentSlot current, 
                                    CurrentLabel label, StatusLabel status, SlotLabels labels) {
        slotLabels = labels;
        add(WEST, new RowLabels(rows));
        add(CENTER, labels);
    }

    public SlotLabels getAll() {
        return slotLabels;
    }

}

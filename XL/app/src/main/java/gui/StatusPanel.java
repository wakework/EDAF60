package gui;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.WEST;

public class StatusPanel extends BorderPanel {

    protected StatusPanel(CurrentLabel current, StatusLabel statusLabel) {
        add(WEST, current);
        add(CENTER, statusLabel);
    }
}

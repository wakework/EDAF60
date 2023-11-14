package gui.menu;

import java.io.FileNotFoundException;
import javax.swing.JFileChooser;

import gui.SlotLabel;
import gui.StatusLabel;
import gui.XL;
import util.XLException;

class LoadMenuItem extends OpenMenuItem {

    public LoadMenuItem(XL xl, StatusLabel statusLabel) {
        super(xl, statusLabel, "Load");
    }

    // Load a existing XL file.
    protected void action(String path) throws FileNotFoundException {
        XLBufferedReader reader = new XLBufferedReader(path);

        try {
            reader.load(super.xl.sheet(), statusLabel);
            reader.close(); // Close the stream, check documentation.
        } catch (Exception e) {
            statusLabel.update(e.getMessage());
            throw new XLException(e.getMessage());
        }

        var sheet = super.xl.sheet();

        for(SlotLabel slot : super.xl.slotList()) {
            slot.setText("");
            if (sheet.getCell(slot.getLabel()) != null) {
                slot.setText(sheet.getCell(slot.getLabel()).displayCell(sheet));
            }
        }
    }

    protected int openDialog(JFileChooser fileChooser) {
        return fileChooser.showOpenDialog(xl);
    }
}

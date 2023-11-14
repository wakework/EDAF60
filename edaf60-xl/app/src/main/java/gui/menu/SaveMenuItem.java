package gui.menu;

import java.io.FileNotFoundException;
import javax.swing.JFileChooser;
import gui.StatusLabel;
import gui.XL;
import util.XLException;

class SaveMenuItem extends OpenMenuItem {

    public SaveMenuItem(XL xl, StatusLabel statusLabel) {
        super(xl, statusLabel, "Save");
    }

    // Save a sheet.
    protected void action(String path) throws FileNotFoundException {
        XLPrintStream ps = new XLPrintStream(path);

        // Should save to a file?
        try {
            ps.save(super
                    .xl
                    .sheet()
                    .map()
                    .entrySet());
            ps.close();
        } catch (Exception e) {
            statusLabel.update(e.getMessage());
            throw new XLException(e.getMessage());
        }
    }

    protected int openDialog(JFileChooser fileChooser) {
        return fileChooser.showSaveDialog(xl);
    }
}

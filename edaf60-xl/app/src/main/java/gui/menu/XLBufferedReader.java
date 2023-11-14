package gui.menu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import gui.StatusLabel;
import sheet.Sheet;
import sheet.CellFactory;
import util.XLException;

public class XLBufferedReader extends BufferedReader {

    CellFactory cf = new CellFactory();

    public XLBufferedReader(String name) throws FileNotFoundException {
        super(new FileReader(name));
    }

    // Load a new sheet.
    public void load(Sheet sheet, StatusLabel status) {
        sheet.clearAll();

        try {
            while (ready()) {
                StringBuilder sb1 = new StringBuilder();
                StringBuilder sb2 = new StringBuilder();
                
                var string = readLine();
                var i = string.indexOf('=');
                sb1.append(string, 0, i);
                sb2.append(string, i + 1, string.length());
                
                // Byt mappning
                sheet.map().put(sb1.toString(), cf.create(sb2.toString(), status));
            }
        } catch (Exception e) {
            status.update(e.getMessage());
            throw new XLException(e.getMessage());
        }
    }
}

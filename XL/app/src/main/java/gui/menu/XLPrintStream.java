package gui.menu;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Map.Entry;

import sheet.Cell;

//import java.util.Optional;
import java.util.Set;

public class XLPrintStream extends PrintStream {

    public XLPrintStream(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    // Save a sheet.
    public void save(Set<Entry<String, Cell>> set) {

        for (Entry<String, Cell> entry : set) {
            print(entry.getKey());
            print('=');
            println(entry.getValue().displayEditor());
        }
        
        flush();
        close();
    }
}

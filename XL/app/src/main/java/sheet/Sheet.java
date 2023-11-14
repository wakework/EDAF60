package sheet;

import java.util.Map;
import java.util.TreeMap;

import gui.StatusLabel;
import sheet.expr.Environment;
import util.XLException;

public class Sheet implements Environment {

    private Map<String, Cell> sheet;
    private CellFactory cf;
    private StatusLabel status;

    public Sheet (StatusLabel status) {
        sheet = new TreeMap<>();
        cf = new CellFactory();
        this.status = status;
    }

    @Override
    public double value(String name) {
        if (sheet.get(name) == null) {
            status.update("Cell is empty");
            throw new XLException("Cell is empty");
        }

        return sheet.get(name).value(this);
    } 

    public void clearAll() {
        sheet.clear();
    }

    public void remove(String key) {
        sheet.remove(key);
    }

    public Map<String, Cell> map() {
        return sheet;
    }

    public Cell getCell(String address) {
        return sheet.get(address);
    }

    public String displayCell(String address) {
        if (sheet.containsKey(address)) {
            return sheet.get(address).displayCell(this);
        }

        return "";
    }

    public void addToSlot(String enter, String text) {
        update(enter, cf.create(text, status), getCell(enter));
    }

    // Check if possible to enter Cell at given address
    private void update(String enter, Cell newCell, Cell oldCell) {

        // Bomb test
        sheet.put(enter, new BombTest(status)); 

        try {
            newCell.value(this);
        } finally { // The output is infinite when using catch
            sheet.put(enter, oldCell);
        }

        sheet.put(enter, newCell);
    } 
}

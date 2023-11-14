package gui;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import gui.menu.XLMenuBar;
import sheet.Sheet;

public class XL extends JFrame {

    private static final int ROWS = 10, COLUMNS = 8;
    private XLCounter counter;
    private StatusLabel statusLabel = new StatusLabel();
    private XLList xlList;

    // Our code
    private Sheet sheet;
    private CurrentSlot current;
    private CurrentLabel label;
    private Editor editor;
    private List<SlotLabel> slotList;

    public XL(XL oldXL) {
        this(oldXL.xlList, oldXL.counter);
    }

    public XL(XLList xlList, XLCounter counter) {
        super("Untitled-" + counter);
        this.xlList = xlList;
        this.counter = counter;
        xlList.add(this);
        counter.increment();

        // Our code
        current = new CurrentSlot();
        sheet = new Sheet(statusLabel);
        label = new CurrentLabel();
        slotList = new ArrayList<SlotLabel>(ROWS * COLUMNS);
        editor = new Editor(this, current, statusLabel);
        
        JPanel statusPanel = new StatusPanel(label, statusLabel);
        SlotLabels slotLabels = new SlotLabels(ROWS, COLUMNS, this, current, label, statusLabel, slotList);
        JPanel sheetPanel = new SheetPanel(ROWS, COLUMNS, this, current, label, statusLabel, slotLabels);
        
        add(NORTH, statusPanel);
        add(CENTER, editor);
        add(SOUTH, sheetPanel);
        setJMenuBar(new XLMenuBar(this, xlList, statusLabel, current));
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public void rename(String title) {
        setTitle(title);
    }

    //-------New methods for XL---------
    public Sheet sheet() {
        return sheet;
    }

    public Editor editor() {
        return editor;
    }

    public List<SlotLabel> slotList() {
        return slotList;
    }
    //-----------------------------------

    /**
     * Main 
     * @param args
     */
    public static void main(String[] args) {
        new XL(new XLList(), new XLCounter());
    }
}

package gui.menu;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import gui.CurrentSlot;
import gui.StatusLabel;
import gui.XL;
import gui.XLList;

public class XLMenuBar extends JMenuBar {

    public XLMenuBar(XL xl, XLList xlList, StatusLabel statusLabel, CurrentSlot current) {
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        file.add(new SaveMenuItem(xl, statusLabel));
        file.add(new LoadMenuItem(xl, statusLabel));
        file.add(new NewMenuItem(xl));
        file.add(new CloseMenuItem(xl, xlList));
        edit.add(new ClearMenuItem(xl, current));
        edit.add(new ClearAllMenuItem(xl));
        add(file);
        add(edit);
        add(new WindowMenu(xlList));
    }
}

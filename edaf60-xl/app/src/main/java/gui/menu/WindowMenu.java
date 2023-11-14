package gui.menu;

import javax.swing.JMenu;
import gui.XL;
import gui.XLList;

public class WindowMenu extends JMenu {

    private XLList xlList;

    public WindowMenu(XLList xlList) {
        super("Window");
        this.xlList = xlList;
        update(null);
    }

    public void update(Object object) {
        removeAll();
        for (XL xl : xlList) {
            add(new WindowMenuItem(xl));
        }
    }
}

package Seminarie2;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.event.*;
import java.util.*;


/**
 * Actionhandler ska finnas som en egen klass, annars beror den på vad som
 * händer i Main2. Bryter mot SOLID-principen DIP. Detta leder till att när ett
 * ActionHandler objekt ska skapas måste ett main objekt skapas.
 * 
 * Bryter mot Single-Responsibility!
 * 
 * Egentligen skall GUI och funktion skiljas åt, knapparna ska inte ha med
 * funktionen för att utföra någonting att göra. Composite Pattern.
 * 
 * Svar: Definierar en allmän Factory, sen specifika Factory som 
 * implementerar interfacet. Bryter mot Single-Responsibility!
 * 
 * DIP - interface Application!
 */
public class Main2 {

    public static void main (String[] args) {
        new Main2().run();
    }

    void run() {
        var panel = new JPanel();
        panel.add(new ActionButton("Action1", app -> app.action1()));
    }
    
}

interface Application {

    void action1();
    void action2();
    void action3();

}

class TestApplication implements Application {

    public void action1() {
        System.out.println("Action 1");
    }

    public void action2() {
        System.out.println("Action 2");
    }

    public void action3() {
        System.out.println("Action 3");
    }
}

interface ButtonAction {
    void execute(Application app);
}

class ButtonUI {

    private Application app = new TestApplication();

    abstract class ActionButton 
        extends JButton 
        implements ActionListener {

        private ButtonAction action;

        public ActionButton (String label, ButtonAction action) {
            super(label);
            this.action = action;
            addActionListener(e -> action.execute(app));
        }
    }

    List<ActionButton> buttons = List.of(new ActionButton("Action1", a -> a.action1()),
                                         new ActionButton("Action2", a -> a.action2()),
                                         new ActionButton("Action3", a -> a.action3()), 
                                         new ActionButton("Exit", a -> System.exit(0)));
}
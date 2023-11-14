package Seminarie3;

import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

/**
 * Uppgift 1: Cirkuläritet mellan hardware och software, inte bra!
 * Lösning: Flytta en klass, men det får vi inte. Skapar en wordOperator
 * som pekar på Word. Komposition <>, Word finns OM Memory finns!
 * 
 * Uppgift 2:
 * Bryter mot Dependency Inversion och SRP, single responsibility!
 * Har alla Model(Beror inte på någon annan) View(GUI) Controller i samma klass.
 * 
 * Implementera MVC och Observer-mönster.
 */

public class Switch {

    public static void main(String[] args) {
        new Switch().run();
    }

    private void run() {
        var frame = new JFrame("Seminar 3, problem 2");
        var panel = new JPanel();

        var model = new SwitchModel();
        var view = new SwitchView(model);
        var controller = new SwitchController(model, view);

        panel.add(view);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}

/**
 * M
 */
class SwitchModel extends Observable {

    private boolean on = false;

    public SwitchModel () {
    }

    public boolean isOn() {
        return on;
    }

    public void toggle() {
        on = !on;
        setChanged();
        notifyObservers();
    }
}

/**
 * V
 */
class SwitchView extends JButton {

    private SwitchModel model;

    public SwitchView (SwitchModel model) {
        super("OFF");
        this.model = model;
        model.addObserver((obs, obj) -> update());
    }

    public void update() {
        setText(model.isOn()
                ? "ON" // Vad är detta
                : "OFF");
    }
}

/**
 * C
 */
class SwitchController {

    public SwitchController (SwitchModel model, SwitchView view) {
        view.addActionListener(e -> { // Vid event
            model.toggle();
        });
    }
}
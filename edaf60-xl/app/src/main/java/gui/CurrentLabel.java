package gui;

import java.awt.Color;

public class CurrentLabel extends ColoredLabel {

    public CurrentLabel() {
        super("A1", Color.WHITE);
    }

    public void update(String text) {
        setText(text);
    }
}

package Tentor;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// 3. -------------------Streams------------------------
public class Ex201910 {
    public List<String> wordsOfLength(String text, int wordLength) {
        return 
            Arrays // Array stream
            .stream(text.split(" "))
            .filter(s -> s.length() == wordLength)
            .collect(Collectors.toList()); // Collect(Collectors) gör lista
    }

    public int partialSum(List<String> strings, int min, int max) {
        return 
            strings
            .stream()
            .filter(s -> isInt(s))
            .mapToInt(i -> toInt(i))
            .filter(n -> min <= n && n <= max)
            .sum();
    }

    public boolean isInt(String s) {
        return true;
    }

    public int toInt(String s) {
        return 0;
    }
}


// 4. --------------Decorator Pattern------------------
interface Lever {
    void raise();
}

abstract class DecoratedLever implements Lever {

    protected Lever lever;
    
    public DecoratedLever (Lever lever) {
        this.lever = lever;
    }

    protected abstract void preRaise();

    protected abstract void postRaise();

    public void raise() {
        preRaise();
        lever.raise();
        postRaise();
    }
}

class LoggedLever extends DecoratedLever {

    public LoggedLever(Lever lever) {
        super(lever);
    }

    @Override
    protected void preRaise() {
        System.out.println("Before raising the lever");
    }

    @Override
    protected void postRaise() {
        System.out.println("After raising the lever");
    }
}

class PrintingLever implements Lever {
    
    public void raise() {
        System.out.println("Raising the lever");
    }
}

class M4in {
    public void run() {
        var lever = new LoggedLever(new PrintingLever());
        lever.raise();
    }
}


// 5. ---------------------Lambda------------------------
interface ButtonEvent {
    Button source(); // anger vilken knapp som tryckts

    Instant timeStamp(); // anger tidpunkten
}

interface ButtonPressHandler {
    void handleButtonPress(ButtonEvent event); // anropas vid tryck på knappen
}

class Button {
    public Button (String label) {}

    public void addButtonPressHandler(ButtonPressHandler s) {}
}

abstract class ActionButton extends Button implements ButtonPressHandler {

    protected Mp3Circuit circuit;

    public ActionButton(String label, Mp3Circuit circuit) {
        super(label);
        this.circuit = circuit;
        addButtonPressHandler(this);
    }

    protected abstract void execute(Mp3Circuit circuit);

    @Override
    public void handleButtonPress(ButtonEvent event) {
        execute(circuit);        
    }
}

class PlayPause extends ActionButton {

    public PlayPause(Mp3Circuit circuit) {
        super("Play/Pause", circuit);
    }

    @Override
    protected void execute(Mp3Circuit circuit) {
        circuit.togglePlay();
    }
}

class Previous extends ActionButton {

    public Previous(Mp3Circuit circuit) {
        super("Previous", circuit);
    }

    @Override
    protected void execute(Mp3Circuit circuit) {
        circuit.skip(-1);
    }
}

class Next extends ActionButton {

    public Next(Mp3Circuit circuit) {
        super("Next", circuit);
    }

    @Override
    protected void execute(Mp3Circuit circuit) {
        circuit.skip(1);
    }
}


interface Mp3Circuit {
    void togglePlay(); // startar eller pausar uppspelning

    void skip(int step); // hoppar bland spåren, negativa värden hoppar bakåt

    void changeVolume(int percent); // ändrar volymen, negativa värden sänker den
}

class OmdMp3Circuit implements Mp3Circuit {

    public OmdMp3Circuit () {}

    @Override
    public void togglePlay() {        
    }

    @Override
    public void skip(int step) {
    }

    @Override
    public void changeVolume(int percent) {
    }
    
}

interface Guin {
    void add(Button button); // lägger in en knapp i Guin:t
}

class OmdGuin implements Guin {

    @Override
    public void add(Button button) {}
}

class OriginalPlayer extends OmdGuin implements ButtonPressHandler {
    private Mp3Circuit circuit;
    private Button toggleButton;
    private Button previousButton;
    private Button nextButton;

public OriginalPlayer(Mp3Circuit circuit) {
    this.circuit = circuit;
    toggleButton = new Button("play/pause");
    toggleButton.addButtonPressHandler(this);
    add(toggleButton);
    previousButton = new Button("previous");
    previousButton.addButtonPressHandler(this);
    add(previousButton);
    nextButton = new Button("next");
    nextButton.addButtonPressHandler(this);
    add(nextButton);
}

    public void handleButtonPress(ButtonEvent event) {
        if (event.source() == toggleButton) {
            circuit.togglePlay();
        } else if (event.source() == previousButton) {
            circuit.skip(-1);
        } else if (event.source() == nextButton) {
            circuit.skip(1);
        }
    }
}

// Bryter mot OCP eftersom vi behandlar många olika events i metoden
class SolidPlayer extends OmdGuin {

    public SolidPlayer (Mp3Circuit circuit) {
        add(new PlayPause(circuit));
        add(new Previous(circuit));
        add(new Next(circuit));
    }

    public void add(ActionButton button) {
        add(button);
    }
}

class M5in {
    void run() {
        //var mp3 = new OmdMp3Circuit();
        //var player = new SolidPlayer(mp3);
        //player.addButton("volume up", c -> c.changeVolume(10));
    }
}


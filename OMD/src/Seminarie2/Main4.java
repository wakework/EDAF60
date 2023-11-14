package Seminarie2;

/**
 * 1 Svar: Decorator Pattern! Wrappar in klasserna i varandra. 
 * I detta fall, lägger på utskriften, sedan skickar vidare.
 * 
 * 2 Svar: Template Method, olika implementation på handleByte!
 * 
 * 3 Svar: Kapsla in allt!
 */

public class Main4 {

    public void start(ByteSource source) {
        var processor = new StreamProcessorSupervisor(new StreamProcessorLogger(
            new AcmeStreamProcessor()));
        new Streamer().run(source, processor);
    }
}

interface ByteSource {
    boolean hasNext(); // true om det finns fler bytes att hämta

    byte next(); // ger nästa byte
}

abstract class StreamProcessorDecorator implements StreamProcessor {

    private StreamProcessor processor;

    public StreamProcessorDecorator(StreamProcessor processor) {
        this.processor = processor;
    }

    @Override
    public final void handle(byte b) {
        handleByte(b);
        processor.handle(b);
    }

    protected abstract void handleByte(byte b);
}

class StreamProcessorLogger extends StreamProcessorDecorator {

    public StreamProcessorLogger(StreamProcessor processor) {
        super(processor);
    }

    protected void handleByte(byte b) {
        System.out.println(b);
    }
}

class StreamProcessorSupervisor extends StreamProcessorDecorator {

    private int counter = 0;

    public StreamProcessorSupervisor(StreamProcessor processor) {
        super(processor);
    }

    protected void handleByte(byte b) {
        if (b == 127) {
            counter++;
            if (counter == 3) {
                throw new IllegalSequence();
            }
        } else {
            counter = 0;
        }
    }
}

interface StreamProcessor {
    void handle(byte b); // hanterar en byte (skickar den på något sätt)
}

class AcmeStreamProcessor implements StreamProcessor {

    public void handle(byte b) {
    }
}

final class Streamer {

    public void run(ByteSource source, StreamProcessor processor) {
        while (source.hasNext()) {
            processor.handle(source.next());
        }
    }
}

class IllegalSequence extends RuntimeException {

}
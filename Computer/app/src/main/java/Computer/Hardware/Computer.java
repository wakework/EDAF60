package Computer.Hardware;

/**
 * Computer running a Program with Memory and ProgramCounter.
 */
public class Computer {

    private Memory memory;
    private Program program;
    private ProgramCounter pc;

    public Computer(Memory memory) {
        this.memory = memory;
        program = null;
        pc = new ProgramCounter();
    }

    public void load(Program program) {
        this.program = program;
    }

    public void run() {
        int idx = 0;
        Instruction ins;

        // Main Program loop. If Program is not halted, next Instruction is called.
        while (!pc.isHalt()) {

            // Initial values each loop.
            idx = pc.getIndex();

            ins = program.getIns(idx);

            ins.execute(memory, pc);

            // Next Instruction.
            pc.newIns();
        }
    }
}

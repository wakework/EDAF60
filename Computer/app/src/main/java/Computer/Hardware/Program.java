package Computer.Hardware;

import java.util.*;

/**
 * The Progam includes a list for instructions.
 */
public class Program {

    private List<Instruction> list;

    public Program (String value, WordFactory wf) {
        list = new ArrayList<>();
    }
    
    /**
     * Adds the specified instruction to the list.
     * @param instruction
     */
    public void add(Instruction instruction){
        list.add(instruction);
    }

    public Instruction getIns(int index) {
        return list.get(index);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        int i = 0;

        for (Instruction ins : list) {
            sb.append (i + ": " + ins.toString() + "\n");
            i++;
        }

        return sb.toString();
    }
}

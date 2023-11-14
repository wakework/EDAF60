package gui;

import java.awt.Color;
import java.util.List;
import javax.swing.SwingConstants;

public class SlotLabels extends GridPanel {

    //private List<SlotLabel> labelList;

    public SlotLabels(int rows, int cols, XL xl, CurrentSlot current, 
                        CurrentLabel cl, StatusLabel status, List<SlotLabel> labelList) {
        super(rows + 1, cols);
        //this.labelList = labelList;

        for (char ch = 'A'; ch < 'A' + cols; ch++) {
            add(new ColoredLabel(Character.toString(ch), Color.LIGHT_GRAY, SwingConstants.CENTER));
        }

        // Create address
        StringBuilder sb = new StringBuilder();

        for (int row = 1; row <= rows; row++) {
            for (char ch = 'A'; ch < 'A' + cols; ch++) {
                sb.append(ch + String.valueOf(row));

                SlotLabel label = new SlotLabel(xl, sb.toString(), current, cl, status);
                
                add(label);
                labelList.add(label);

                sb.delete(0, sb.length());
            }
        }
        SlotLabel firstLabel = labelList.get(0);
        firstLabel.setBackground(Color.YELLOW);
        current.setCurrent(firstLabel);
    }
}

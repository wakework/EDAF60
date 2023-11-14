package sheet;

import sheet.expr.Environment;

/**
 * Should show the comment in sheet.
 */
public class CommentCell implements Cell {

    private String com;

    public CommentCell (String com) {
        this.com = com;
    }

    @Override
    public double value(Environment env) {
        return 0;
    }

    @Override
    public String displayCell(Environment env) {
        return com.toString();
    }
    
    public String displayEditor() {
        return "#" + com;
    }
}

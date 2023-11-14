package sheet;

import java.io.IOException;

import gui.StatusLabel;
//import java.util.Optional;
import sheet.expr.*;
import util.XLException;

public class CellFactory {  

    private ExprParser parser = new ExprParser();

    public Cell create(String string, StatusLabel status) {

        // Optional EMPTY
        if (string.equals("")) {
            return null;
        }

        // # first -> create CommentCell
        if(string.charAt(0) == '#') {

            return new CommentCell(string.substring(1));

        } 

        // Else ExpressionCell, but must handle IOException
        try {
            var e = parser.build(string);
            return new ExpressionCell(e);

        } catch (IOException exception) {
            status.update(exception.getMessage());
            throw new XLException(exception.getMessage());
        }
    }
}

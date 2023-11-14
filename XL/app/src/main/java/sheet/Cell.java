package sheet;

import sheet.expr.Environment;

public interface Cell {

    double value(Environment env);

    String displayEditor();

    String displayCell(Environment env);

}

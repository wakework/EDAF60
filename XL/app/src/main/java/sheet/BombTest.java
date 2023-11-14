package sheet;

import gui.StatusLabel;
import sheet.expr.Environment;
import util.XLException;

public class BombTest implements Cell {

    //private StatusLabel status;

    protected BombTest(StatusLabel status) {
        //this.status = status;
    }

    @Override
    public double value(Environment env) {
        //status.update("Cyclic reference error");
        throw new XLException("Cyclic reference error");
    }

    @Override
    public String displayEditor() {
        return "";
    }

    @Override
    public String displayCell(Environment env) {
        return "";
    }

    
}

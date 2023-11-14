package sheet;

import sheet.expr.Environment;
import sheet.expr.Expr;

/** 
 * Should express the computed value of a expression in the sheet.
 */
public class ExpressionCell implements Cell {

    private Expr expr;

    public ExpressionCell(Expr expr){
        this.expr = expr;
    }

    @Override
    public double value(Environment env) {
        return expr.value(env);
    }

    @Override
    public String displayCell(Environment env) {
        return Double.toString(value(env));
    }

    public String displayEditor() {
        return expr.toString();
    }
    
}

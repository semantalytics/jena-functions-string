package com.semantalytics.jena.function.string;

import org.apache.jena.atlas.lib.Lib;
import org.apache.jena.query.QueryBuildException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.ExprList;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;

import static org.apache.commons.lang3.StringUtils.*;
import static org.apache.jena.sparql.expr.NodeValue.*;

public final class AbbreviateMiddle extends FunctionBase3 {

    public static final String name = StringVocabulary.abbreviateMiddle.stringValue();

    @Override
    public NodeValue exec(final NodeValue arg0, final NodeValue arg1, final NodeValue arg2) {

        if(!arg0.isString())
            throw new ExprEvalException(Lib.className(this) + " first argument must be a string literal");

        if(!arg1.isString())
            throw new ExprEvalException(Lib.className(this) + " first argument must be a string literal");

        if(!arg2.isInteger())
            throw new ExprEvalException(Lib.className(this) + " third argument must be a integer literal");

       final String string = arg0.getString();
       final String middle = arg1.getString();
       final int length  = arg2.getInteger().intValue();

      return makeString(abbreviateMiddle(string, middle, length));
    }

    @Override
    public void checkBuild(String uri, ExprList args) {
        if(args.size() != 3) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' takes three arguments");
        }
        if(args.get(0).isConstant() && !args.get(0).getConstant().isString()) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' first argument must be a string literal") ;
        }
        if(args.get(1).isConstant() && !args.get(1).getConstant().isString()) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' second argument must be a string literal") ;
        }
        if(args.get(2).isConstant() && !args.get(2).getConstant().isInteger()) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' third argument must be a integer literal") ;
        }
    }
}

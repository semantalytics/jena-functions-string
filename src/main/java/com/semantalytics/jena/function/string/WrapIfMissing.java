package com.semantalytics.jena.function.string;

import org.apache.jena.atlas.lib.Lib;
import org.apache.jena.query.QueryBuildException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.ExprList;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;

import static org.apache.commons.lang3.StringUtils.*;
import static org.apache.jena.sparql.expr.NodeValue.*;

public final class WrapIfMissing extends FunctionBase2 {

    public static final String name = StringVocabulary.wrapIfMissing.stringValue();

    @Override
    public NodeValue exec(final NodeValue arg0, final NodeValue arg1) {

        if(!arg0.isString())
            throw new ExprEvalException(Lib.className(this) + " first argument must be a string literal");
        if(!arg1.isString())
            throw new ExprEvalException(Lib.className(this) + " second argument must be a string literal");

        final String string = arg0.asString();
        final String wrapWith = arg1.asString();

        return makeString(wrapIfMissing(string, wrapWith.charAt(0)));
    }

    @Override
    public void checkBuild(final String uri, final ExprList args) {
        if(args.size() != 2) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' takes two arguments") ;
        }
        if(args.get(0).isConstant() && !args.get(0).getConstant().isString()) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' first argument must be a string literal") ;
        }
        if(args.get(1).isConstant() && !args.get(1).getConstant().isString()) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' second argument must be a string literal") ;
        }
    }
}

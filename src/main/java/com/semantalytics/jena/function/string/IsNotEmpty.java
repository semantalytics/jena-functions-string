package com.semantalytics.jena.function.string;

import org.apache.jena.atlas.lib.Lib;
import org.apache.jena.query.QueryBuildException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.ExprList;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import static org.apache.commons.lang3.StringUtils.*;
import static org.apache.jena.sparql.expr.NodeValue.*;

public final class IsNotEmpty extends FunctionBase1 {

    public static final String name = StringVocabulary.isNotEmpty.stringValue();

    @Override
    public NodeValue exec(final NodeValue arg0) {

        if(!arg0.isString())
            throw new ExprEvalException(Lib.className(this) + " first argument must be a string literal");

        return makeBoolean(isNotBlank(arg0.asString()));
    }

    @Override
    public void checkBuild(String uri, ExprList args) {
        if(args.size() != 1) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' takes one argument") ;
        }
        if(args.get(0).isConstant() && !args.get(0).getConstant().isString()) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' first argument must be a string literal") ;
        }
    }
}

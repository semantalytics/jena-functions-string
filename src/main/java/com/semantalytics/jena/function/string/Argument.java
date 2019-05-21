package com.semantalytics.jena.function.string;

import org.apache.jena.query.QueryBuildException;
import org.apache.jena.sparql.expr.Expr;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;

public class Argument {
    public static final String isString(final NodeValue nodeValue) throws ExprEvalException {
        if (!nodeValue.isString()) {
            throw new ExprEvalException("argument must be a string literal");
        } else {
            return nodeValue.asString();
        }
    }

    public static final void isConstantString(final Expr expr) throws QueryBuildException {
        if(expr.isConstant() && !expr.getConstant().isString()) {
            throw new QueryBuildException("argument must be a string literal");
        }
    }

    public static final void isConstantInteger(final Expr expr) throws QueryBuildException {
        if(expr.isConstant() && !expr.getConstant().isInteger()) {
            throw new QueryBuildException("argument must be a integer literal") ;
        }
    }
}

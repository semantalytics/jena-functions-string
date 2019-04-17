package com.semantalytics.jena.function.string;

import org.apache.jena.atlas.lib.Lib;
import org.apache.jena.ext.com.google.common.collect.Range;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryBuildException;
import org.apache.jena.sparql.ARQInternalErrorException;
import org.apache.jena.sparql.expr.Expr;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.ExprList;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase;

import java.util.List;
import java.util.stream.StreamSupport;

import static org.apache.commons.lang3.StringUtils.*;
import static org.apache.jena.sparql.expr.NodeValue.*;

public final class EqualsAny extends FunctionBase {

    public static final String name = StringVocabulary.equalsAny.stringValue();

    @Override
    public NodeValue exec(List<NodeValue> args) {

        if ( args == null )
            // The contract on the function interface is that this should not happen.
            throw new ARQInternalErrorException(Lib.className(this) + ": Null args list") ;
        if(!args.stream().allMatch(NodeValue::isString)) {
            throw new ExprEvalException("All arguments must be strings");
        }

        final String string = args.get(0).asString();
        final String[] searchStrings = args.stream().skip(1).map(NodeValue::getString).toArray(String[]::new);

        return makeBoolean(equalsAny(string, searchStrings));
    }

    @Override
    public void checkBuild(String uri, ExprList args) {
        if(!Range.atLeast(2).contains(args.size())) {
            throw new QueryBuildException("Function '" + Lib.className(this) + " takes at least two arguments") ;
        }
        if(!StreamSupport.stream(args.spliterator(), false).filter(Expr::isConstant).map(Expr::getConstant).allMatch(NodeValue::isString)) {
            throw new QueryBuildException("All arguments must be strings");
        }
    }
}

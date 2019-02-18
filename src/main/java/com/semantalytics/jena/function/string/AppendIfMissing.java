package com.semantalytics.jena.function.string;

import org.apache.commons.lang3.StringUtils;
import org.apache.jena.atlas.lib.Lib;
import org.apache.jena.ext.com.google.common.collect.Range;
import org.apache.jena.query.QueryBuildException;
import org.apache.jena.sparql.ARQInternalErrorException;
import org.apache.jena.sparql.expr.Expr;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.ExprList;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase;

import java.util.List;
import java.util.stream.StreamSupport;

public final class AppendIfMissing extends FunctionBase {

    public static final String name = StringVocabulary.appendIfMissing.stringValue();

    @Override
    public NodeValue exec(final List<NodeValue> args) {

        if ( args == null )
            // The contract on the function interface is that this should not happen.
            throw new ARQInternalErrorException(Lib.className(this) + ": Null args list") ;

        if (!Range.atLeast(2).contains(args.size()))
            throw new ExprEvalException(Lib.className(this)+": Wrong number of arguments: Wanted at least 2, got "+args.size()) ;

        if(!args.stream().allMatch(NodeValue::isString))
            throw new ExprEvalException(Lib.className(this) + " all arguments must be a string literal");

        final NodeValue result;

        final String string = args.get(0).getString();
        final String suffix = args.get(1).getString();

        switch(args.size()) {
            case 2: {

                result =  NodeValue.makeString(StringUtils.appendIfMissing(string, suffix));
                break;
            }
            case 3: {
                final String[] suffixes = args.stream().skip(2).toArray(String[]::new);

                result =  NodeValue.makeString(StringUtils.appendIfMissing(string, suffix, suffixes));
                break;
            }
            default: {
                throw new ExprEvalException("Should have at least 2 arguments. Found " + args.size());
            }
        }
        return result;
    }

    @Override
    public void checkBuild(String uri, ExprList args) {
        if(!Range.atLeast(2).contains(args.size())) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' takes two or more arguments") ;
        }
        if(!StreamSupport.stream(args.spliterator(), false).filter(Expr::isConstant).map(Expr::getConstant).allMatch(NodeValue::isString)) {
            throw new QueryBuildException("Function '" + Lib.className(this) + " all arguments must be a string literal") ;

        }
    }
}

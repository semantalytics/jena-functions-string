package com.semantalytics.jena.function.string;

import org.apache.commons.lang3.StringUtils;
import org.apache.jena.atlas.lib.Lib;
import org.apache.jena.ext.com.google.common.collect.Range;
import org.apache.jena.query.QueryBuildException;
import org.apache.jena.sparql.ARQInternalErrorException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.ExprList;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase;

import java.util.List;

public final class LastIndexOfIgnoreCase extends FunctionBase {

    public static final String name = StringVocabulary.lastIndexOfIgnoreCase.stringValue();

    @Override
    public NodeValue exec(final List<NodeValue> args) {

        if ( args == null )
            // The contract on the function interface is that this should not happen.
            throw new ARQInternalErrorException(Lib.className(this) + ": Null args list") ;

        if (!Range.closed(2, 3).contains(args.size()))
            throw new ExprEvalException(Lib.className(this)+": Wrong number of arguments: Wanted either 2 or 3, got "+args.size()) ;

        final String string = args.get(0).asString();
        final String searchString = args.get(1).asString();

        switch (args.size()) {
            case 2: {
                return NodeValue.makeInteger(StringUtils.lastIndexOfIgnoreCase(string, searchString));
            }
            case 3: {
                final int startPos = args.get(2).getInteger().intValue();
                return NodeValue.makeInteger(StringUtils.lastIndexOfIgnoreCase(string, searchString, startPos));
            }
            default:
                throw new ExprEvalException("function takes two or three arguments. Found " + args.size());
        }
    }

    @Override
    public void checkBuild(String uri, ExprList args) {
        if(!Range.closed(2, 3).contains(args.size())) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' takes two or three arguments") ;
        }
    }
}
